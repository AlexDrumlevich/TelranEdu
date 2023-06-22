package telran.perfonance;

import java.util.Arrays;

public class JoinStringsPerfomanceAppl {
	
	public static void main(String[] args) {
		final int N_STRINGS = 1000;
		String string = "ABC";
		String[] strings = new String[N_STRINGS];	
		Arrays.fill(strings, string);

		JoinStringsPerfomanceTest joinStringBuinderTest	= new JoinStringsPerfomanceTest("Join StringBuinder Test", N_STRINGS, strings, new JoinStringsBuilderImpl());
		joinStringBuinderTest.run();  
		JoinStringsPerfomanceTest joinStringTest = new JoinStringsPerfomanceTest("Join String Test", N_STRINGS, strings, new JoinStringsImpl());  
		joinStringTest.run();
		
	}
	
}
