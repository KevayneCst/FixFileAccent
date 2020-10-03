package core.grammar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe abstraite dont le seul but est d'être dérivée par des classes
 * concrètes implémentant de nouvelles langues. Chaque nouveau language X doit
 * posséder son propre dictionnaire de mots en langue X.
 * 
 * @author Kévin Constantin
 *
 */
public abstract class Language {

	private Dictionnary dictionnary;
	private Map<WordCorrupted, WordDictionnary> savedCorrections;

	public Language(Dictionnary d) {
		dictionnary = d;
		savedCorrections = new HashMap<>();
	}

	public abstract Sentence correctSentence(Sentence toCorrect);

	public abstract WordCorrupted matchWordWithDictionnary(WordCorrupted w);

	/**
	 * Vérifie que le mot passé en paramètre <code>w</code> n'ait pas déjà été
	 * corrigé. S'il l'a déjà été, alors la fonction renverra le mot corrigé, sinon
	 * elle renverra <code>w</code>.
	 * 
	 * @param w Le mot corrompu dont on veut savoir s'il a déjà été corrigé
	 * @return Version corrigée de <code>(WordDictionnary)w</code> OU non corrigée <code>(WordCorrupted)w</code>
	 */
	public Word checkIfWordAlreadyCorrected(WordCorrupted w) {
		return customContainsKey(w) ? customGet(w) : w;
	}
	
	private boolean customContainsKey(WordCorrupted w) {
		for (Map.Entry<WordCorrupted, WordDictionnary> map : savedCorrections.entrySet()) {
			if (map.getKey().getTheWord().equals(w.getTheWord())) {
				return true;
			}
		}
		return false;
	}
	
	private WordDictionnary customGet(WordCorrupted w) {
		for (Map.Entry<WordCorrupted, WordDictionnary> map : savedCorrections.entrySet()) {
			if (map.getKey().getTheWord().equals(w.getTheWord())) {
				return map.getValue();
			}
		}
		return null; 
	}

	public Dictionnary getDictionnary() {
		return dictionnary;
	}

	public Map<WordCorrupted, WordDictionnary> getSavedCorrections() {
		return savedCorrections;
	}
}
