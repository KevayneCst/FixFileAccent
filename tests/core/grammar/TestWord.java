package core.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestWord {

	String s1 = "bonjour";
	String s2 = "péris";
	String s3 = "journée";
	String s4 = "bl�s�tes";
	String s5 = "abaissâtes";
	String s6 = "déambulions";
	String s7 = "d�sindexer";
	String s8 = "action";
	String s9 = "marcher";
	String s10 = "non";
	String s11 = "v�tures";
	String s12 = "activé";
	String s13 = "coryph�e";
	String s14 = "jutés";
	String s15 = "tolér�t";
	String s16 = "vêtements";
	String s17 = "dénickel�mes";
	String s18 = "exag�r�s";
	String s19 = "parler";
	String s20 = "râtela";

	Word w1 = new Word(s1);
	Word w2 = new Word(s2);
	Word w3 = new Word(s3);
	Word w4 = new Word(s4);
	Word w5 = new Word(s5);
	Word w6 = new Word(s6);
	Word w7 = new Word(s7);
	Word w8 = new Word(s8);
	Word w9 = new Word(s9);
	Word w10 = new Word(s10);
	Word w11 = new Word(s11);
	Word w12 = new Word(s12);
	Word w13 = new Word(s13);
	Word w14 = new Word(s14);
	Word w15 = new Word(s15);
	Word w16 = new Word(s16);
	Word w17 = new Word(s17);
	Word w18 = new Word(s18);
	Word w19 = new Word(s19);
	Word w20 = new Word(s20);

	@Test
	void testFindUnknowCharSize() {
		assertEquals(0, w1.findUnknowChar().size());
		assertEquals(0, w2.findUnknowChar().size());
		assertEquals(0, w3.findUnknowChar().size());
		assertEquals(2, w4.findUnknowChar().size());
		assertEquals(0, w5.findUnknowChar().size());
		assertEquals(0, w6.findUnknowChar().size());
		assertEquals(1, w7.findUnknowChar().size());
		assertEquals(0, w8.findUnknowChar().size());
		assertEquals(0, w9.findUnknowChar().size());
		assertEquals(0, w10.findUnknowChar().size());
		assertEquals(1, w11.findUnknowChar().size());
		assertEquals(0, w12.findUnknowChar().size());
		assertEquals(1, w13.findUnknowChar().size());
		assertEquals(0, w14.findUnknowChar().size());
		assertEquals(1, w15.findUnknowChar().size());
		assertEquals(0, w16.findUnknowChar().size());
		assertEquals(1, w17.findUnknowChar().size());
		assertEquals(2, w18.findUnknowChar().size());
		assertEquals(0, w19.findUnknowChar().size());
		assertEquals(0, w20.findUnknowChar().size());
	}

	@Test
	void testFindUnknowCharIndexes() {
		assertEquals(new ArrayList<Integer>(), w1.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w2.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w3.findUnknowChar());
		assertEquals(new ArrayList<Integer>(Arrays.asList(2,4)), w4.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w5.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w6.findUnknowChar());
		assertEquals(new ArrayList<Integer>(Arrays.asList(1)), w7.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w8.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w9.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w10.findUnknowChar());
		assertEquals(new ArrayList<Integer>(Arrays.asList(1)), w11.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w12.findUnknowChar());
		assertEquals(new ArrayList<Integer>(Arrays.asList(6)), w13.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w14.findUnknowChar());
		assertEquals(new ArrayList<Integer>(Arrays.asList(5)), w15.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w16.findUnknowChar());
		assertEquals(new ArrayList<Integer>(Arrays.asList(8)), w17.findUnknowChar());
		assertEquals(new ArrayList<Integer>(Arrays.asList(4, 6)), w18.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w19.findUnknowChar());
		assertEquals(new ArrayList<Integer>(), w20.findUnknowChar());
	}
}
