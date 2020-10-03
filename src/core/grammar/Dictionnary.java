package core.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Classe abstraite dont le but est d'être dérivée par de nouvelles classes
 * définissant de nouveau dictionnaires dans différentes langues utilisant des
 * accents tel que les français les utilisent.
 * 
 * @author Kévin Constantin
 *
 */
public abstract class Dictionnary {

	private List<WordDictionnary> rawDico;
	private Map<Integer, List<WordDictionnary>> dico;
	private Random r;

	public Dictionnary() {
		rawDico = new ArrayList<>();
		dico = new HashMap<>();
		r = new Random();
		fillDictionnary();
	}

	public abstract void fillDictionnary();
	
	public WordDictionnary getRandomWord() {
		return rawDico.get(r.nextInt(rawDico.size()));
	}
	
	public WordDictionnary findWord(String wordToFind) {
		List<WordDictionnary> list = dico.get(wordToFind.length());
		int indexWord = list.indexOf(new WordDictionnary(wordToFind));
		return indexWord == -1 ? null : list.get(indexWord);
	}
	
	public List<WordDictionnary> getRawDico() {
		return rawDico;
	}

	public Map<Integer, List<WordDictionnary>> getDico() {
		return dico;
	}

}
