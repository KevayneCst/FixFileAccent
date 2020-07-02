package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Language;
import core.grammar.LanguageFactory;
import core.grammar.Sentence;
import core.grammar.UnknowLanguageException;
import core.log.LevelLog;
import core.log.LevelLogFactory;
import core.log.Log;
import core.log.TypeLog;
import core.log.UnknowLevelLogException;
import core.read.Finder;
import core.read.Reader;
import core.write.Creater;

/**
 * Classe contenant l'algorithme et la logique principale nécessaire au bon
 * fonctionnement de l'application.
 * 
 * @author Kévin Constantin
 *
 */
public class Core {

	public static LevelLog level = LevelLog.QUIET; // Niveau par défaut
	private LanguageFactory lf;
	private Creater c;
	private Reader r;
	private Finder f;
	private Language lang;

	public Core(String pathDirectory, String language) throws UnknowLanguageException {
		init(pathDirectory, language);
		// TODO custom name save
	}

	public Core(String pathDirectory, String language, String levelLog)
			throws UnknowLanguageException, UnknowLevelLogException {
		level = LevelLogFactory.createLevelLog(levelLog);
		init(pathDirectory, language);
		// TODO custom name save
	}
	
	private void init(String pathDirectory, String language) throws UnknowLanguageException {
		lf = new LanguageFactory();
		c = new Creater();
		r = new Reader();
		f = new Finder(pathDirectory);
		lang = lf.createLanguage(language);
		c.makeSave(pathDirectory); // TODO log sauvegarde effectué du répertoire d'entrée
	}

	private void putIntoMap(Map<String, List<Sentence>> map, String key, Sentence toAdd) {
		map.computeIfAbsent(key, k -> new ArrayList<>()).add(toAdd);
	}

	public void start() {
		List<String> listPath = f.getPathFiles();
		Map<String, List<Sentence>> firstReading = new HashMap<>();
		Map<String, List<Sentence>> afterCorrection = new HashMap<>();

		// Step 1: Lecture et sauvegarde de toutes les lignes de tous les fichiers
		Log.printLog("Étape 1: Lecture et sauvegarde de toutes les lignes de tous les fichiers", TypeLog.INFO);
		for (String s : listPath) {
			firstReading.put(s, r.readFile(s));
		}

		// Step 2: Corriger toutes les lignes qui ont besoin d'être corrigé pour tous
		// les fichiers
		Log.printLog("Étape 2: Corriger toutes les lignes qui ont besoin d'être corrigé pour tous les fichiers",
				TypeLog.INFO);
		for (Map.Entry<String, List<Sentence>> hm : firstReading.entrySet()) {
			int ligne = 1;
			Log.printLog("============ Traitement du fichier: "+hm.getKey()+" ============", TypeLog.DEBUGGING);
			for (Sentence st : hm.getValue()) {
				if (st.needCorrection()) {
					Log.printLog("Ligne " + ligne + ", la phrase \"" + st.getTheLine() + "\" a besoin d'être corrigée",
							TypeLog.DEBUGGING);
					putIntoMap(afterCorrection, hm.getKey(), st.rebuildSentence(lang.correctSentence(st)));
				} else {
					Log.printLog("Ligne " + ligne + ", la phrase \"" + st.getTheLine()
							+ "\" n'a pas eu besoin d'être corrigée", TypeLog.DEBUGGING);
					putIntoMap(afterCorrection, hm.getKey(), st);
				}
				ligne++;
			}
		}

		// Step 3: Réécriture sur tous les fichiers qui ont eu besoin de corretion
		Log.printLog("Étape 3: Réécriture sur tous les fichiers qui ont eu besoin de corretion", TypeLog.INFO);
		for (Map.Entry<String, List<Sentence>> hm : afterCorrection.entrySet()) {
			c.writeFile(hm.getKey(), hm.getValue());
		}
	}
}
