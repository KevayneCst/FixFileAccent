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
	
	/**
	 * Purifie l'attribut de la classe <code>(String) theWord</code> en enlevant tous les caractères n'étant pas une lettre.
	 * @param w
	 * @return une tableau de taille 2 avec :<br>
	 * -indice [0] le mot purifié<br>
	 * -indice [1] une map contenant l'indice et le caractère supprimé (qui n'est pas une lettre)
	 */
	public Object[] purifyWord() {
		Object[] obj = new Object[2];
		Map<Integer,Character> deletedCharacters = new TreeMap<>();
		
		String regexOnlyLetters = Regex.REGEX_ONLY_LETTERS;
		String regexOnlyDigits = Regex.REGEX_ONLY_DIGITS;
		String regexNoLettersAndDigit = Regex.REGEX_NO_LETTERS_AND_DIGITS;
		String regexLettersSubstractLetters = Regex.REGEX_LETTERS_DASH_LETTERS;
		String regexLettersApostrLetters = Regex.REGEX_LETTERS_APOSTROPHE_LETTERS;
		String regexLettersAccentLetters = Regex.REGEX_LETTERS_ACCENT_LETTERS;
		String regexSpecificChar = Regex.REGEX_SPECIFIC_CHAR;
		
		boolean forced = false;
		if (theWord.contains(Word.UNKNOWCHAR+"")) {
			String tmp = theWord.replaceAll(Word.UNKNOWCHAR+"", "e");
			if (tmp.matches(regexOnlyLetters) || tmp.matches(regexNoLettersAndDigit) || tmp.matches(regexLettersSubstractLetters) || tmp.matches(regexLettersApostrLetters) || tmp.matches(regexLettersAccentLetters)) {
				forced = true;
			}
		}
				
		if ((theWord.matches(regexOnlyLetters) || theWord.matches(regexNoLettersAndDigit) || theWord.matches(regexOnlyDigits) || forced)) {
			obj[0] = theWord;
			obj[1] = deletedCharacters;
		} else {
			for (int i = 0; i<theWord.length(); i++) {
				int k = i+1;
				if (theWord.substring(i, k).matches(regexSpecificChar)) {
					deletedCharacters.put(i, theWord.charAt(i));
				}
			}
			obj[0] = theWord.replaceAll(regexSpecificChar, "");
			obj[1] = deletedCharacters;
		}
		return obj;
	}

	public String getTheWord() {
		return theWord;
	}

	public void setTheWord(String word) {
		this.theWord = word;
	}

	@Override
	public String toString() {
		return "Word [word=" + theWord + "]";
	}
}
