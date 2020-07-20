package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public Core(String pathDirectory) {
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
		Config.getInstance().showProperties();

		List<String> listPathFiles = f.getPathFiles();
		Map<String, List<Sentence>> fileSentences = new HashMap<>();
		Map<String, List<Sentence>> fileSentencesCorrected = new HashMap<>();

		if (Config.getInstance().isConfirmFiles()) {
			showFiles(listPathFiles);
			waitConfirmation();
		}

		fileSentences = readAndStoreLines(listPathFiles);

		fileSentencesCorrected = correctFiles(fileSentences);

		if (Config.getInstance().isApplyCorrection()) {
			writeCorrectionOnFiles(fileSentencesCorrected);
		} else {
			Log.printLog("Fin de la simulation", TypeLog.INFO);
		}
	}
	
	private void showFiles(List<String> listPathFiles) {
		Log.printLog("Les fichiers suivants vont être corrigés", TypeLog.INFO);
		int i = 1;
		for (String s : listPathFiles) {
			Log.printLog("Fichier n°"+i+ ":"+s,TypeLog.INFO);
			i++;
		}
	}

	private void waitConfirmation() {
		boolean confirmationGiven = false;
		Log.printLog("Veuillez saisir le mot "+Config.getInstance().getConfirmationKey()+ " pour commencer la correction", TypeLog.INFO);
		while (!confirmationGiven) {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			try {
				System.out.print("$>");
				String readedString = bufferRead.readLine();
				if (readedString.equalsIgnoreCase(Config.getInstance().getConfirmationKey())) {
					confirmationGiven = true;
					Log.printLog("Mot clé correct, démarrage de la correction...", TypeLog.INFO);
				} else {
					Log.printLog("Mot clé incorrect: "+readedString+", veuillez réessayer", TypeLog.INFO);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Map<String, List<Sentence>> readAndStoreLines(List<String> listPathFiles) {
		Map<String, List<Sentence>> fileSentences = new HashMap<>();
		Log.printLog("Étape 1: Lecture et sauvegarde de toutes les lignes de tous les fichiers", TypeLog.INFO);
		for (String s : listPathFiles) {
			fileSentences.put(s, r.readFile(s));
		}

		return fileSentences;
	}

	private Map<String, List<Sentence>> correctFiles(Map<String, List<Sentence>> fileSentences) {
		Map<String, List<Sentence>> fileSentencesCorrected = new HashMap<>();
		Log.printLog("Étape 2: Corriger toutes les lignes qui ont besoin d'être corrigé pour tous les fichiers",
				TypeLog.INFO);
		for (Map.Entry<String, List<Sentence>> hm : fileSentences.entrySet()) {
			int numLigne = 1;
			Log.printLog("============ Traitement du fichier: " + hm.getKey() + " ============", TypeLog.DEBUGGING);
			for (Sentence sentence : hm.getValue()) {
				if (sentence.needCorrection()) {
					Log.printLog("Ligne " + numLigne + ", la phrase \"" + sentence.getTheLine()
							+ "\" a besoin d'être corrigée", TypeLog.DEBUGGING);
					putIntoMap(fileSentencesCorrected, hm.getKey(),
							sentence.rebuildSentence(lang.correctSentence(sentence)));
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
		for (Map.Entry<String, List<Sentence>> hm : fileSentencesCorrected.entrySet()) {
			c.writeFile(hm.getKey(), hm.getValue());
		}
	}
}