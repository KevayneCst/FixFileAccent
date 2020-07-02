package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Language;
import core.grammar.Sentence;
import core.grammar.UnknowLanguageException;
import core.log.LevelLog;
import core.log.Log;
import core.log.TypeLog;
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

	public static final LevelLog level = Config.getInstance().getLevelLog();
	private Creater c;
	private Reader r;
	private Finder f;
	private Language lang;

	public Core(String pathDirectory) throws UnknowLanguageException {
		c = new Creater();
		r = new Reader();
		f = new Finder(pathDirectory);
		lang = Config.getInstance().getLanguage();
		c.makeSave(pathDirectory); // TODO log sauvegarde effectué du répertoire d'entrée
	}

	private void putIntoMap(Map<String, List<Sentence>> map, String key, Sentence toAdd) {
		map.computeIfAbsent(key, k -> new ArrayList<>()).add(toAdd);
	}

	public void start() {
		List<String> listPath = f.getPathFiles();
		Map<String, List<Sentence>> firstReading = new HashMap<>();
		Map<String, List<Sentence>> afterCorrection = new HashMap<>();

		Log.printLog("Étape 1: Lecture et sauvegarde de toutes les lignes de tous les fichiers", TypeLog.INFO);
		for (String s : listPath) {
			firstReading.put(s, r.readFile(s));
		}

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

		if (Config.getInstance().isApplyCorrection()) {
			Log.printLog("Étape 3: Réécriture sur tous les fichiers qui ont eu besoin de correction", TypeLog.INFO);
			for (Map.Entry<String, List<Sentence>> hm : afterCorrection.entrySet()) {
				c.writeFile(hm.getKey(), hm.getValue());
			}
		}
	}
}
