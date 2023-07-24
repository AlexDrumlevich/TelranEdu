package telran.interviews.collectionTasks.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.collectionTasks.Words;

import static org.junit.jupiter.api.Assertions.*;

class WordsTests {

	String words[]= {"abcdef","ab123","aaa","ab","ablmn","abbbb",
			"a", "ABd","bbb", "B12", "*/", "-1-754", "werÿtr", "werÿtr"};
	String wordsStartB[] = {"B12", "bbb"};
	String wordsStartAB[] = {"ab","ab123","abbbb","abcdef","ABd","ablmn"};
	String wordsStartABC[] = {"abcdef"};
	String wordsStartAsteric[] = {"*/"};
	String wordsStartMinus[] = {"-1-754"};
	String wordsStartContainsLastChar[] = {"werÿtr"};
	Words elasticSearch;
		
	@BeforeEach
		void setUp() throws Exception {
			elasticSearch = new Words();
			for(String word: words) {
				elasticSearch.addWord(word);
			}
				
				
		}

		@Test
		void test() {
			assertArrayEquals(wordsStartABC,
					elasticSearch.getWordsByPrefix("abc").toArray(String[]::new));
			assertArrayEquals(wordsStartB, elasticSearch.getWordsByPrefix("B").toArray(String[]::new));
			assertArrayEquals(wordsStartAB, elasticSearch.getWordsByPrefix("ab").toArray(String[]::new));
			assertArrayEquals(wordsStartAsteric, elasticSearch.getWordsByPrefix("*").toArray(String[]::new));
			assertArrayEquals(wordsStartMinus, elasticSearch.getWordsByPrefix("-1-").toArray(String[]::new));
			assertArrayEquals(wordsStartContainsLastChar, elasticSearch.getWordsByPrefix("werÿ").toArray(String[]::new));
		}


}
