package telran.util;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(square(-10));
		
		System.out.println(pow(2, 3));
		
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
	
	 
	
	public static long pow(int value, int power) {
		
		//3^3 = 3^2 + 3^2 + 3^2
		//3^2 = 3^1 + 3^1 + 3^1		
		//3^1 = 3^0 + 3^0 + 3^0
		
		//2^3 = 2^2 + 2^2
		//2^2 = 2^1 + 2^1
		//2^1 = 2^0 + 2^0
		
		if(power < 0) {
			throw new IllegalArgumentException();
		}
		return getSubPow(value - 1, value, power);
	}
	
	private static long getSubPow(int count, int value, int power) {
	
		long result = 0;
		if (power == 1) {
			result = value;
		} else if (power == 0) {
			result = 1;
		} else if (value == 0) {
			result = 0;
		}
		
		if(power > 1) {
			if(count > 0) {
				result += getSubPow(count - 1, value, power);
			} 	
				result += getSubPow(count, value, power - 1);
		}

		System.out.println(result);
		return result;
	}
		

	
}
