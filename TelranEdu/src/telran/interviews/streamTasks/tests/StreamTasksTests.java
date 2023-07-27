package telran.interviews.streamTasks.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import telran.interviews.streamTasks.StreamTasks;

;class StreamTasksTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void displayOccurrencesTest() {
		String [] strings = {"lmn", "ab", "lmn", "c", "ab", "a", "lmn", "aaa"};
		StreamTasks.displayOccurrences(strings);
	}
	@Test
	void displayOddEvenGroupingTest() {
		StreamTasks.displayOddEvenGrouping(20);
	}
	@Test
	void displayDigitStatisticsTest() {
		StreamTasks.displayDigitStatistics();
	}
	@Test
	void displayPersonsMap() {
		System.out.println(StreamTasks.getRandomPersonsMap(5));
		
	}

}
