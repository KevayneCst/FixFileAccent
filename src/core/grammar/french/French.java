package core.grammar.french;

import java.util.List;

import core.grammar.Sentence;
import core.grammar.Word;

/**
 * Défini les caractères possédant des accents, et déduis les accents manquants
 * 
 * @author ck802131
 *
 */
public class French {

	private static FrenchDictionnary fd = new FrenchDictionnary();

	public French() {
		// Ne fait rien
	}

	public Sentence correctSentence(List<Word> listWord) {
		StringBuilder sb = new StringBuilder();
		for (Word w : listWord) {
			sb.append(matchWordWithDictionnary(w).getWord());
		}
		return new Sentence(sb.toString());
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
	 * dont je vais prendre le deuxième indice (ici le caractère 'è' et le partager
	 * avec mon X, maintenant X = "crèer".<br>
	 * Dans ce cas-ci, X != "pièce", on essaye donc avec un autre mot de A, jusqu'à
	 * tomber sur le bon. donc pour le suivant mot de A, X serait égal à "créer" !=
	 * "créée", mais pour le mot suivant de A, ici "créer", X serait égal à "créer"
	 * donc X = mot de A<br><br> DONC le mot qu'on cherchait était "créer".
	 * 
	 * 
	 * @param w Le mot inconnu
	 * @return
	 */
	public Word matchWordWithDictionnary(Word w) {
		List<Integer> unknowsChar = w.findUnknowChar();
		if (unknowsChar.isEmpty()) {
			return w;
		} else {
			List<Word> potentialMatches = fd.getDictionnary().get(w.getWord().length());
			for (Word wd : potentialMatches) {
				StringBuilder tmp = new StringBuilder(w.getWord());
				for (int i : unknowsChar) {
					tmp.setCharAt(i, wd.getWord().charAt(i));
				}
				if (tmp.toString().equalsIgnoreCase(wd.getWord())) {
					return wd;
				}
			}
			return null;
		}
	}
}
