package core.grammar.french;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import core.grammar.Word;

class TestFrench {

	French f = new French();
	String falseUnknow = "bonjour";
	String uk1 = "int�ressement" ;
	String uk2 = "caract�re" ;
	String uk3 = "r�guli�rement" ;
	String uk4 = "cr�ation" ;
	
	@Test
	void testWord() {
		assertEquals("intéressement",f.matchWordWithDictionnary(new Word(uk1)).getTheWord());
		assertEquals("caractère",f.matchWordWithDictionnary(new Word(uk2)).getTheWord());
		assertEquals("régulièrement",f.matchWordWithDictionnary(new Word(uk3)).getTheWord());
		assertEquals("création",f.matchWordWithDictionnary(new Word(uk4)).getTheWord());
		assertEquals("bonjour",f.matchWordWithDictionnary(new Word(falseUnknow)).getTheWord());
	}

}
