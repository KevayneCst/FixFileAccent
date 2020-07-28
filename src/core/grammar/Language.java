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
	private Map<Word, Word> savedCorrections;

	public Language(Dictionnary d) {
		dictionnary = d;
		savedCorrections = new HashMap<>();
	}

	public abstract List<Word> correctSentence(Sentence toCorrect);

	public abstract Word matchWordWithDictionnary(Word w);
	
	public Word checkIfWordAlreadyCorrected(Word w) {
		return savedCorrections.containsKey(w) ? savedCorrections.get(w) : w;
	}

	public Dictionnary getDictionnary() {
		return dictionnary;
	}
	
	public Map<Word, Word> getSavedCorrections() {
		return savedCorrections;
	}
}
