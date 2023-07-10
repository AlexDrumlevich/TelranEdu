package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
	
		if(!(temporal.isSupported(ChronoUnit.MONTHS) && temporal.isSupported(ChronoField.DAY_OF_MONTH))) {
			throw new UnsupportedTemporalTypeException("Temporal is not supported Chrono unit month or year");
		}
		
		if(temporal.get(ChronoField.DAY_OF_MONTH) >= 13) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		temporal = temporal.with(ChronoField.DAY_OF_MONTH, 13);

		while (temporal.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		
		return temporal;
	}

}
