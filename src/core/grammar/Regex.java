package core.grammar;

/**
 * Classe utilitaire où je stock mes expressions régulières pour les retrouver
 * plus facilement, et factoriser le code.
 * 
 * @author Kévin Constantin
 *
 */
public class Regex {

	public static final String REGEX_ONLY_LETTERS = "[a-zA-Z]+";
	public static final String REGEX_ONLY_DIGITS = "[0-9]+";
	public static final String REGEX_SPACE = "[[ ]*]+";
	public static final String REGEX_NO_LETTERS_AND_DIGITS = "[^A-Za-z0-9]+";
	public static final String REGEX_LETTERS_DASH_LETTERS = "[a-zA-Z]+[-][a-zA-Z]+";
	public static final String REGEX_LETTERS_APOSTROPHE_LETTERS = "[a-zA-Z]+['][a-zA-Z]+";
	public static final String REGEX_LETTERS_ACCENT_LETTERS = "[a-zA-Z]*[\\p{L}*]*[a-zA-Z]*";
	public static final String REGEX_PUNCTUATION = "\\p{Punct}";
	
	private Regex() {
	}
}
