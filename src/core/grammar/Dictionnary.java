package core.grammar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Dictionnary() {
		dico = new HashMap<>();
		fillDictionnary();
	}

	public abstract void fillDictionnary();

	public Map<Integer, List<Word>> getDico() {
		return dico;
	}

}
