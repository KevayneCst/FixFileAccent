package core.grammar;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import core.log.Log;
import core.log.TypeLog;

/**
 * Classe permettant de faire différentes opération sur un chaîne de caractère
 * considéré comme une phrase (qu'elle contienne uniquement du code ou pas) et
 * facilitant la correction de cette phrase (si cela s'avère être nécessaire)
 * pour la classe <code>Word</code>.
 * 
 * @author Kévin Constantin
 *
 */
public class Sentence {

	private String theLine;

	public Sentence(String s) {
		this.theLine = s;
	}

	/**
	 * Remplace par l'ensemble des mots de la liste dans <code>theLine</code>.
	 * 
	 * @see replaceWord(Word w)
	 * @param listOfWords
	 */
	public void replaceWords(List<WordCorrupted> listOfWords) {
		for (WordCorrupted w : listOfWords) {
			replaceWord(w);
		}
	}

	/**
	 * Remplace par le mot <code>w</code> dans <code>theLine</code>, de l'indice
	 * <code>w.getIndexBeginInSentence()</code> à
	 * <code>w.getIndexEndInSentence()</code>, en remplaçant par
	 * <code>w.getTheWords()</code>.
	 * 
	 * @param w
	 */
	private void replaceWord(WordCorrupted w) {
		StringBuilder sb = new StringBuilder(theLine);
		sb.replace(w.getIndexBeginInSentence(), w.getIndexEndInSentence(), w.getTheWord());
		theLine = sb.toString();
	}

	/**
	 * A partir de <code>theLine</code>, va renvoyer la liste des mots qui sont
	 * corrompus en extryant précisémment le mot sans déborder sur d'autre mots.
	 * 
	 * @return
	 */
	public List<WordCorrupted> extractCorruptedWords() {
		Set<WordCorrupted> corruptedWords = new LinkedHashSet<>();
		List<Integer> corruptedCharIndexe = findUnknowChar();

		for (Integer i : corruptedCharIndexe) {
			WordCorrupted w = extractWord(i);
			if (w == null) {
				Log.printLog("Erreur inattendue lors de l'extraction, ne devrait jamais arriver...", TypeLog.CRITICAL);
			} else {
				corruptedWords.add(w);
			}
		}
		List<WordCorrupted> convertedSet = new ArrayList<>();
		convertedSet.addAll(corruptedWords);
		return convertedSet;
	}

	/**
	 * Fonction qui va rechercher et extraire, à partir d'un indice de caractère
	 * corrompu, le mot dans lequel se trouve se caractère corrompu.
	 * 
	 * @param indexOfCorruptedChars
	 * @return
	 */
	private WordCorrupted extractWord(int indexOfCorruptedChars) {
		int lengthTheLine = theLine.length();
		int lastIndexLeftSide = indexOfCorruptedChars - 1;
		int beginIndexRightSide = indexOfCorruptedChars + 1;

		int indexToCutBegin;
		int indexToCutEnd;

		if (Regex.isPuncOrSpace(theLine, indexOfCorruptedChars)) {
			return null;
		}

		if (lastIndexLeftSide < 0) {
			indexToCutBegin = indexOfCorruptedChars;
		} else {
			indexToCutBegin = countLeftSide(indexOfCorruptedChars);
		}

		if (beginIndexRightSide >= lengthTheLine) {
			indexToCutEnd = indexOfCorruptedChars;
		} else {
			indexToCutEnd = beginIndexRightSide + countRightSide(beginIndexRightSide, lengthTheLine);
		}
		String extracted = theLine.substring(indexToCutBegin, indexToCutEnd);
		return new WordCorrupted(extracted, indexToCutBegin, indexToCutEnd);
	}

	private int countLeftSide(int indexOfCorruptedChars) {
		int indexToCutBegin;
		StringBuilder sb = new StringBuilder(theLine.substring(0, indexOfCorruptedChars)).reverse();
		int count = 0;
		int lengthSb = sb.length();
		for (int i = 0; i < lengthSb; i++) {
			if (Regex.isPuncOrSpace(sb.toString(), i)) {
				break;
			}
			count++;
		}
		indexToCutBegin = lengthSb - count;
		return indexToCutBegin;
	}

	private int countRightSide(int begin, int end) {
		StringBuilder sb = new StringBuilder(theLine.substring(begin, end));
		int count = 0;
		int lengthSb = sb.length();
		for (int i = 0; i < lengthSb; i++) {
			if (Regex.isPuncOrSpace(sb.toString(), i)) {
				break;
			}
			count++;
		}
		return count;
	}

	/**
	 * La phrase contient-elle des caractères inconnus ?
	 * 
	 * @return <code>True</code> oui<br>
	 *         <code>False</code> non
	 */
	public boolean needCorrection() {
		return theLine.contains(Word.UNKNOWCHAR + "");
	}

	/**
	 * Compte le nombre de caractère inconnus dans la phrase
	 * 
	 * @return <code>int</code>
	 */
	public int countUnknowChar() {
		if (!needCorrection())
			return 0;
		int count = 0;
		for (int i = 0; i < theLine.length(); i++) {
			if (theLine.charAt(i) == Word.UNKNOWCHAR) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Trouve les caractères correspondant à un symbole inconnu, et range dans une
	 * liste d'indice du caractère inconnu
	 * 
	 * @return la liste d'indices de caractères inconnus
	 */
	public List<Integer> findUnknowChar() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < theLine.length(); i++) {
			if (theLine.charAt(i) == Word.UNKNOWCHAR) {
				list.add(i);
			}
		}
		return list;
	}

	public String getTheLine() {
		return theLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theLine == null) ? 0 : theLine.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sentence) {
			Sentence other = (Sentence) obj;
			return other.theLine.equals(this.theLine);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Sentence [" + theLine + "]";
	}
}
