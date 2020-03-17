package core.grammar;

import java.util.List;

public abstract class Language {

	private Dictionnary dictionnary;
	
	public Language(Dictionnary d) {
		dictionnary=d;
	}
	
	public abstract Sentence correctSentence(List<Word> listWord);
	
	public abstract Word matchWordWithDictionnary(Word w);

	public Dictionnary getDictionnary() {
		return dictionnary;
	}
}
