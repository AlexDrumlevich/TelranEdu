package telran.interviews.collectionTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


public class Words {
	
	private TreeSet<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	/**
	 * adds word
	 * @param word
	 * @return true if added, otherwise false if an word already exists (case insensitive)
	 */
	public boolean addWord(String word) {
		return words.add(word); 
	}
	/**
	 * 
	 * @param prefix
	 * @return list of words starting from a given prefix (case insensitive)
	 */
	public List<String> getWordsByPrefix(String prefix) {
		SortedSet<String> subset = 
				words.subSet(prefix, getPrefixLimit(prefix));
		System.out.println(prefix + "  - "  + getPrefixLimit(prefix));
		return Arrays.asList(subset.toArray(new String[0]));
	}
	
	private String getPrefixLimit(String prefix) {
		char lastChar = prefix.charAt(prefix.length() - 1);
		char limitChar = (char) (lastChar + 1);
		//0 inclusive, prefix.length() - 1 exclusive (get rid of last char and add limitChar 
		return prefix.substring(0, prefix.length() - 1) + limitChar;
		//abc  - abd
		//B  - C
		//ab  - ac
	}
	
}
