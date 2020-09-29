package core.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe définissant un mot contenant un ou plusieurs caractères corrompus,
 * elle permet de savoir où ils sont dans le mot.
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
	private int indexBeginInSentence;
	private int indexEndInSentence;

	public Word(String s) {
		this.theWord = s;
		this.indexBeginInSentence = -1;
		this.indexEndInSentence = -1;
	}

	public Word(String s, int indexBeginInSentence, int indexEndInSentence) {
		this.theWord = s;
		this.indexBeginInSentence = indexBeginInSentence;
		this.indexEndInSentence = indexEndInSentence;
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

	public int getIndexBeginInSentence() {
		return indexBeginInSentence;
	}

	public int getIndexEndInSentence() {
		return indexEndInSentence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theWord == null) ? 0 : theWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Word) {
			Word other = (Word) o;
			if (other.indexBeginInSentence == -1 && this.indexBeginInSentence == -1) {
				return other.indexEndInSentence == -1 && this.indexEndInSentence == -1;
			} else {
				return other.theWord.equals(this.theWord) && other.indexBeginInSentence == this.indexBeginInSentence
						&& other.indexEndInSentence == this.indexEndInSentence;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Word [word=" + theWord + "]";
	}
}
