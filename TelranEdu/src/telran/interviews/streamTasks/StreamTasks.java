package telran.interviews.streamTasks;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntFunction;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.StringValueExp;

public class StreamTasks {
      static public void displayOccurrences(String strings[]) {
    	  Arrays.stream(strings)
    	  .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
    	  .entrySet().stream().sorted((e1, e2) -> {
    		  int res = Long.compare(e2.getValue(), e1.getValue());
    		  return res != 0 ? res : e1.getKey().compareTo(e2.getKey());
    	  })
    	  .forEach(e -> System.out.printf("%s -> %s\n", e.getKey(), e.getValue()));
      }
      static public long sumGroups(int [][] groups) {
    	  return Arrays.stream(groups)
    			  .flatMapToInt(g -> Arrays.stream(g)).asLongStream()
    			  .sum();
      }
      static public void displayOddEvenGrouping(int nNumbers) {
    	  new Random().ints(nNumbers, 0, Integer.MAX_VALUE ).boxed()
    	  .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd",
    			  Collectors.summingLong(x -> x)))
    	  .forEach((k, v) -> System.out.printf("%s -> %d\n", k, v));
      }
      
      static public void printDigitStatistics() {
    	  new Random()
    	  	.ints(1000000, 0, Integer.MAX_VALUE)
    	  	.flatMap(intValue -> String.valueOf(intValue).chars())
    	  	.boxed()
    	  	.collect(Collectors.groupingBy(charInteger -> Character.valueOf((char) charInteger.intValue()), Collectors.counting()))
    	  	.entrySet()
    	  	.stream()
    	  	.sorted((entry1, entry2) -> (entry1.getKey().charValue() == '0' || entry2.getKey().charValue() == '0') ? (entry1.getKey().charValue() == '0' ? 1 : -1) : Character.compare(entry1.getKey(), entry2.getKey()))
    	  	.forEach(entry -> System.out.printf("%s - %d\n", entry.getKey(), entry.getValue()));
    	  	
      }
      
}
