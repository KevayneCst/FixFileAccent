package core.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe définissant un mot, elle permet notamment de vérifier si ce mot
 * contient des accents corrompu, et surtout de savoir où sont-ils dans le mot.
 * 
 * @author Kévin Constantin
 *
 */
public class Word {

	public static final char UNKNOWCHAR = '�';

	public static char getUnknowchar() {
		return UNKNOWCHAR;
	}

	private String theWord;

	public Word(String s) {
		this.theWord = s;
	}

	/**
	 * Trouve les caractères correspondant à un symbole inconnu, et range dans une
	 * liste d'indice du caractère inconnu
	 * 
	 * @return la liste d'indices de caractères inconnus
	 */
	public List<Integer> findUnknowChar() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < theWord.length(); i++) {
			if (theWord.charAt(i) == UNKNOWCHAR) {
				list.add(i);
			}
		}
		return list;
	}

	public String getTheWord() {
		return theWord;
	}

	@Override
	public String toString() {
		return "Word [word=" + theWord + "]";
	}
	
	public String debugString() {
		return "["+ theWord +"]";
	}
	
	public static String debugStringList(List<Word> listWord) {
		StringBuilder sb = new StringBuilder();
		for (Word w : listWord) {
			sb.append(w.debugString() + " ");
		}
		return sb.toString();
	}
}
