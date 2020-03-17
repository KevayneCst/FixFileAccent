package core.grammar;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private static final char UNKNOWCHAR = '�';

	public static char getUnknowchar() {
		return UNKNOWCHAR;
	}

	private String word;

	public Word(String s) {
		this.word = s;
	}

	/**
	 * Trouve les caractères correspondant à un symbole inconnu, et range dans une
	 * liste d'indice du caractère inconnu
	 * 
	 * @return la liste d'indices de caractères inconnus
	 */
	public List<Integer> findUnknowChar() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == UNKNOWCHAR) {
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

	@Override
	public String toString() {
		return "Word [word=" + word + "]";
	}
}
