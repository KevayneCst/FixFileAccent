package core.grammar.french;

import java.util.ArrayList;
import java.util.List;

import core.Config;
import core.grammar.Language;
import core.grammar.Regex;
import core.grammar.Sentence;
import core.grammar.Word;
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
	public List<Word> correctSentence(Sentence toCorrect) {
		int countUnknowChar = toCorrect.countUnknowChar();
		if (countUnknowChar == 1) {
			Log.printLog("Correction de la phrase suivante[" + countUnknowChar + " caractère inconnu]: \""
					+ toCorrect.getTheLine() + "\"", TypeLog.DEBUGGING);
		} else {
			Log.printLog("Correction de la phrase suivante[" + countUnknowChar + " caractères inconnus]: \""
					+ toCorrect.getTheLine() + "\"", TypeLog.DEBUGGING);
		}
		List<Word> listWord = toCorrect.getWords();
		List<Word> correctedWords = new ArrayList<>();
		for (int i = 0; i < listWord.size(); i++) {
			if (countUnknowChar == 0) {
				correctedWords.add(listWord.get(i));
			} else {
				Word correctedWord = matchWordWithDictionnary(listWord.get(i));
				boolean isWordCorrected = !correctedWord.equals(listWord.get(i));
				correctedWords.add(correctedWord);
				if (isWordCorrected)
					countUnknowChar--;
			}
			Log.printLog("Mots corrigés:" + Utilities.debugStringList(correctedWords), TypeLog.DEBUGGING);
		}
		return correctedWords;
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
	public Word matchWordWithDictionnary(Word w) {
		List<Integer> unknowsCharIndexes = w.findUnknowChar();
		if (unknowsCharIndexes.isEmpty()) {
			Log.printLog("\"" + w.getTheWord() + "\" n'a pas besoin d'être corrigé", TypeLog.DEBUGGING);
			return w;
		} else {
			Word potentialSavedWord = super.checkIfWordAlreadyCorrected(w);
			if (potentialSavedWord.equals(w)) {
				return tryCorrection(w, unknowsCharIndexes);
			} else {
				return potentialSavedWord;
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
	private Word tryCorrection(Word w, List<Integer> unknowsCharIndexes) {
		List<Word> potentialMatches = super.getDictionnary().getDico().get(w.getTheWord().length());
		List<Word> rightMatches = new ArrayList<>();
		Log.printLog("Correction du mot \"" + w.getTheWord() + "\"", TypeLog.DEBUGGING);
		if (potentialMatches != null) {
			Log.printLog(potentialMatches.size() + " mots candidats pour la correction", TypeLog.DEBUGGING);
			Word result = computePotentialMatches(w, unknowsCharIndexes, potentialMatches, rightMatches);
			if (result != null) {
				return result;
			} else {
				if (!rightMatches.isEmpty()) {
					Word choosenWord = rightMatches.size() == 1 ? rightMatches.get(0)
							: Utilities.waitConfirmationWord(w, rightMatches);
					super.getSavedCorrections().put(w, choosenWord);
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
	 * activé, va renvoyer le premier résultat positif (un type <code>Word</code>),
	 * sinon va chercher tous les résultats positifs, les stocker dans
	 * <code>rightMatches</code> et renvoyer <code>null</code>.
	 * 
	 * @param w                  Le mot corrompu à corriger
	 * @param unknowsCharIndexes Liste d'indice du/des caractère(s) corrompu(s) de
	 *                           <code>w</code>
	 * @param potentialMatches   Liste de mots candidats à la correction de
	 *                           <code>w</code>
	 * @param rightMatches       Liste des mots qui sont égaux à <code>w</code>
	 * @return <code>Word</code> OU <code>null</code>
	 */
	private Word computePotentialMatches(Word w, List<Integer> unknowsCharIndexes, List<Word> potentialMatches,
			List<Word> rightMatches) {
		for (Word wd : potentialMatches) {
			List<Character> unknowsChars = new ArrayList<>();
			StringBuilder tmp = new StringBuilder(w.getTheWord());
			for (int i : unknowsCharIndexes) {
				char currentPotentialMatchChar = wd.getTheWord().charAt(i);
				if ((currentPotentialMatchChar + "").matches(Regex.REGEX_ONLY_LETTERS)) {
					break;
				}
				tmp.setCharAt(i, currentPotentialMatchChar);
				unknowsChars.add(currentPotentialMatchChar);
			}
			if (tmp.toString().equalsIgnoreCase(wd.getTheWord())) {
				StringBuilder sb = new StringBuilder(w.getTheWord());
				int indexListChars = 0;
				for (Integer i : unknowsCharIndexes) {
					sb.setCharAt(i, unknowsChars.get(indexListChars));
					indexListChars++;
				}
				Word correctedWord = new Word(sb.toString());
				Log.printLog("Correspondance trouvé pour \"" + w.getTheWord() + "\" => \"" + correctedWord.getTheWord()
						+ "\"", TypeLog.DEBUGGING);
				if (Config.getInstance().isConfirmWord()) {
					rightMatches.add(correctedWord);
				} else {
					super.getSavedCorrections().put(w, correctedWord);
					return correctedWord;
				}
			}
		}
		return null;
	}
}
