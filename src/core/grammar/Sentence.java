package core.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

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
	private List<Word> words;
	private List<Map<Integer, Character>> specificCharDeleted;
	private List<Word> purifiedWords;

	@SuppressWarnings("unchecked")
	public Sentence(String s) {
		this.theLine = s;
		this.words = spaceSplitSentence();
		this.specificCharDeleted = new ArrayList<>();
		this.purifiedWords = new ArrayList<>();
		for (Word w : words) {
			purifiedWords.add(new Word((String) w.purifyWord()[0]));
			specificCharDeleted.add((Map<Integer, Character>) w.purifyWord()[1]);
		}
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

	/**
	 * Regénère la phrase à partir des mots corrigés et purifiés pour replacer les
	 * caractères spéciaux là où ils étaient dans le mot et donc dans la phrase
	 * 
	 * @param purifiedAndCorrectedWords
	 * @return
	 */
	public Sentence rebuildSentence(List<Word> purifiedAndCorrectedWords) {
		StringBuilder sb = new StringBuilder();
		int lastIndex = purifiedWords.size() - 1;
		for (int i = 0; i < purifiedAndCorrectedWords.size(); i++) {
			Word w = rebuildWord(i,purifiedAndCorrectedWords);
			if (i == lastIndex) {
				sb.append(w.getTheWord());
			} else {
				sb.append(w.getTheWord() + " ");
			}
		}
		return new Sentence(sb.toString());
	}

	public Word rebuildWord(int index,List<Word> purifiedAndCorrectedWords) {
		StringBuilder sb = new StringBuilder(purifiedAndCorrectedWords.get(index).getTheWord());
		for (Map.Entry<Integer, Character> map : specificCharDeleted.get(index).entrySet()) {
			sb.insert(map.getKey(), map.getValue() + "");
		}
		return new Word(sb.toString());
	}

	public void setTheLine(String theLine) {
		this.theLine = theLine;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public List<Map<Integer, Character>> getSpecificCharDeleted() {
		return specificCharDeleted;
	}

	public void setSpecificCharDeleted(List<Map<Integer, Character>> specificCharDeleted) {
		this.specificCharDeleted = specificCharDeleted;
	}

	public List<Word> getPurifiedWords() {
		return purifiedWords;
	}

	public void setPurifiedWords(List<Word> purifiedWords) {
		this.purifiedWords = purifiedWords;
	}

	@Override
	public String toString() {
		return "Sentence [" + theLine + ", words=" + words + "]";
	}
}
