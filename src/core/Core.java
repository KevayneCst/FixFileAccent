package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		if (Config.getInstance().isCreateSave()) {
			c.makeSave(pathDirectory);
		}
	}

	public void start() {
		Config.getInstance().showProperties();

		final List<String> listPathFiles = f.getPathFiles();
		final List<FileContent> fileSentences = readAndStoreLines(listPathFiles);
		final List<FileContent> fileSentencesCorrected = correctFiles(fileSentences);

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

	private List<FileContent> readAndStoreLines(List<String> listPathFiles) {
		Log.printLog("Étape 1: Lecture et sauvegarde de toutes les lignes de tous les fichiers", TypeLog.INFO);

		final List<FileContent> fileSentences = new ArrayList<>();
		listPathFiles.forEach(path -> fileSentences.add(new FileContent(path, r.readFile(path))));

		return fileSentences;
	}

	private List<FileContent> correctFiles(List<FileContent> fileSentences) {
		final List<FileContent> fileSentencesCorrected = new ArrayList<>();
		Log.printLog("Étape 2: Corriger toutes les lignes qui ont besoin d'être corrigé pour tous les fichiers",
				TypeLog.INFO);
		for (final FileContent file : fileSentences) {
			int numLigne = 1;
			final String path = file.getPath();
			final List<Sentence> sentences = file.getContent();
			Log.printLog("============ Traitement du fichier: " + path + " ============", TypeLog.DEBUGGING);
			for (final Sentence sentence : sentences) {
				if (sentence.needCorrection()) {
					Log.printLog("Ligne " + numLigne + ", la phrase \"" + sentence.getTheLine()
							+ "\" a besoin d'être corrigée", TypeLog.DEBUGGING);
					putIntoList(fileSentencesCorrected, path, lang.correctSentence(sentence));
				} else {
					Log.printLog("Ligne " + numLigne + ", la phrase \"" + sentence.getTheLine()
							+ "\" n'a pas eu besoin d'être corrigée", TypeLog.DEBUGGING);
					putIntoList(fileSentencesCorrected, path, sentence);
				}
				numLigne++;
			}
		}
		return fileSentencesCorrected;
	}

	private void writeCorrectionOnFiles(List<FileContent> fileSentencesCorrected) {
		Log.printLog("Étape 3: Réécriture sur tous les fichiers qui ont eu besoin de correction", TypeLog.INFO);
		fileSentencesCorrected.forEach(file -> c.writeFile(file.getPath(), file.getContent()));
	}

	private void putIntoList(List<FileContent> destination, String path, Sentence toAdd) {
		final Optional<FileContent> entry = destination.stream().filter(x -> x.getPath().equals(path)).findFirst();
		if (entry.isPresent()) {
			entry.get().addSentence(toAdd);
		} else {
			final FileContent tmp = new FileContent(path);
			tmp.addSentence(toAdd);
			destination.add(tmp);
		}
	}
}