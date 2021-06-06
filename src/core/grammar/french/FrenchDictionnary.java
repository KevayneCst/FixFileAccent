package core.grammar.french;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import core.grammar.Dictionnary;
import core.grammar.WordDictionnary;
import core.log.Log;
import core.log.TypeLog;

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
		final long startTime = System.currentTimeMillis();
		final File fichier = new File(PATHFILEDICTIONNARY);
		try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
			String line;
			while ((line = br.readLine()) != null) {
				final String before = line;
				line = line.trim();
				if (!line.isEmpty()) {
					final WordDictionnary newEntry = new WordDictionnary(before);
					super.getRawDico().add(newEntry);
					super.getDico().computeIfAbsent(before.length(), k -> new ArrayList<>()).add(newEntry);
				}
			}
			Log.printLog("Lecture du dictionnaire \"" + PATHFILEDICTIONNARY + "\" en "
					+ (System.currentTimeMillis() - startTime) + " ms", TypeLog.DEBUGGING);
		} catch (final Exception e) {
			Log.printLog("Erreur lors de la lecture du fichier: \"" + PATHFILEDICTIONNARY + "\"", TypeLog.CRITICAL);
		}
	}
}
