package core.grammar;

/**
 * Classe définissant un mot contenant un ou plusieurs caractères corrompus,
 * elle permet de savoir où ils sont dans le mot.
 * 
 * @author Kévin Constantin
 *
 */
public abstract class Word {

	public static final char UNKNOWCHAR = '�';
	private String theWord;

	public Word(String s) {
		this.theWord = s;
	}
	
	public String getTheWord() {
		return theWord;
	}

	@Override
	public String toString() {
		return "Word [word=" + theWord + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theWord == null) ? 0 : theWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Word) {
			Word other = (Word) obj;
			return this.theWord.equalsIgnoreCase(other.theWord);
		} 
		return false;
	}
}
