package core.grammar;

import static org.junit.Assert.assertTrue;
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
	Word w1bis = new Word(s1);
	Word w1bis2 = new Word(s1);
	Word w2 = new Word(s2);
	Word w2bis = new Word(s2);
	Word w2bis2 = new Word(s2);
	Word w3 = new Word(s3);
	Word w3bis = new Word(s3);
	Word w3bis2 = new Word(s3);
	Word w4 = new Word(s4);
	Word w4bis = new Word(s4);
	Word w4bis2 = new Word(s4);
	Word w5 = new Word(s5);
	Word w5bis = new Word(s5);
	Word w5bis2 = new Word(s5);
	Word w6 = new Word(s6);
	Word w6bis = new Word(s6);
	Word w6bis2 = new Word(s6);
	Word w7 = new Word(s7);
	Word w7bis = new Word(s7);
	Word w7bis2 = new Word(s7);
	Word w8 = new Word(s8);
	Word w8bis = new Word(s8);
	Word w8bis2 = new Word(s8);
	Word w9 = new Word(s9);
	Word w9bis = new Word(s9);
	Word w9bis2 = new Word(s9);
	Word w10 = new Word(s10);
	Word w10bis = new Word(s10);
	Word w10bis2 = new Word(s10);
	Word w11 = new Word(s11);
	Word w11bis = new Word(s11);
	Word w11bis2 = new Word(s11);
	Word w12 = new Word(s12);
	Word w12bis = new Word(s12);
	Word w12bis2 = new Word(s12);
	Word w13 = new Word(s13);
	Word w13bis = new Word(s13);
	Word w13bis2 = new Word(s13);
	Word w14 = new Word(s14);
	Word w14bis = new Word(s14);
	Word w14bis2 = new Word(s14);
	Word w15 = new Word(s15);
	Word w15bis = new Word(s15);
	Word w15bis2 = new Word(s15);
	Word w16 = new Word(s16);
	Word w16bis = new Word(s16);
	Word w16bis2 = new Word(s16);
	Word w17 = new Word(s17);
	Word w17bis = new Word(s17);
	Word w17bis2 = new Word(s17);
	Word w18 = new Word(s18);
	Word w18bis = new Word(s18);
	Word w18bis2 = new Word(s18);
	Word w19 = new Word(s19);
	Word w19bis = new Word(s19);
	Word w19bis2 = new Word(s19);
	Word w20 = new Word(s20);
	Word w20bis = new Word(s20);
	Word w20bis2 = new Word(s20);

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
		assertEquals(new ArrayList<Integer>(Arrays.asList(2, 4)), w4.findUnknowChar());
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

	@Test
	void testEqualsReflexive() {
		assertTrue(w1.equals(w1));
		assertTrue(w2.equals(w2));
		assertTrue(w3.equals(w3));
		assertTrue(w4.equals(w4));
		assertTrue(w5.equals(w5));
		assertTrue(w6.equals(w6));
		assertTrue(w7.equals(w7));
		assertTrue(w8.equals(w8));
		assertTrue(w9.equals(w9));
		assertTrue(w10.equals(w10));
		assertTrue(w11.equals(w11));
		assertTrue(w12.equals(w12));
		assertTrue(w13.equals(w13));
		assertTrue(w14.equals(w14));
		assertTrue(w15.equals(w15));
		assertTrue(w16.equals(w16));
		assertTrue(w17.equals(w17));
		assertTrue(w18.equals(w18));
		assertTrue(w19.equals(w19));
		assertTrue(w20.equals(w20));
	}

	@Test
	void testEqualsSymmetric() {
		assertTrue(w1.equals(w1bis));
		assertTrue(w1bis.equals(w1));
		assertTrue(w2.equals(w2bis));
		assertTrue(w2bis.equals(w2));
		assertTrue(w3.equals(w3bis));
		assertTrue(w3bis.equals(w3));
		assertTrue(w4.equals(w4bis));
		assertTrue(w4bis.equals(w4));
		assertTrue(w5.equals(w5bis));
		assertTrue(w5bis.equals(w5));
		assertTrue(w6.equals(w6bis));
		assertTrue(w6bis.equals(w6));
		assertTrue(w7.equals(w7bis));
		assertTrue(w7bis.equals(w7));
		assertTrue(w8.equals(w8bis));
		assertTrue(w8bis.equals(w8));
		assertTrue(w9.equals(w9bis));
		assertTrue(w9bis.equals(w9));
		assertTrue(w10.equals(w10bis));
		assertTrue(w10bis.equals(w10));
		assertTrue(w11.equals(w11bis));
		assertTrue(w11bis.equals(w11));
		assertTrue(w12.equals(w12bis));
		assertTrue(w12bis.equals(w12));
		assertTrue(w13.equals(w13bis));
		assertTrue(w13bis.equals(w13));
		assertTrue(w14.equals(w14bis));
		assertTrue(w14bis.equals(w14));
		assertTrue(w15.equals(w15bis));
		assertTrue(w15bis.equals(w15));
		assertTrue(w16.equals(w16bis));
		assertTrue(w16bis.equals(w16));
		assertTrue(w17.equals(w17bis));
		assertTrue(w17bis.equals(w17));
		assertTrue(w18.equals(w18bis));
		assertTrue(w18bis.equals(w18));
		assertTrue(w19.equals(w19bis));
		assertTrue(w19bis.equals(w19));
		assertTrue(w20.equals(w20bis));
		assertTrue(w20bis.equals(w20));
	}

	@Test
	void testEqualsTransitive() {
		assertTrue(w1.equals(w1bis));
		assertTrue(w1bis.equals(w1bis2));
		assertTrue(w1.equals(w1bis2));
		assertTrue(w2.equals(w2bis));
		assertTrue(w2bis.equals(w2bis2));
		assertTrue(w2.equals(w2bis2));
		assertTrue(w3.equals(w3bis));
		assertTrue(w3bis.equals(w3bis2));
		assertTrue(w3.equals(w3bis2));
		assertTrue(w4.equals(w4bis));
		assertTrue(w4bis.equals(w4bis2));
		assertTrue(w4.equals(w4bis2));
		assertTrue(w5.equals(w5bis));
		assertTrue(w5bis.equals(w5bis2));
		assertTrue(w5.equals(w5bis2));
		assertTrue(w6.equals(w6bis));
		assertTrue(w6bis.equals(w6bis2));
		assertTrue(w6.equals(w6bis2));
		assertTrue(w7.equals(w7bis));
		assertTrue(w7bis.equals(w7bis2));
		assertTrue(w7.equals(w7bis2));
		assertTrue(w8.equals(w8bis));
		assertTrue(w8bis.equals(w8bis2));
		assertTrue(w8.equals(w8bis2));
		assertTrue(w9.equals(w9bis));
		assertTrue(w9bis.equals(w9bis2));
		assertTrue(w9.equals(w9bis2));
		assertTrue(w10.equals(w10bis));
		assertTrue(w10bis.equals(w10bis2));
		assertTrue(w10.equals(w10bis2));
		assertTrue(w11.equals(w11bis));
		assertTrue(w11bis.equals(w11bis2));
		assertTrue(w11.equals(w11bis2));
		assertTrue(w12.equals(w12bis));
		assertTrue(w12bis.equals(w12bis2));
		assertTrue(w12.equals(w12bis2));
		assertTrue(w13.equals(w13bis));
		assertTrue(w13bis.equals(w13bis2));
		assertTrue(w13.equals(w13bis2));
		assertTrue(w14.equals(w14bis));
		assertTrue(w14bis.equals(w14bis2));
		assertTrue(w14.equals(w14bis2));
		assertTrue(w15.equals(w15bis));
		assertTrue(w15bis.equals(w15bis2));
		assertTrue(w15.equals(w15bis2));
		assertTrue(w16.equals(w16bis));
		assertTrue(w16bis.equals(w16bis2));
		assertTrue(w16.equals(w16bis2));
		assertTrue(w17.equals(w17bis));
		assertTrue(w17bis.equals(w17bis2));
		assertTrue(w17.equals(w17bis2));
		assertTrue(w18.equals(w18bis));
		assertTrue(w18bis.equals(w18bis2));
		assertTrue(w18.equals(w18bis2));
		assertTrue(w19.equals(w19bis));
		assertTrue(w19bis.equals(w19bis2));
		assertTrue(w19.equals(w19bis2));
		assertTrue(w20.equals(w20bis));
		assertTrue(w20bis.equals(w20bis2));
		assertTrue(w20.equals(w20bis2));
	}
}
