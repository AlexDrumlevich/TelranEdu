package telran.time.application;

import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import telran.util.LinkedHashSet;

public class PrintCalendar {
private static final int TITLE_OFFSET = 8;

//HW
static LinkedHashMap<DayOfWeek, Integer> daysOfWeek;
	
	public static void main(String[] args)  {
		try {
			RecordArguments recordArguments = getRecordArguments(args);
			daysOfWeek = getDayOfWeekMap(recordArguments.firstWeekDay());
			printCalendar(recordArguments);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	
	private static RecordArguments getRecordArguments(String[] args) throws Exception {
		LocalDate ld = LocalDate.now();
		int month = args.length == 0 ? ld.get(ChronoField.MONTH_OF_YEAR) :
			getMonth(args[0]);
		int year = args.length > 1 ? getYear(args[1]) :
			ld.get(ChronoField.YEAR);
		//HW
		DayOfWeek startWeekDay = args.length > 2 ? getStartWeekDay(args[2]) : DayOfWeek.MONDAY;  
		return new RecordArguments(month, year, startWeekDay);
	}

	
	//HW get Map <DayOfWeek, Integer>
	private static LinkedHashMap<DayOfWeek, Integer> getDayOfWeekMap(DayOfWeek firstDayOfWeek) {
		DayOfWeek[] daysOfWeek = DayOfWeek.values();
		int startIndex = firstDayOfWeek.getValue();
		LinkedHashMap<DayOfWeek, Integer> daysOfWeekMap = new LinkedHashMap<>();
		for(int i = 1; i <= daysOfWeek.length; i++) {
			daysOfWeekMap.put(daysOfWeek[startIndex - 1], i);
			System.out.println(startIndex);
			startIndex = startIndex == daysOfWeek.length ? 1 : startIndex + 1; 
		}
		return daysOfWeekMap;
	}

	//HW get StartWeekDay
	private static DayOfWeek getStartWeekDay(String startWeekDayString) throws Exception {
		DayOfWeek startWeekDay;
		try {
			startWeekDay = DayOfWeek.valueOf(startWeekDayString.toUpperCase());
		} catch (Exception e) {
			StringBuilder validDaysOfWeekString = new StringBuilder();
			Arrays.stream(DayOfWeek.values()).forEach(day -> validDaysOfWeekString.append(day.toString() + " "));
			throw new Exception("imposible regognize day of week, please use one of the next values: " + validDaysOfWeekString.toString());
		
		}
		return startWeekDay;
	}
	
	
	
	private static void printCalendar(RecordArguments recordArguments) {
		printTitle(recordArguments.month(), recordArguments.year());
		printWeekDays();
		printDays(recordArguments.month(), recordArguments.year());
	}

	private static void printDays(int month, int year) {
		int nDays = getNumberOfDays(month, year);
		int currentWeekDay = getFirstWeekDay(month, year);
		printOffset(currentWeekDay);
		for (int day = 1; day <= nDays; day++) {
			System.out.printf("%4d",day);
			currentWeekDay++;
			if(currentWeekDay == 7) {
				currentWeekDay = 0;
				System.out.println();
			}
			
		}
		
	}

	private static void printOffset(int currentWeekDay) {
		System.out.printf("%s", " ".repeat(4 * currentWeekDay));
		
	}

	
	private static int getFirstWeekDay(int month, int year) {
		//HW
		int weekDayNumber = daysOfWeek.get(LocalDate.of(year, month, 1).getDayOfWeek());
	
		return weekDayNumber - 1;
	}

	private static int getNumberOfDays(int month, int year) {
		YearMonth ym = YearMonth.of(year, month);
		return ym.lengthOfMonth();
	}

	private static void printWeekDays() {
		System.out.print("  ");
		//HW
		daysOfWeek
			.keySet()
			.stream()
			.forEach(dw -> System.out.printf("%s ", dw.getDisplayName(TextStyle.SHORT, Locale.getDefault())));
		
		System.out.println();
		
	}

	private static void printTitle(int monthNumber, int year) {
		// <year>, <month name>
		Month month = Month.of(monthNumber);
		String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE,
				Locale.getDefault());
		System.out.printf("%s%s, %d\n", " ".repeat(TITLE_OFFSET),monthName, year);
		
	}

	private static int getYear(String yearStr) throws Exception {
		String message = "";
		int year = 0;
		try {
			year = Integer.parseInt(yearStr);
			if(year < 0) {
				message = "year must be a positive number";
			}
			
		} catch (NumberFormatException e) {
			message = "year must be a number";
		}
		if(!message.isEmpty()) {
			throw new Exception(message);
		}
		return year;
	}

	private static int getMonth(String monthStr) throws Exception {
		String message = "";
		int month = 0;
		try {
			month = Integer.parseInt(monthStr);
			if(month < 1 || month > 12) {
				message = "month must be in the range [1-12]";
			}
			
		} catch (NumberFormatException e) {
			message = "month must be a number";
		}
		if(!message.isEmpty()) {
			throw new Exception(message);
		}
		return month;
	}

}
