package telran.interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Anagram {
/**
 * 
 * @param word
 * @param anagram
 * @return true if anagram is one of the possible anagrams of a given word
 * anagram is a string containing all symbols from a given word with different order
 * Example: yellow (wolely, lowlye, yellow) , wrong anagrams (yello, yelllw)
 */
	public static boolean isAnagram(String word, String anagram) {
		//TODO
		boolean result = true;
		HashMap<Integer, Integer> mapWord = new HashMap<>();
		word.chars().forEach(i -> mapWord.merge(i, 1, (a, b) -> a + b));
		
		for(int charInt : anagram.chars().toArray()) {
			Integer value = mapWord.get(charInt);
			if(value == null) {
				result = false;
				break;
			} else if(value == 1) {
				mapWord.remove(charInt);
			} else {
				mapWord.merge(charInt, 1, (a, b) -> a - b);
			}
		}
		
		return result ? mapWord.isEmpty() : false;
		
	}

	


}
