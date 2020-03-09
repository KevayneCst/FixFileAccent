package grammar;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private String word;
	private static final char UNKNOWCHAR = '�';
	
	public Word(String s) {
		this.word=s;
	}
	
	public List<Integer> findUnknowChar() {
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<word.length(); i++) {
			if (word.charAt(i)==UNKNOWCHAR) {
				list.add(i);
			}
		}
		return list;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public static char getUnknowchar() {
		return UNKNOWCHAR;
	}
}
