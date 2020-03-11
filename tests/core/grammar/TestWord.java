package core.grammar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestWord {

	String str1 = "�";
	String str2 = "int�ressant";
	String str3 = "cha�ne";
	String str4 = "pasAccent";
	
	Word w1 = new Word(str1);
	Word w2 = new Word(str2);
	Word w3 = new Word(str3);
	Word w4 = new Word(str4);
	
	@Test
	void testWord() {
		assertEquals(1,w1.findUnknowChar().size());
		assertEquals(1,w2.findUnknowChar().size());
		assertEquals(1,w3.findUnknowChar().size());
		assertEquals(0,w4.findUnknowChar().size());
	}

}
