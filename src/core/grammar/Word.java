package core.grammar;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private String word;
	private static final char UNKNOWCHAR = '�';
	
	public Word(String s) {
		this.word=s;
	}
	
	/**
	 * Trouve les caractères correspondant à un symbole inconnu, et range dans une liste d'indice du caractère inconnu
	 * @return la liste d'indices de caractères inconnus
	 */
	public List<Integer> findUnknowChar() {
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<word.length(); i++) {
			if (word.charAt(i)==UNKNOWCHAR) {
				list.add(i);
			}
		}
		System.out.println("J'ai trouvé "+list.size()+" unknow char");
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
	
	@Override
	public String toString() {
		return "Word [word=" + word + "]";
	}
}
