package telran.algorithm.recursion;

import java.util.concurrent.CountDownLatch;

public class LineRecursion {
	
	public static long factorial(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		long res = 1;
		if(n > 1) {
			res = n*factorial(n - 1);
		}
		return res;
	}
	
	

	private static int powCall;
	private static int multiplyCall;
	
	public static int pow(int a, int b) {
		//a - any number
		//b - any positive number or zero
//		if (b < 0) {
//				throw new IllegalArgumentException("Pow cannot be negative value");
//		}
//		long res = 1;
//		if (b > 0) {
//			res = a * pow(a, b - 1); // a^b = a * a^(b - 1)
//		}
//		return res;
		//TODO HW #18
		//Limitations:
		// 1. no cycles
		// 2. only + or - for arithmetic operations
		
		
		
				//3^3 = 3^2 + 3^2 + 3^2
				//3^2 = 3^1 + 3^1 + 3^1		
				//3^1 = 3^0 + 3^0 + 3^0
				
				//2^3 = 2^2 + 2^2
				//2^2 = 2^1 + 2^1
				//2^1 = 2^0 + 2^0
				
		System.out.println("Pow call " + ++powCall + " power: " + b);
		if (b < 0) {
			throw new IllegalArgumentException("Pow cannot be negative value");
		}
		int res = 1;
		if (b > 0) {
			res = multiply(a, pow(a, b - 1)); // a^b = a * a^(b - 1)
		}
		return res;
	}
	
	public static int multiply(int a, int b) {
		System.out.println("Multiply call " + ++multiplyCall + ". " + " a: " + a +  " b: " + b);
		//make all positive, saved in other var
		int x = (a>=0) ? a : -a;
		int y = (b>=0) ? b : -b;
		int res = 0;
		if(y>0) {
			res = x + multiply(x, y - 1); // x + 0 -> x + x -> 2x + x -> 3x + x
			System.out.println("Res: " + res);
		}
		
		// - * - = +  (no(!) + and +  OR - and -)  
		if(!((a>0 && b>0) || (a<0 && b<0))) {
			res = -res;
		}
		System.out.println("Res final: " + res);
		return res;
	}
	
	
	
	
	
	
	
	public static long sum(int[] array) {
		return sum(0, array);
	}
	
	private static long sum(int firstIndex, int[] array) {
		long sum = 0;
		if (firstIndex < array.length) {
			sum = array[firstIndex] + sum(firstIndex + 1, array);
		}
		return sum;
	}
	
	public static int[] reverse(int[] array) {
		return reverse(array, 0, array.length - 1);
	}

	private static int[] reverse(int[] array, int left, int right) {
		
		if(left < right) {
			array[left] = array[left] + array[right];
			array[right] = array[left] - array[right];
			array[left] = array[left] - array[right];
			reverse(array, left + 1, right - 1);
		}
		return array;
	}
	
	
	public static int square(int x) {
		// difference between previous and next result: x + x - 1:
		//0^2 = 0
		//1^2 = 0^2 + 1 + 1 - 1
		//2^2 = 1^2 + 2 + 2 - 1
		//3^2 = 2^2 + 3 + 3 - 1
		//4^2 = 3^2 + 4 + 4 - 1
		//5^2 = 4^2 + 5 + 5 - 1
		int absValue = Math.abs(x);
		int result = 0;
		if(absValue > 0) {
			result = absValue + absValue + -1 + square(absValue + -1);
		}
		return result;   	
	}
	

	public static boolean isSubstring(String string, String substr) {
		boolean exists = false;
		// base case 1: substr is empty
		if (substr.length() == 0) {
			exists = true;
		} else if (string.length() == 0 || string.length() < substr.length()) {
		// base case 2: string is empty or shorter than substr
			exists = false;
		} else if (string.charAt(0) == substr.charAt(0)) {
			// recursive case: compare first character of substr with each character of string		
			// if match, compare rest of substr with rest of string
			exists = isSubstring(string.substring(1), substr.substring(1));
		} else {
			// if no match, move to next character of string
			exists = isSubstring(string.substring(1), substr);
		}
		return exists;
	}
}


	
	
