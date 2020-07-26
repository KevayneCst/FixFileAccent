package core.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import core.Config;
import core.grammar.french.French;
import core.log.Log;
import core.log.TypeLog;
import core.tools.Tuple;

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
	private List<Boolean> rebuildWithSpace;

	public Sentence(String s) {
		this.theLine = s;
		this.specificCharDeleted = new ArrayList<>();
		this.rebuildWithSpace = new ArrayList<>();
		this.words = splitSentence();
		
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
	 * Compte le nombre de caractère inconnus
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

	public List<Word> splitSentence() {
		List<Word> list = new ArrayList<>();
		String[] spaceSplit = theLine.split(Regex.REGEX_SPACE);
		for (String currentString : spaceSplit) {
			if (!currentString.contains(Word.UNKNOWCHAR + "")) {
				list.add(new Word(currentString));
				specificCharDeleted.add(new HashMap<Integer, Character>());
				rebuildWithSpace.add(true);
			} else {
				String tmp = currentString.replaceAll(Word.UNKNOWCHAR + "", "e");
				if (tmp.matches(Regex.REGEX_LETTERS_APOSTROPHE_LETTERS) || tmp.matches(Regex.REGEX_LETTERS_DASH_LETTERS)
						|| tmp.matches(Regex.REGEX_ONLY_LETTERS)) {
					list.add(new Word(currentString));
					specificCharDeleted.add(new HashMap<Integer, Character>());
					rebuildWithSpace.add(true);
				} else {
					Map<Integer, Character> map = mapOfPuncChar(currentString);
					Tuple<List<Word>, List<Map<Integer, Character>>> tuple = splitPuncWordMap(map,
							currentString);
					list.addAll(tuple.getA());
					specificCharDeleted.addAll(tuple.getB());
				}
			}
		}
		return list;
	}

	private Tuple<List<Word>, List<Map<Integer, Character>>> splitPuncWordMap(
			Map<Integer, Character> mapDeletedPuncWord, String firstSpaceSplitedString) {
		List<Word> list = new ArrayList<>();
		List<Map<Integer, Character>> listOfMap = new ArrayList<>();
		String noPuncChar = firstSpaceSplitedString.replaceAll(Regex.REGEX_PUNCTUATION, " ");
		String[] spaceSplitNoPuncChar = noPuncChar.trim().split(Regex.REGEX_SPACE);

		for (int i = 0; i < spaceSplitNoPuncChar.length; i++) {
			Map<Integer, Character> currentMapOfPuncCharacter = new TreeMap<>();
			List<Integer> toDelete = new ArrayList<>();
			int beforeIndex = 0;
			int afterIndex = spaceSplitNoPuncChar[i].length();
			int previousMapIndex = -1;

			for (Map.Entry<Integer, Character> mapEntry : mapDeletedPuncWord.entrySet()) {
				if (previousMapIndex == -1 || previousMapIndex + 1 == mapEntry.getKey()
						|| (previousMapIndex + 1 + spaceSplitNoPuncChar[i].length() == mapEntry.getKey()
								&& spaceSplitNoPuncChar[i].length() > 1)) {
					previousMapIndex = mapEntry.getKey();
					if (mapEntry.getKey() >= spaceSplitNoPuncChar[i].length()) {
						if (beforeIndex == 0) {
							currentMapOfPuncCharacter.put(afterIndex, mapEntry.getValue());
						} else {
							currentMapOfPuncCharacter.put(beforeIndex + afterIndex, mapEntry.getValue());
						}
						afterIndex++;
					} else {
						currentMapOfPuncCharacter.put(beforeIndex, mapEntry.getValue());
						beforeIndex++;
					}
					toDelete.add(mapEntry.getKey());
				} else {
					break;
				}
			}
			for (Integer in : toDelete) {
				mapDeletedPuncWord.remove(in);
			}
			list.add(new Word(spaceSplitNoPuncChar[i]));
			listOfMap.add(currentMapOfPuncCharacter);
			rebuildWithSpace.add(false);
		}
		return new Tuple<>(list, listOfMap);
	}

	private Map<Integer, Character> mapOfPuncChar(String s) {
		Map<Integer, Character> map = new TreeMap<>();
		for (int i = 0; i < s.length(); i++) {
			int k = i + 1;
			if (s.substring(i, k).matches(Regex.REGEX_PUNCTUATION)) {
				map.put(i, s.charAt(i));
			}
		}
		return map;
	}

	/**
	 * Regénère la phrase à partir des mots corrigés et purifiés pour replacer les
	 * caractères spéciaux là où ils étaient dans le mot et donc dans la phrase
	 * 
	 * @param purifiedAndCorrectedWords
	 * @return
	 */
	public Sentence rebuildSentence(List<Word> purifiedAndCorrectedWords) {
		Log.printLog("Reconstruction de la phrase avec les mots purifiés suivants :"
				+ Word.debugStringList(purifiedAndCorrectedWords), TypeLog.DEBUGGING);
		StringBuilder sb = new StringBuilder();
		int lastIndex = words.size() - 1;
		boolean lastWasWithSpace = true;
		for (int i = 0; i < purifiedAndCorrectedWords.size(); i++) {
			Word w = rebuildWord(i, purifiedAndCorrectedWords);
			if (i == lastIndex) {
				sb.append(w.getTheWord());
			} else {
				if (rebuildWithSpace.get(i)) {
					if (lastWasWithSpace) {
						sb.append(w.getTheWord() + " ");
					} else {
						sb.append(" " + w.getTheWord() + " ");
					}
					lastWasWithSpace = true;
				} else {
					sb.append(w.getTheWord());
					lastWasWithSpace = false;
				}
			}
			Log.printLog("Étape n°" + (i + 1) + " de la reconstruction de la phrase :" + sb.toString(),
					TypeLog.DEBUGGING);
		}
		Log.printLog("Phrase reconstruite :" + sb.toString(), TypeLog.DEBUGGING);
		return new Sentence(sb.toString());
	}

	/**
	 * Replace les caractères spéciaux manquants d'un mot à l'emplacement où ils
	 * étaient avant la purification du mot (le fait de supprimer les caractères
	 * spéciaux et de ne laisser que des lettres pour former un vrai mot
	 * exploitable)
	 * 
	 * @param index                     indice du mot à reformer
	 * @param purifiedAndCorrectedWords la liste des mots purifés
	 * @return le mot à son état d'origine + la correction des accents manquants
	 *         s'il y en avait
	 */
	public Word rebuildWord(int index, List<Word> purifiedAndCorrectedWords) {
		StringBuilder sb = new StringBuilder(purifiedAndCorrectedWords.get(index).getTheWord());
		Log.printLog("Mot purifié avant reconstruction :" + sb.toString(), TypeLog.DEBUGGING);
		int count = 1;
		for (Map.Entry<Integer, Character> map : specificCharDeleted.get(index).entrySet()) {
			sb.insert(map.getKey(), map.getValue() + "");
			Log.printLog("Étape n°" + (count++) + " de reconstruction du mot :" + sb.toString(), TypeLog.DEBUGGING);
		}
		Log.printLog("Mot reconstruit :" + sb.toString(), TypeLog.DEBUGGING);
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

	@Override
	public String toString() {
		return "Sentence [" + theLine + ", words=" + words + "]";
	}
}
