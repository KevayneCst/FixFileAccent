package core.grammar;

import java.util.ArrayList;
import java.util.List;

public class WordCorrupted extends Word {

	private int indexBeginInSentence;
	private int indexEndInSentence;

	/**
	 * /!\ Constructeur pour les tests /!\
	 * @param s
	 * @param indexBeginInSentence
	 * @param indexEndInSentence
	 */
	public WordCorrupted(String s) {
		super(s);
		this.indexBeginInSentence = -1;
		this.indexEndInSentence = -1;
	}
	
	public WordCorrupted(String s, int indexBeginInSentence, int indexEndInSentence) {
		super(s);
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
		for (int i = 0; i < super.getTheWord().length(); i++) { // TODO calculer la length avant la boucle
			if (super.getTheWord().charAt(i) == UNKNOWCHAR) {
				list.add(i);
			}
		}
		return list;
	}
	
	public WordCorrupted convert(WordDictionnary wd) {
		return new WordCorrupted(wd.getTheWord(), indexBeginInSentence, indexEndInSentence);
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
		int result = super.hashCode();
		result = prime * result + indexBeginInSentence;
		result = prime * result + indexEndInSentence;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		} else if (obj instanceof WordCorrupted) {
			WordCorrupted other = (WordCorrupted) obj;
			return this.indexBeginInSentence == other.indexBeginInSentence
					&& this.indexEndInSentence == other.indexEndInSentence;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WordCorrupted [");
		builder.append(super.toString());
		builder.append(", indexBeginInSentence=");
		builder.append(indexBeginInSentence);
		builder.append(", indexEndInSentence=");
		builder.append(indexEndInSentence);
		builder.append("]");
		return builder.toString();
	}
}