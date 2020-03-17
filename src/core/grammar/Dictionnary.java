package core.grammar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Dictionnary {

	private Map<Integer, List<Word>> dico;
	
	public Dictionnary() {
		dico = new HashMap<>();
		fillDictionnary();
	}

	public abstract void fillDictionnary();
	
	public Map<Integer, List<Word>> getDico() {
		return dico;
	}
	
}
