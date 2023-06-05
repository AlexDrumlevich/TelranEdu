package telran.algorithm.recursion;

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
	
	public static long pow(int a, int b) {
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
				
			
				return 0;
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
	
	
	
}
