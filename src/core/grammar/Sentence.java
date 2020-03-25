package core.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de faire différentes opération sur un chaîne de caractère
 * considéré comme une phrase (qu'elle contienne uniquement du code oui pas) et
 * facilitant la correction de cette phrase (ci cela s'avère être nécessaire)
 * pour la classe <code>Word</code>.
 * 
 * @author Kévin Constantin
 *
 */
public class Sentence {

	private String theLine;
	private List<Word> words;

	public Sentence(String s) {
		this.theLine = s;
		this.words = sentenceIntoWords();
	}

	public String getTheLine() {
		return theLine;
	}

	public List<Word> getWords() {
		return words;
	}

	public boolean needCorrection() {
		return theLine.contains(Word.UNKNOWCHAR + "");
	}

	public List<Word> spaceSplitSentence() {
		List<Word> list = new ArrayList<>();
		String[] wordsSplit = theLine.split("[[ ]*]+");
		for (int i = 0; i < wordsSplit.length; i++) {
			list.add(new Word(wordsSplit[i]));
		}
		return list;
	}

	private List<Word> sentenceIntoWords() {
		List<Word> list = new ArrayList<>();
		String[] wordsSplit = theLine
				.split("[[ ]*|[,]*|[;]*|[:]*|[']*|[’]*|[\\.]*|[:]*|[.]*|[#]*|[$]*|[-]*|[\"]*|[/]*|[!]*|[?]*|[+]*]+");
		for (int i = 0; i < wordsSplit.length; i++) {
			list.add(new Word(wordsSplit[i]));
		}
		return list;
	}

	/**
	 * 
	 * @param original
	 * @return
	 */
	public Sentence rephrase(Sentence original) {
		System.out.println("\nPHRASE D'ORIGINE:\"" + original.getTheLine() + "\"");

		StringBuilder originalSentence = new StringBuilder(original.getTheLine());
		StringBuilder rephrasedSentence = new StringBuilder(theLine);
		rephrasedSentence.setLength(originalSentence.length());

		for (int i = 0; i < originalSentence.length(); i++) {
			System.out.println("POTENTIELLEMENT A CE CARACTERE:\"" + originalSentence.charAt(i) + "\"");
			System.out.println("ORIGINAL EGAL QUOI ??:\"" + rephrasedSentence.charAt(i) + "\"");
			if (rephrasedSentence.charAt(i) == ' ' || rephrasedSentence.charAt(i) == 0) {
				System.out.println(
						"Je remplace le vide à " + i + " par ce caractère:\'" + originalSentence.charAt(i) + "\'");
				rephrasedSentence.setCharAt(i, originalSentence.charAt(i));
			}
		}
		System.out.println("AVANT TRANSFORMA:\"" + theLine + "\"");
		System.out.println("APRES TRANSFORMA:\"" + rephrasedSentence.toString() + "\"");
		return new Sentence(rephrasedSentence.toString());
	}

	public void setTheLine(String theLine) {
		this.theLine = theLine;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "Sentence [" + theLine + ", words=" + words + "]";
	}
}
