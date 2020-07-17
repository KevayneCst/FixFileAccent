package core.grammar;

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

	private Map<Integer, List<Word>> dico;
	private Random r;

	public Dictionnary() {
		dico = new HashMap<>();
		r = new Random();
		fillDictionnary();
	}

	public abstract void fillDictionnary();
	
	public Word getRandomWord() {
		Object[] values = dico.values().toArray();
		@SuppressWarnings("unchecked")
		List<Word> listWords = (List<Word>) values[r.nextInt(values.length)];
		return listWords.get(r.nextInt(listWords.size()));
	}

	public Map<Integer, List<Word>> getDico() {
		return dico;
	}

}
