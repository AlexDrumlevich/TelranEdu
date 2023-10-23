package telran.perfomance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class JoinStringsPerfomanceAppl {
	private final static String PACKAGE = "telran.perfomance."; 	
	
	
	public static void main(String[] args) {
		
		if(args.length < 1) {
			System.out.println("Too few arguments: must be at least one name of class");
		} else {
			final int N_STRINGS = 1000;
			String string = "ABC";
			String[] strings = new String[N_STRINGS];	
			Arrays.fill(strings, string);
			
			IntStream.range(0, args.length).forEach(i ->  {
				String className = args[i];
				try {
					Class<?> clazz = Class.forName(PACKAGE + className);
					Constructor<?> constructor = clazz.getConstructor();
					
					Object someClass = constructor.newInstance();
					JoinStrings joinStrings;
					if(someClass instanceof JoinStrings) {
						joinStrings = (JoinStrings) someClass;
					} else {
						throw new ClassCastException();
					}
					
					JoinStringsPerfomanceTest joinStringTest = new JoinStringsPerfomanceTest(String.format("Join %s Test", className), N_STRINGS, strings, joinStrings);
					joinStringTest.run();
				} catch (ClassNotFoundException e) {
					System.out.println(String.format("Class %s not found", PACKAGE + className));
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println(String.format("Method %s() not found", className));
					e.printStackTrace();
				} catch (InstantiationException e) {
					System.out.println(String.format("Class %s can`t be instantiated", className));
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					System.out.println(String.format("Method %s() is not accessable", className));
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println(String.format("Illegal arguments for method %s()", className));
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					System.out.println(String.format("Method %s() can`t be invocated", className));
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println(String.format("Class %s does not implement JoinStrings interface", className));
					e.printStackTrace();
				}
		
			});	
		}
		
	}
	
}
