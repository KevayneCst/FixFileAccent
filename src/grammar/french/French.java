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
	private final char AAcentCirconflex = 'â';
	private final char EAccentAigu = 'é';
	private final char EAccentGrave = 'è';
	private final char OAccentCirconflex = 'ô';
	private final char IAccentCirconflex = 'î';
	private final char ITrema = 'ï';
	
	
	public French() {
	}
	
	public void correctWord(Word w) {
		List<Integer> list = w.findUnknowChar();
		if (!list.isEmpty()) { //S'il y a des caractères mal encodés à corriger
			for (int i : w.findUnknowChar()) {
				
			}
		}
		
	}
}
