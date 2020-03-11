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
				if (tmp.toString().equals(wd.getWord())) {
					return wd;
				}
			}
			return null;
		}
	}
}
