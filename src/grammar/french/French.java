package grammar.french;

import java.util.List;

import grammar.Word;

/**
 * Défini les caractères possédant des accents, et déduis les accents manquants
 * @author ck802131
 *
 */
public class French {
	
	private final char AAccentGrave = 'à';
	private final char AAcentCirconflexe = 'â';
	private final char EAccentAigu = 'é';
	private final char EAccentGrave = 'è';
	private final char OAccentCirconflexe = 'ô';
	private final char IAccentCirconflexe = 'î';
	private final char ITrema = 'ï';
	private FrenchDictionnary fd;
	
	public French() {
		this.fd=new FrenchDictionnary();
	}
	
	public void correctWord(Word w) {
		List<Integer> list = w.findUnknowChar();
		if (!list.isEmpty()) { //S'il y a des caractères mal encodés à corriger
			for (int i : w.findUnknowChar()) {
				
			}
		}
	}
	
	public char matchWord(Word w, int indice) {
		return 'k';
	}
}
