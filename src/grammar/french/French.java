package grammar.french;

import java.util.List;

import grammar.Word;

/**
 * Défini les caractères possédant des accents, et déduis les accents manquants
 * @author ck802131
 *
 */
public class French {
	
	private static FrenchDictionnary fd = new FrenchDictionnary();
	
	public French() {
	}
	
	public Word matchWordWithDictionnary(Word w) throws CloneNotSupportedException {
		List<Word> potentialMatches = fd.getDictionnary().get(w.getWord().length());
		System.out.println(potentialMatches);
		for (Word wd : potentialMatches) {
			StringBuilder tmp = new StringBuilder(w.getWord());
			for (int i : w.findUnknowChar()) {
				System.out.println("Remplacement à l'indice "+i+" par le char:"+wd.getWord().charAt(i));
				tmp.setCharAt(i, wd.getWord().charAt(i));
			}
			if (tmp.toString().equals(wd.getWord())) {
				return wd;
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		French f = new French();
		String unknow ="v�tement";
		System.out.println("Mot trouvé: "+f.matchWordWithDictionnary(new Word(unknow)));
	}
}
