package core.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestWord {

	String str1 = "�";
	String str2 = "int�ressant";
	String str3 = "cha�ne";
	String str4 = "pasAccent";

	String purify1 = "!*^$ù";
	String purify2 = "KjUYhbJlMpOlOiUHbCdcXwSQaqzfgdSRyHIkoPmLkJHhNbVxdFG";
	String purify3 = "9746531";
	String purify4 = "l'arm�e";
	String purify5 = "suis-je?";
	String purify6 = "fool1";
	String purify7 = "aujourd'hui";
	String purify8 = "#FreeAnimals";

	Word w1 = new Word(str1);
	Word w2 = new Word(str2);
	Word w3 = new Word(str3);
	Word w4 = new Word(str4);

	Word w5 = new Word(purify1);
	Word w6 = new Word(purify2);
	Word w7 = new Word(purify3);
	Word w8 = new Word(purify4);
	Word w9 = new Word(purify5);
	Word w10 = new Word(purify6);
	Word w11 = new Word(purify7);
	Word w12 = new Word(purify8);

	@Test
	void testWord() {
		assertEquals(1, w1.findUnknowChar().size());
		assertEquals(1, w2.findUnknowChar().size());
		assertEquals(1, w3.findUnknowChar().size());
		assertEquals(0, w4.findUnknowChar().size());
	}

	@Test
	void testPurifyWord() {
		assertEquals("!*^$ù", w5.purifyWord()[0]); // Ne supprime aucun caractère
		assertEquals("KjUYhbJlMpOlOiUHbCdcXwSQaqzfgdSRyHIkoPmLkJHhNbVxdFG", w6.purifyWord()[0]); // Garde tout
		assertEquals("9746531", w7.purifyWord()[0]); // Renvoie les nombres telquel
		assertEquals("l'arm�e", w8.purifyWord()[0]); // Ne supprime pas le '
	}

}
