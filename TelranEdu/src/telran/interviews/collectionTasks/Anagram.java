package telran.interviews.collectionTasks;

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
		//mapWord - contains chars int of word
		//key - int char
		//value - amount of this char in the word  
		HashMap<Integer, Integer> mapWord = new HashMap<>();
		//get old value and add new value 
		word.chars().forEach(i -> mapWord.merge(i, 1, (a, b) -> a + b));
		
		//for each by chars of anagram
		//get value from mapWord by key, and reduce value by 1, 
		//if value already 1 => remove 
		//if there is not key => word does not contain char from anagram 
		//(or contains in smaller quantities)
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
		//if there is at least one value in mapWord after for each by chars of anagram
		//=> word contains more chars then anagram
		return result ? mapWord.isEmpty() : false;
		
	}

	


}
