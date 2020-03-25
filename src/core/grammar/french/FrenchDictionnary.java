package core.grammar.french;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import core.grammar.Dictionnary;
import core.grammar.Word;

/**
 * Classe concrète étendant <code>Dictionnary</code>, elle indique notamment
 * l'emplacement du fichier dictionnaire, et implémente la façon de lire se
 * fichier.
 * 
 * @author Kévin Constantin
 *
 */
public class FrenchDictionnary extends Dictionnary {

	private static final String PATHFILEDICTIONNARY = "data/FrenchDico.in";

	public FrenchDictionnary() {
		super();
	}

	@Override
	public void fillDictionnary() {
		File fichier = new File(PATHFILEDICTIONNARY);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fichier));
			String line;
			while ((line = br.readLine()) != null) {
				String before = line;
				line = line.trim();
				if (!line.isEmpty()) {
					super.getDico().computeIfAbsent(before.length(), k -> new ArrayList<>()).add(new Word(before));
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture du fichier: \"" + PATHFILEDICTIONNARY + "\"");
		}
	}
}
