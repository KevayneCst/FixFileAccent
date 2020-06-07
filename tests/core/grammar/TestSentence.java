package core.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import core.grammar.french.French;

class TestSentence {

	String str1 = "Tant que durera la crise observée, je n'exclus pas de considérer la plus grande partie des hypothèses du passé, parce que la nature a horreur du vide.";
	String str2 = "Compte tenu de la dégradation des moeurs que nous constatons, je suggère fortement de se souvenir systématiquement les choix draconiennes, toutes choses étant égales par ailleurs.";
	String str3 = "Si vous voulez mon avis concernant la morosité induite, il est très important d’analyser les principales voies envisageables, parce qu'il est temps d'agir.";
	String str4 = "Dans le cas particulier de l'inconstance de la société, on ne peut se passer de réorganiser la plus grande partie des organisations matricielles imaginables, parce qu'il s'agit de notre dernière chance.";
	String str5 = "Compte tenu de la dégradation des moeurs de ces derniers temps, nous sommes contraints d’uniformiser l'ensemble des problématiques que nous connaissons, à long terme.";
	String str6 = "Nonobstant l'austérité conjoncturelle, il ne faut pas s'interdire d’expérimenter la totalité des ouvertures s'offrant à nous, pour le futur.";
	String str7 = "N?Pl;d!oà.Az'#ae&rax$pmd= polçf;kLa:/smp dzs";
	String str8 = "Ce n'est pas très légal";

	String fullCorrection1 = "Il existe, diff�rents num�ros de t�l�phone!";
	String fullCorrection2 = "Le syst�me n'est pas termin�.";
	String fullCorrection3 = "if (varié1 && varié2) { return journ�e=4 } else { return soirée=8 }";
	String fullCorrection4 = "System.out.printl(\"Le thème sera fix� le lendemain matin, vers 8heures, avec peut-�tre 10-15minutes de retard\");";

	Language lang = new French();

	Sentence s1 = new Sentence(str1);
	Sentence s2 = new Sentence(str2);
	Sentence s3 = new Sentence(str3);
	Sentence s4 = new Sentence(str4);
	Sentence s5 = new Sentence(str5);
	Sentence s6 = new Sentence(str6);
	Sentence s7 = new Sentence(str7);
	Sentence s8 = new Sentence(str8);

	Sentence fc1 = new Sentence(fullCorrection1);
	Sentence fc2 = new Sentence(fullCorrection2);
	Sentence fc3 = new Sentence(fullCorrection3);
	Sentence fc4 = new Sentence(fullCorrection4);

	@Test
	void testInit() {
		assertEquals(s1.getTheLine(), str1);
		assertEquals(s2.getTheLine(), str2);
		assertEquals(s3.getTheLine(), str3);
		assertEquals(s4.getTheLine(), str4);
		assertEquals(s5.getTheLine(), str5);
		assertEquals(s6.getTheLine(), str6);
		assertEquals(s7.getTheLine(), str7);
		assertEquals(s8.getTheLine(), str8);
	}

	@Test
	void testInit2() { // Vérification de la purification des mots
		assertEquals(3, s7.getPurifiedWords().size());
		assertEquals("NPldoàAzaeraxpmd", s7.getPurifiedWords().get(0).getTheWord());
		assertEquals("polçfkLasmp", s7.getPurifiedWords().get(1).getTheWord());
		assertEquals("dzs", s7.getPurifiedWords().get(2).getTheWord());
	}

	@Test
	void testSpaceSplitSentence() {
		assertEquals(27, s1.spaceSplitSentence().size());
		assertEquals(26, s2.spaceSplitSentence().size());
		assertEquals(23, s3.spaceSplitSentence().size());
		assertEquals(31, s4.spaceSplitSentence().size());
		assertEquals(24, s5.spaceSplitSentence().size());
		assertEquals(19, s6.spaceSplitSentence().size());
		assertEquals(3, s7.spaceSplitSentence().size());
	}

	@Test
	void testCorrectSentence() {
		assertEquals("Il existe, différents numéros de téléphone!",
				fc1.rebuildSentence(lang.correctSentence(fc1)).getTheLine());
		assertEquals("Le système n'est pas terminé.", fc2.rebuildSentence(lang.correctSentence(fc2)).getTheLine());
		assertEquals("if (varié1 && varié2) { return journée=4 } else { return soirée=8 }",
				fc3.rebuildSentence(lang.correctSentence(fc3)).getTheLine());
		assertEquals(
				"System.out.printl(\"Le thème sera fixé le lendemain matin, vers 8heures, avec peut-être 10-15minutes de retard\");",
				fc4.rebuildSentence(lang.correctSentence(fc4)).getTheLine());
	}
}
