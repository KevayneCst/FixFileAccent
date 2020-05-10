package core.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * Divise la phrase avec le séparateur "espace" pour donner une liste de
	 * mots.<br>
	 * Exemple: la phrase "bonjour je suis une phrase" donnera une liste de 5 mots,
	 * "bonjour", "je", "suis", "une", "phrase"
	 * 
	 * @return la liste des mots composant la phrase
	 */
	public List<Word> spaceSplitSentence() {
		List<Word> list = new ArrayList<>();
		String[] wordsSplit = theLine.split(Regex.REGEX_SPACE);
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
			Word w = rebuildWord(i, purifiedAndCorrectedWords);
			if (i == lastIndex) {
				sb.append(w.getTheWord());
			} else {
				sb.append(w.getTheWord() + " ");
			}
		}
		return new Sentence(sb.toString());
	}

	/**
	 * Replace les caractères spéciaux manquants d'un mot à l'emplacement où ils
	 * étaient avant la purification du mot (le fait de supprimer les caractères
	 * spéciaux et de ne laisser que des lettres pour former un vrai mot exploitable
	 * 
	 * @param index                     indice du mot à reformer
	 * @param purifiedAndCorrectedWords la liste des mots purifés
	 * @return le mot à son état d'origine + la correction des accents manquants
	 *         s'il y en avait
	 */
	public Word rebuildWord(int index, List<Word> purifiedAndCorrectedWords) {
		StringBuilder sb = new StringBuilder(purifiedAndCorrectedWords.get(index).getTheWord());
		for (Map.Entry<Integer, Character> map : specificCharDeleted.get(index).entrySet()) {
			sb.insert(map.getKey(), map.getValue() + "");
		}
		return new Word(sb.toString());
	}

	public String getTheLine() {
		return theLine;
	}

	public List<Word> getWords() {
		return words;
	}

	public List<Map<Integer, Character>> getSpecificCharDeleted() {
		return specificCharDeleted;
	}

	public List<Word> getPurifiedWords() {
		return purifiedWords;
	}

	@Override
	public String toString() {
		return "Sentence [" + theLine + ", words=" + words + "]";
	}
}
