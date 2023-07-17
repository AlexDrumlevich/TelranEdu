package telran.interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;


public class Words {
	
	TreeSet<String> innerTreeSet;

	public	Words() {
		innerTreeSet = new TreeSet<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.toLowerCase().compareTo(o2.toLowerCase());
			}
		});
	}
	
	//TODO
	/**
	 * adds word
	 * @param word
	 * @return true if added, otherwise false if an word already exists (case insensitive)
	 */
	public boolean addWord(String word) {
		return innerTreeSet.add(word);
	}
	/**
	 * 
	 * @param prefix
	 * @return list of words starting from a given prefix (case insensitive)
	 */
	public List<String> getWordsByPrefix(String prefix) {
		
		List<String> list = new ArrayList<>();
		
		innerTreeSet.stream().forEach(e -> {
			StringBuilder regexBuilder = new StringBuilder();
			//add each symbol to [] to exclude action of metasymbols
			prefix.toLowerCase().chars().forEach(charInt -> regexBuilder.append("[").append((char) charInt).append("]"));
			regexBuilder.append(".*");
	
			//replace "-" for "\\-" to exclude action of metasymbol "-" inside [ ]
			if(e.toLowerCase().matches(regexBuilder.toString().replaceAll("-", "\\-"))) {
				list.add(e);
			}
		});
		return list;
	}
	
}
