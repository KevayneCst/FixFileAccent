package core.grammar;

import java.util.List;

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

	public Language(Dictionnary d) {
		dictionnary = d;
	}

	public abstract List<Word> correctSentence(Sentence toCorrect);

	public abstract Word matchWordWithDictionnary(Word w);

	public Dictionnary getDictionnary() {
		return dictionnary;
	}
}
