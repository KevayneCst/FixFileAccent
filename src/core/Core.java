package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Language;
import core.grammar.Sentence;
import core.log.LevelLog;
import core.log.Log;
import core.log.TypeLog;
import core.read.Finder;
import core.read.Reader;
import core.tools.Utilities;
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
	private final Creater c;
	private final Reader r;
	private final Finder f;
	private final Language lang;

	public Core(String pathDirectory) {
		c = new Creater();
		r = new Reader();
		f = new Finder(pathDirectory);
		lang = Config.getInstance().getLanguage();
		c.makeSave(pathDirectory);
	}

	private void putIntoMap(Map<String, List<Sentence>> map, String key, Sentence toAdd) {
		map.computeIfAbsent(key, k -> new ArrayList<>()).add(toAdd);
	}

	public void start() {
		Config.getInstance().showProperties();

		final List<String> listPathFiles = f.getPathFiles();
		final Map<String, List<Sentence>> fileSentences = readAndStoreLines(listPathFiles);
		final Map<String, List<Sentence>> fileSentencesCorrected = correctFiles(fileSentences);

		if (Config.getInstance().isConfirmFiles()) {
			Utilities.showFiles(listPathFiles);
			Utilities.waitConfirmationKey();
		}

		if (Config.getInstance().isApplyCorrection()) {
			writeCorrectionOnFiles(fileSentencesCorrected);
		} else {
			Log.printLog("Fin de la simulation", TypeLog.INFO);
		}
	}

	private Map<String, List<Sentence>> readAndStoreLines(List<String> listPathFiles) {
		Log.printLog("Étape 1: Lecture et sauvegarde de toutes les lignes de tous les fichiers", TypeLog.INFO);

		final Map<String, List<Sentence>> fileSentences = new HashMap<>();
		listPathFiles.forEach(path -> fileSentences.put(path, r.readFile(path)));

		return fileSentences;
	}

	private Map<String, List<Sentence>> correctFiles(Map<String, List<Sentence>> fileSentences) {
		final Map<String, List<Sentence>> fileSentencesCorrected = new HashMap<>();
		Log.printLog("Étape 2: Corriger toutes les lignes qui ont besoin d'être corrigé pour tous les fichiers",
				TypeLog.INFO);
		for (final Map.Entry<String, List<Sentence>> hm : fileSentences.entrySet()) {
			int numLigne = 1;
			Log.printLog("============ Traitement du fichier: " + hm.getKey() + " ============", TypeLog.DEBUGGING);
			for (final Sentence sentence : hm.getValue()) {
				if (sentence.needCorrection()) {
					Log.printLog("Ligne " + numLigne + ", la phrase \"" + sentence.getTheLine()
							+ "\" a besoin d'être corrigée", TypeLog.DEBUGGING);
					putIntoMap(fileSentencesCorrected, hm.getKey(), lang.correctSentence(sentence));
				} else {
					Log.printLog("Ligne " + numLigne + ", la phrase \"" + sentence.getTheLine()
							+ "\" n'a pas eu besoin d'être corrigée", TypeLog.DEBUGGING);
					putIntoMap(fileSentencesCorrected, hm.getKey(), sentence);
				}
				numLigne++;
			}
		}
		return fileSentencesCorrected;
	}

	private void writeCorrectionOnFiles(Map<String, List<Sentence>> fileSentencesCorrected) {
		Log.printLog("Étape 3: Réécriture sur tous les fichiers qui ont eu besoin de correction", TypeLog.INFO);
		fileSentencesCorrected.forEach((k, v) -> c.writeFile(k, v));
	}
}