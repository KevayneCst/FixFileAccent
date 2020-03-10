package core.grammar.french;

import java.util.List;

import core.grammar.Word;

/**
 * Défini les caractères possédant des accents, et déduis les accents manquants
 * @author ck802131
 *
 */
public class French {
	
	private static FrenchDictionnary fd = new FrenchDictionnary();
	
	public French() {
		//Ne fait rien
	}
	
	public Word matchWordWithDictionnary(Word w) {
		List<Word> potentialMatches = fd.getDictionnary().get(w.getWord().length());
		for (Word wd : potentialMatches) {
			StringBuilder tmp = new StringBuilder(w.getWord());
			for (int i : w.findUnknowChar()) {
				tmp.setCharAt(i, wd.getWord().charAt(i));
			}
			if (tmp.toString().equals(wd.getWord())) {
				return wd;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		French f = new French();
		String unknow ="v�tement";
		System.out.println("Mot trouvé: "+f.matchWordWithDictionnary(new Word(unknow)));
	}
}
