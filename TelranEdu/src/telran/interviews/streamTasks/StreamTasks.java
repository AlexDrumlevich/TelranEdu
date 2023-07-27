package telran.interviews.streamTasks;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntFunction;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.StringValueExp;

public class StreamTasks {
	
	//groupingBy + counting
    static public void displayOccurrences(String strings[]) {
  	  Arrays.stream(strings)
  	  .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
  	  .entrySet().stream().sorted((e1, e2) -> {
  		  int res = Long.compare(e2.getValue(), e1.getValue());
  		  return res != 0 ? res : e1.getKey().compareTo(e2.getKey());
  	  })
  	  .forEach(e -> System.out.printf("%s -> %s\n", e.getKey(), e.getValue()));
    }
    
    //sum
    static public long sumGroups(int [][] groups) {
  	  return Arrays.stream(groups)
  			  .flatMapToInt(g -> Arrays.stream(g)).asLongStream()
  			  .sum();
    }
    
  //groupingBy + summingLong
    static public void displayOddEvenGrouping(int nNumbers) {
  	  new Random().ints(nNumbers, 0, Integer.MAX_VALUE ).boxed()
  	  .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd",
  			  Collectors.summingLong(x -> x)))
  	  .forEach((k, v) -> System.out.printf("%s -> %d\n", k, v));
    }
    
    //groupingBy + counting
    static public void displayDigitStatistics() {
  	  int nNumbers = 1_000_000;
//  	  new Random().ints(nNumbers, 0, Integer.MAX_VALUE)
//  	  .flatMap(num -> Integer.toString(num).chars())
//  	  .boxed().collect(Collectors.groupingBy(d -> d,
//  			  Collectors.counting()))
//  	  .entrySet().stream()
//  	  .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
//  	  .forEach(e -> System.out.printf("%c: %d\n", e.getKey(), e.getValue()));
  	  new Random().ints(nNumbers, 0, Integer.MAX_VALUE)
  	  .mapToObj(Integer::toString).flatMap(s ->
  	  Arrays.stream(s.split("")))
  	  .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
  	  .entrySet().stream()
  	  .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
  	  .forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
  	  
  	  
    }
    //MapTo
    public static Map<Integer, Person> getRandomPersonsMap(int nPersons) {
  	  return new Random().ints(1000, 2001).distinct()
  			  .limit(nPersons).mapToObj(id -> new Person(id, "name"+id))
  			  .collect(Collectors.toMap(p -> p.id(), p -> p,
  					  (p, u) -> p, TreeMap::new));
    }
    
}

record Person(Integer id, String name) {}
