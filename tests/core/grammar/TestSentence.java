package core.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import core.grammar.french.French;

class TestSentence {

	String str1 = "Tant que durera la crise observ�e, je n'exclus pas de consid�rer la plus grande partie des hypoth�ses du passé, parce que la nature a horreur du vide.";
	String str2 = "Compte tenu de la d�gradation des moeurs que nous constatons, je sugg�re fortement de se souvenir syst�matiquement les choix draconiennes, toutes choses étant égales par ailleurs.";
	String str3 = "Si vous voulez mon avis concernant la morosité induite, il est très important d’analyser les principales voies envisageables, parce qu'il est temps d'agir.";
	String str4 = "Dans le cas particulier de l'inconstance de la société, on ne peut se passer de réorganiser la plus grande partie des organisations matricielles imaginables, parce qu'il s'agit de notre dernière chance.";
	String str5 = "Compte tenu de la dégradation des moeurs de ces derniers temps, nous sommes contraints d’uniformiser l'ensemble des problématiques que nous connaissons, à long terme.";
	String str6 = "Nonobstant l'austérité conjoncturelle, il ne faut pas s'interdire d’expérimenter la totalité des ouvertures s'offrant à nous, pour le futur.";
	String str7 = "N?Pl;d!oà.Az'#ae&rax$pmd= polçf;kLa:/smp dzs";
	String str8 = "Ce n'est pas très légal";
	String str9 = "Il existe, diff�rents num�ros de t�l�phone!";
	String str10 = "Le syst�me n'est pas termin�.";
	String str11 = "if (varié1 && varié2) { return journ�e=4 } else { return soirée=8 }";
	String str12 = "System.out.println(\"Le thème sera fix� le lendemain matin, vers 8heures, avec peut-�tre 10-15minutes de retard\");";
	String str13 = "System.out.println(\"Bonjour\\nJe pense que ça ne va bien aller\\t. Après, je peux me tromper et je l'espère fortement !!!Mais bon, c'est pas �vident d'en être s�r!\");";
	String str14 = "Egalit�!\\n\\n\");";
	String str15 = "!?ùEgalit�!\\n\\n\");";
	String str16 = "\\\\Je suis un commentaire de code. Je contiens un accent � corriger, peut-�tre plus?Quoiqu'il en soit, il faut �tre: prudent, curieux, s�rieux.";
	String str17 = "if ((variable1 && variable2) || (oula || ohoh)) { System.out.println(\"�tat de variable1:\"+variable1+\"\\n état de variable2:\"+variable2+\"\\n �tat de oula:\"+oula+\"\\n état de ohoh:\"+ohoh); }";
	String str18 = "*Il manquait le fait d'ajouter dans la port�e la case d'une";
	String str19 = "* pi�ce d'une autre couleur si la case n'�tait pas libre";
	String str20 = "Il n'y a que deux options possibles aujourd'hui. N'�tant disponible que pour l'un, il faudra renoncer à l'autre.";

	Language lang = new French();

	Sentence s1 = new Sentence(str1);
	Sentence s2 = new Sentence(str2);
	Sentence s3 = new Sentence(str3);
	Sentence s4 = new Sentence(str4);
	Sentence s5 = new Sentence(str5);
	Sentence s6 = new Sentence(str6);
	Sentence s7 = new Sentence(str7);
	Sentence s8 = new Sentence(str8);
	Sentence s9 = new Sentence(str9);
	Sentence s10 = new Sentence(str10);
	Sentence s11 = new Sentence(str11);
	Sentence s12 = new Sentence(str12);
	Sentence s13 = new Sentence(str13);
	Sentence s14 = new Sentence(str14);
	Sentence s15 = new Sentence(str15);
	Sentence s16 = new Sentence(str16);
	Sentence s17 = new Sentence(str17);
	Sentence s18 = new Sentence(str18);
	Sentence s19 = new Sentence(str19);
	Sentence s20 = new Sentence(str20);

	@Test
	void testCorrectSentence() {
		assertEquals(
				"Tant que durera la crise observée, je n'exclus pas de considérer la plus grande partie des hypothèses du passé, parce que la nature a horreur du vide.",
				lang.correctSentence(s1).getTheLine());
		assertEquals(
				"Compte tenu de la dégradation des moeurs que nous constatons, je suggère fortement de se souvenir systématiquement les choix draconiennes, toutes choses étant égales par ailleurs.",
				lang.correctSentence(s2).getTheLine());
		assertEquals(
				"Si vous voulez mon avis concernant la morosité induite, il est très important d’analyser les principales voies envisageables, parce qu'il est temps d'agir.",
				lang.correctSentence(s3).getTheLine());
		assertEquals("Il existe, différents numéros de téléphone!", lang.correctSentence(s9).getTheLine());
		assertEquals("Le système n'est pas terminé.", lang.correctSentence(s10).getTheLine());
		assertEquals("if (varié1 && varié2) { return journée=4 } else { return soirée=8 }",
				lang.correctSentence(s11).getTheLine());
		assertEquals(
				"System.out.println(\"Le thème sera fixé le lendemain matin, vers 8heures, avec peut-être 10-15minutes de retard\");",
				lang.correctSentence(s12).getTheLine());
		assertEquals(
				"System.out.println(\"Bonjour\\nJe pense que ça ne va bien aller\\t. Après, je peux me tromper et je l'espère fortement !!!Mais bon, c'est pas évident d'en être sûr!\");",
				lang.correctSentence(s13).getTheLine());
		assertEquals("Egalit�!\\n\\n\");", lang.correctSentence(s14).getTheLine());
		assertEquals("!?ùEgalit�!\\n\\n\");", lang.correctSentence(s15).getTheLine());
		assertEquals(
				"\\\\Je suis un commentaire de code. Je contiens un accent à corriger, peut-être plus?Quoiqu'il en soit, il faut être: prudent, curieux, sérieux.",
				lang.correctSentence(s16).getTheLine());
		assertEquals(
				"if ((variable1 && variable2) || (oula || ohoh)) { System.out.println(\"état de variable1:\"+variable1+\"\\n état de variable2:\"+variable2+\"\\n état de oula:\"+oula+\"\\n état de ohoh:\"+ohoh); }",
				lang.correctSentence(s17).getTheLine());
		assertEquals("*Il manquait le fait d'ajouter dans la portée la case d'une",
				lang.correctSentence(s18).getTheLine());
		assertEquals("* pièce d'une autre couleur si la case n'était pas libre",
				lang.correctSentence(s19).getTheLine());
		assertEquals(
				"Il n'y a que deux options possibles aujourd'hui. N'étant disponible que pour l'un, il faudra renoncer à l'autre.",
				lang.correctSentence(s20).getTheLine());
	}
}
