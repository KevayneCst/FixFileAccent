package core.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestSentence {

	String str1 = "Tant que durera la crise observée, je n'exclus pas de considérer la plus grande partie des hypothèses du passé, parce que la nature a horreur du vide.";
	String str2 = "Compte tenu de la dégradation des moeurs que nous constatons, je suggère fortement de se souvenir systématiquement les choix draconiennes, toutes choses étant égales par ailleurs.";
	String str3 = "Si vous voulez mon avis concernant la morosité induite, il est très important d’analyser les principales voies envisageables, parce qu'il est temps d'agir.";
	String str4 = "Dans le cas particulier de l'inconstance de la société, on ne peut se passer de réorganiser la plus grande partie des organisations matricielles imaginables, parce qu'il s'agit de notre dernière chance.";
	String str5 = "Compte tenu de la dégradation des moeurs de ces derniers temps, nous sommes contraints d’uniformiser l'ensemble des problématiques que nous connaissons, à long terme.";
	String str6 = "Nonobstant l'austérité conjoncturelle, il ne faut pas s'interdire d’expérimenter la totalité des ouvertures s'offrant à nous, pour le futur.";
	String str7 = "N?Pl;d!oà.Az'#ae&rax$pmd= polçf;kLa:/smp dzs";

	Sentence s1 = new Sentence(str1);
	Sentence s2 = new Sentence(str2);
	Sentence s3 = new Sentence(str3);
	Sentence s4 = new Sentence(str4);
	Sentence s5 = new Sentence(str5);
	Sentence s6 = new Sentence(str6);
	Sentence s7 = new Sentence(str7);

	@Test
	void testInit() {
		assertEquals(s1.getTheLine(), str1);
		assertEquals(s2.getTheLine(), str2);
		assertEquals(s3.getTheLine(), str3);
		assertEquals(s4.getTheLine(), str4);
		assertEquals(s5.getTheLine(), str5);
		assertEquals(s6.getTheLine(), str6);
		assertEquals(s7.getTheLine(), str7);
	}
	
	@Test
	void testSpaceSplitSentence() {
		assertEquals(27,s1.spaceSplitSentence().size());
		assertEquals(26,s2.spaceSplitSentence().size());
		assertEquals(23,s3.spaceSplitSentence().size());
		assertEquals(31,s4.spaceSplitSentence().size());
		assertEquals(24,s5.spaceSplitSentence().size());
		assertEquals(19,s6.spaceSplitSentence().size());
		assertEquals(3,s7.spaceSplitSentence().size());
	}

	@Test
	void testSentenceIntoWords() {
		assertEquals(28, s1.getWords().size());
		assertEquals(26, s2.getWords().size());
		assertEquals(26, s3.getWords().size());
		assertEquals(34, s4.getWords().size());
		assertEquals(26, s5.getWords().size());
		assertEquals(23, s6.getWords().size());
		assertEquals(11, s7.getWords().size());
	}

}
