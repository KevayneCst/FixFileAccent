package core.grammar.french;

import java.util.ArrayList;
import java.util.List;

import core.Config;
import core.grammar.Language;
import core.grammar.Regex;
import core.grammar.Sentence;
import core.grammar.Word;
import core.grammar.WordCorrupted;
import core.grammar.WordDictionnary;
import core.log.Log;
import core.log.TypeLog;
import core.tools.Utilities;

/**
 * Classe concrète étendant <code>Language</code>, définissant les caractères
 * possédant des accents, et déduis les accents manquants grâce à son
 * dictionnaire.
 *
 * @author ck802131
 *
 */
public class French extends Language {

	public French() {
		super(new FrenchDictionnary());
	}

	@Override
	public Sentence correctSentence(Sentence toCorrect) {
		final List<WordCorrupted> correctedWords = new ArrayList<>();
		final List<WordCorrupted> listWord = toCorrect.extractCorruptedWords();
		final int sizeCorruptedWords = listWord.size();
		if (sizeCorruptedWords == 0) {
			Log.printLog("La phrase n'a pas besoin d'être corrigée: \"" + toCorrect.getTheLine() + "\"",
					TypeLog.DEBUGGING);
			return toCorrect;
		} else if (sizeCorruptedWords == 1) {
			Log.printLog("Correction de la phrase suivante[" + sizeCorruptedWords + " mot corrompu]: \""
					+ toCorrect.getTheLine() + "\"", TypeLog.DEBUGGING);
		} else {
			Log.printLog("Correction de la phrase suivante[" + sizeCorruptedWords + " mots corrompus]: \""
					+ toCorrect.getTheLine() + "\"", TypeLog.DEBUGGING);
		}

		for (int i = 0; i < sizeCorruptedWords; i++) {
			final WordCorrupted currentWord = listWord.get(i);
			final WordCorrupted correctedWord = matchWordWithDictionnary(currentWord);
			correctedWords.add(correctedWord);
			Log.printLog("Mots corrigés:" + Utilities.debugStringList(correctedWords), TypeLog.DEBUGGING);
		}

		toCorrect.replaceWords(correctedWords);
		Log.printLog("Phrase corrigée :\"" + toCorrect.getTheLine() + "\"", TypeLog.DEBUGGING);
		return toCorrect;
	}

	/**
	 * L'idée ici, c'est à partir de notre dictionnaire des mots avec accents qui
	 * existent, on regarde ceux qui ont la même taille (en lettres) que le mot
	 * qu'on veut corriger (appelons-le X). Une fois qu'on a fait ce tri
	 * (appelons-le A). Si le mot qu'on est en train de regarder dans A est égal à X
	 * une fois avoir remplacé les caractères inconnu dans X par les caractères au
	 * même indice du mot de A, alors on aura bien trouvé le mot corrompu, on aura
	 * donc trouvé le mot manquant. <br>
	 * <br>
	 * Exemple:<br>
	 * J'ai le mot inconnu suivant de 5 lettres : "cr�er" (il sera donc le X)<br>
	 * Après avoir fait le tri de tout les mots de 5 lettres de mon dictionnaire,
	 * j'ai les mots candidats suivants : "pièce", "créée", "créer", "porté",
	 * "états", "passé", "légal", "après" (cette ensemble de mots est A)<br>
	 * <br>
	 * Je vais maintenant remplacer à l'indice de l'accent manquant de X, le
	 * caractère se trouvant au même indice qu'un des mots de A, ici on va commencer
	 * par "pièce":<br>
	 *
	 * "cr�er" n'a pas d'accent à l'indice 2. Je prends donc un mot de A, "pièce",
	 * dont je vais prendre le deuxième indice (ici le caractère 'è', et je le
	 * partage avec mon X, maintenant X = "crèer".<br>
	 * Dans ce cas-ci, X != "pièce", on essaye donc avec un autre mot de A, jusqu'à
	 * tomber sur le bon. donc pour le suivant mot de A, X serait égal à "créer" !=
	 * "créée", mais pour le mot suivant de A, ici "créer", X serait égal à "créer"
	 * donc X = mot de A<br>
	 * <br>
	 * DONC le mot qu'on cherchait était "créer".
	 *
	 *
	 * @param w Le mot corrompu et inconnu à corriger
	 * @return <code>Word</code>
	 */
	@Override
	public WordCorrupted matchWordWithDictionnary(WordCorrupted w) {
		final List<Integer> unknowsCharIndexes = w.findUnknowChar();
		if (unknowsCharIndexes.isEmpty()) {
			Log.printLog("\"" + w.getTheWord() + "\" n'a pas besoin d'être corrigé, il n'aurait jamais dû arriver ici",
					TypeLog.DEBUGGING);
			return w;
		} else {
			final Word potentialSavedWord = super.checkIfWordAlreadyCorrected(w);
			if (potentialSavedWord instanceof WordCorrupted) {
				Log.printLog("Pas de correction en mémoire pour \"" + w.getTheWord() + "\"", TypeLog.DEBUGGING);
				return tryCorrection(w, unknowsCharIndexes);
			} else {
				Log.printLog("Une correction a été trouvé dans la mémoire pour \"" + w.getTheWord() + "\"",
						TypeLog.DEBUGGING);
				return w.convert((WordDictionnary) potentialSavedWord);
			}
		}
	}

	/**
	 * Fonction qui va tenter de corriger <code>w</code> en recherchant les mots
	 * candidats pour une correction, et qui pour chacun d'eux va vérifier s'ils le
	 * sont vraiment dans <code>computePotentialMatches</code>.
	 *
	 * @param w                  Le mot corrompu à corriger
	 * @param unknowsCharIndexes Liste d'indice du/des caractère(s) corrompu(s) de
	 *                           <code>w</code>
	 * @return <code>Word</code>
	 */
	private WordCorrupted tryCorrection(WordCorrupted w, List<Integer> unknowsCharIndexes) {
		Log.printLog("Correction du mot \"" + w.getTheWord() + "\"", TypeLog.DEBUGGING);
		final List<WordDictionnary> potentialMatches = super.getDictionnary().getDico().get(w.getTheWord().length());
		final List<WordCorrupted> rightMatches = new ArrayList<>();

		if (potentialMatches != null) {
			Log.printLog(potentialMatches.size() + " mots candidats pour la correction", TypeLog.DEBUGGING);
			final WordCorrupted result = computePotentialMatches(w, unknowsCharIndexes, potentialMatches, rightMatches);
			if (result != null) {
				return result;
			} else {
				if (!rightMatches.isEmpty()) {
					final WordCorrupted choosenWord = rightMatches.size() == 1 ? rightMatches.get(0)
							: Utilities.waitConfirmationWord(w, rightMatches);
					final WordDictionnary correction = super.getDictionnary().findWord(choosenWord.getTheWord());
					if (correction == null) {
						Log.printLog("La correction du mot \"" + w.getTheWord()
								+ "\" a généré une correction en un mot inéxistant lors de l'essai de correction => \""
								+ choosenWord.getTheWord() + "\"", TypeLog.CRITICAL);
					} else {
						saveCorrection(w, new WordDictionnary(choosenWord.getTheWord()));
					}
					return choosenWord;
				}
			}
		}
		Log.printLog("Impossible de trouver une correspondance dans le dictionnaire pour \"" + w.getTheWord() + "\"",
				TypeLog.WARNING);
		return w;
	}

	/**
	 * Fonction qui va vérifier pour chacun des mots de
	 * <code>potentialMatches</code> s'il s'agit du mot corrompu à corriger
	 * <code>w</code>.<br>
	 * Si l'option <code>isConfirmWord</code> dans le fichier de configuration est
	 * désactivé, va renvoyer le premier résultat positif (un type
	 * <code>Word</code>), sinon va chercher tous les résultats positifs, les
	 * stocker dans <code>rightMatches</code> et renvoyer <code>null</code>.
	 *
	 * @param w                  Le mot corrompu à corriger
	 * @param unknowsCharIndexes Liste d'indice du/des caractère(s) corrompu(s) de
	 *                           <code>w</code>
	 * @param potentialMatches   Liste de mots candidats à la correction de
	 *                           <code>w</code>
	 * @param rightMatches       Liste des mots qui sont égaux à <code>w</code>
	 * @return <code>WordCorrupted</code> OU <code>null</code>
	 */
	private WordCorrupted computePotentialMatches(WordCorrupted w, List<Integer> unknowsCharIndexes,
			List<WordDictionnary> potentialMatches, List<WordCorrupted> rightMatches) {
		for (final WordDictionnary wd : potentialMatches) {
			final List<Character> unknowsChars = new ArrayList<>();
			final StringBuilder tmp = new StringBuilder(w.getTheWord());
			for (final int i : unknowsCharIndexes) {
				final char currentPotentialMatchChar = wd.getTheWord().charAt(i);
				if ((currentPotentialMatchChar + "").matches(Regex.REGEX_ONLY_LETTERS)) {
					break;
				}
				tmp.setCharAt(i, currentPotentialMatchChar);
				unknowsChars.add(currentPotentialMatchChar);
			}
			if (tmp.toString().equalsIgnoreCase(wd.getTheWord())) {
				final StringBuilder sb = new StringBuilder(w.getTheWord());
				int indexListChars = 0;
				for (final Integer i : unknowsCharIndexes) {
					sb.setCharAt(i, unknowsChars.get(indexListChars));
					indexListChars++;
				}
				final WordCorrupted correctedWord = new WordCorrupted(sb.toString(), w.getIndexBeginInSentence(),
						w.getIndexEndInSentence());
				Log.printLog("Correspondance trouvé pour \"" + w.getTheWord() + "\" => \"" + correctedWord.getTheWord()
						+ "\"", TypeLog.DEBUGGING);
				if (Config.getInstance().isConfirmWord()) {
					rightMatches.add(correctedWord);
				} else {
					final WordDictionnary correction = super.getDictionnary().findWord(sb.toString());
					if (correction == null) {
						Log.printLog("La correction du mot \"" + w.getTheWord()
								+ "\" a généré une correction en un mot inéxistant lors de la vérifications des mots potentiels => \""
								+ sb.toString() + "\"", TypeLog.CRITICAL);
					} else {
						saveCorrection(w, new WordDictionnary(correctedWord.getTheWord()));
					}
					return correctedWord;
				}
			}
		}
		return null;
	}

	/**
	 * Méthode qui va, si dans <code>config.properties</code>, l'attribut
	 * <code>rememberChoice=TRUE</code>, sauvegarder la correction trouvé par
	 * l'algorithme ou par l'utilisateur dans le cas où
	 * <code>confirmWord=TRUE</code>.
	 *
	 * @param unknow    le mot corrompu
	 * @param corrected la correction pour <code>unknow</code>
	 */
	private void saveCorrection(WordCorrupted unknow, WordDictionnary corrected) {
		if (Config.getInstance().isRememberChoice()) {
			Log.printLog("Ajout d'une entrée dans la mémoire des corrections sauvegardées :\"" + unknow.getTheWord()
					+ "\" => \"" + corrected.getTheWord() + "\"", TypeLog.DEBUGGING);
			super.getSavedCorrections().put(unknow, corrected);
		}
	}
}
