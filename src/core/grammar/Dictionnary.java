package core.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import core.grammar.french.FrenchDictionnary;

/**
 * Classe abstraite dont le but est d'être dérivée par de nouvelles classes
 * définissant de nouveau dictionnaires dans différentes langues utilisant des
 * accents tel que les français les utilisent.
 * 
 * @author Kévin Constantin
 *
 */
public abstract class Dictionnary {

	private List<Word> rawDico;
	private Map<Integer, List<Word>> dico;
	private Random r;

	public Dictionnary() {
		rawDico = new ArrayList<>();
		dico = new HashMap<>();
		r = new Random();
		fillDictionnary();
	}

	public abstract void fillDictionnary();
	
	public Word getRandomWord() {
		return rawDico.get(r.nextInt(rawDico.size()));
	}
	
	public List<Word> getRawDico() {
		return rawDico;
	}

	public Map<Integer, List<Word>> getDico() {
		return dico;
	}

}
