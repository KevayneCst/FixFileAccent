package grammar.french;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import grammar.Word;

class TestFrench {

	French f = new French();
	String uk1 = "int�ressement" ;
	String uk2 = "caract�re" ;
	String uk3 = "r�guli�rement" ;
	String uk4 = "cr�ation" ;
	
	@Test
	void testWord() throws CloneNotSupportedException {
		assertEquals("intéressement",f.matchWordWithDictionnary(new Word(uk1)).getWord());
		assertEquals("caractère",f.matchWordWithDictionnary(new Word(uk2)).getWord());
		assertEquals("régulièrement",f.matchWordWithDictionnary(new Word(uk3)).getWord());
		assertEquals("création",f.matchWordWithDictionnary(new Word(uk4)).getWord());
	}

}
