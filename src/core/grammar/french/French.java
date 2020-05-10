package core.grammar.french;

import java.util.ArrayList;
import java.util.List;

import core.grammar.Language;
import core.grammar.Sentence;
import core.grammar.Word;
import core.log.Log;
import core.log.TypeLog;

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
		Log.printLog("Correction de la phrase suivante: \"" + toCorrect.getTheLine(), TypeLog.DEBUGGING);
		List<Word> listWord = toCorrect.getPurifiedWords();
		List<Word> correctedWords = new ArrayList<>();
		for (int i = 0; i < listWord.size(); i++) {
			correctedWords.add(matchWordWithDictionnary(listWord.get(i)));
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
	 * @param w Le mot inconnu
	 * @return
	 */
	@Override
	public Word matchWordWithDictionnary(Word w) {
		List<Integer> unknowsChar = w.findUnknowChar();
		if (unknowsChar.isEmpty()) {
			Log.printLog("\"" + w.getTheWord() + "\" n'a pas besoin d'être corrigé", TypeLog.DEBUGGING);
			return w;
		} else {
			List<Word> potentialMatches = super.getDictionnary().getDico().get(w.getTheWord().length());
			for (Word wd : potentialMatches) {
				StringBuilder tmp = new StringBuilder(w.getTheWord());
				for (int i : unknowsChar) {
					tmp.setCharAt(i, wd.getTheWord().charAt(i));
				}
				if (tmp.toString().equalsIgnoreCase(wd.getTheWord())) {
					Log.printLog("Correspondance trouvé pour \"" + w.getTheWord() + "\" => \"" + wd.getTheWord() + "\"",
							TypeLog.DEBUGGING);
					return wd;
				}
			}
			Log.printLog(
					"Impossible de trouver une correspondance dans le dictionnaire pour \"" + w.getTheWord() + "\"",
					TypeLog.WARNING);
			return w;
		}
	}
}
