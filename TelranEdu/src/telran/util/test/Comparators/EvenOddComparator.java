package telran.util.test.Comparators;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	
	//even - odd: 
	// остаток от деления имеет знак!!! если от отрицательного то будет минус в остатке
	// поэтому используем Math.abs - абсолютное число (больше 0)
	 	@Override
	 	public int compare(Integer o1, Integer o2) {
	 		int res = Integer.compare(Math.abs(o1 % 2), Math.abs(o2 % 2)) ;
	 		if  (res == 0) {
	 			res = o1 % 2 == 0 ? Integer.compare(o1, o2) :
	 				Integer.compare(o2, o1);
	 		}
	 		return res;
	 	}	
}
