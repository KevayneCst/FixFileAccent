package core.grammar.french;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import core.grammar.Word;

class TestFrench {

	French f = new French();
	String falseUnknow = "bonjour";
	String uk1 = "int�ressement";
	String uk2 = "caract�re";
	String uk3 = "r�guli�rement";
	String uk4 = "cr�ation";

	String uk5 = "l'am�ne";
	String uk6 = "l'arm�e";
	String uk7 = "l'�cran";
	String uk8 = "l'identit�";
	String uk9 = "s'�carter";
	String nonValidUnknow = "l�";

	@Test
	void testWord() {
		assertEquals("intéressement", f.matchWordWithDictionnary(new Word(uk1)).getTheWord());
		assertEquals("caractère", f.matchWordWithDictionnary(new Word(uk2)).getTheWord());
		assertEquals("régulièrement", f.matchWordWithDictionnary(new Word(uk3)).getTheWord());
		assertEquals("création", f.matchWordWithDictionnary(new Word(uk4)).getTheWord());
		assertEquals("bonjour", f.matchWordWithDictionnary(new Word(falseUnknow)).getTheWord());
	}

	@Test
	void testSpecialWord() {
		assertEquals("l'amène", f.matchWordWithDictionnary(new Word(uk5)).getTheWord());
		assertEquals("l'armée", f.matchWordWithDictionnary(new Word(uk6)).getTheWord());
		assertEquals("l'écran", f.matchWordWithDictionnary(new Word(uk7)).getTheWord());
		assertEquals("l'identité", f.matchWordWithDictionnary(new Word(uk8)).getTheWord());
		assertEquals("s'écarter", f.matchWordWithDictionnary(new Word(uk9)).getTheWord());
		assertEquals("l�", f.matchWordWithDictionnary(new Word(nonValidUnknow)).getTheWord());
	}

}
