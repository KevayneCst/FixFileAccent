package core.grammar.french;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import core.grammar.Word;

class TestFrench {

	French f = new French();
	String s1 = "bonjour";
	String s2 = "int�ressement";
	String s3 = "caract�re";
	String s4 = "r�guli�rement";
	String s5 = "cr�ation";
	String s6 = "l'am�ne";
	String s7 = "l'arm�e";
	String s8 = "l'�cran";
	String s9 = "l'identit�";
	String s10 = "s'�carter";
	String s11 = "l�";
	String s12 = "m�dico-professionnelles";
	String s13 = "�";
	String s14 = "correspondanci�res";
	String s15 = "d�n�";
	String s16 = "surd�velopp�es";
	String s17 = "radiom�tallographie";
	String s18 = "exsud�rent";
	String s19 = "d�";
	String s20 = "d�parasitassions";
	String s21 = "go�";
	String s22 = "arri�re-grand-tantes";
	String s23 = "m�";
	String s24 = "d�cl�ricaliserions";
	String s25 = "contre-r�volutionnaire";
	String s26 = "courrou�assions";
	String s27 = "d�chromeront";
	String s28 = "m�dico-p�dagogiques";
	String s29 = "�ta";
	String s30 = "interchangeabilit�";
	String s31 = "controvers�t";
	String s32 = "f�d�raliseraient";
	String s33 = "tch�coslovaques";
	String s34 = "botanis�";
	String s35 = "pagnot�rent";
	String s36 = "rinc�s";
	String s37 = "ins�r�t";
	String s38 = "d�senflassions";
	String s39 = "d�sembourgeois�rent";
	String s40 = "compr�hensibilit�s";
	String s41 = "d�christianisassions";
	String s42 = "d�sapprovisionnassiez";
	String s43 = "s�l�nographiques";
	String s44 = "neutralis�";
	String s45 = "substantialit�s";
	String s46 = "institutionnalis�rent";
	String s47 = "�gravillonnasses";
	String s48 = "d�personnaliserait";
	String s49 = "interf�rom�tre";
	String s50 = "radiot�l�graphiassent";

	@Test
	void testWord() {
		assertEquals("bonjour", f.matchWordWithDictionnary(new Word(s1)).getTheWord());
		assertEquals("intéressement", f.matchWordWithDictionnary(new Word(s2)).getTheWord());
		assertEquals("caractère", f.matchWordWithDictionnary(new Word(s3)).getTheWord());
		assertEquals("régulièrement", f.matchWordWithDictionnary(new Word(s4)).getTheWord());
		assertEquals("création", f.matchWordWithDictionnary(new Word(s5)).getTheWord());
		assertEquals("l'amène", f.matchWordWithDictionnary(new Word(s6)).getTheWord());
		assertEquals("l'armée", f.matchWordWithDictionnary(new Word(s7)).getTheWord());
		assertEquals("l'écran", f.matchWordWithDictionnary(new Word(s8)).getTheWord());
		assertEquals("l'identité", f.matchWordWithDictionnary(new Word(s9)).getTheWord());
		assertEquals("s'écarter", f.matchWordWithDictionnary(new Word(s10)).getTheWord());
		assertEquals("là", f.matchWordWithDictionnary(new Word(s11)).getTheWord());
		assertEquals("médico-professionnelles", f.matchWordWithDictionnary(new Word(s12)).getTheWord());
		assertEquals("à", f.matchWordWithDictionnary(new Word(s13)).getTheWord());
		assertEquals("correspondancières", f.matchWordWithDictionnary(new Word(s14)).getTheWord());
		assertEquals("dîné", f.matchWordWithDictionnary(new Word(s15)).getTheWord());
		assertEquals("surdéveloppées", f.matchWordWithDictionnary(new Word(s16)).getTheWord());
		assertEquals("radiométallographie", f.matchWordWithDictionnary(new Word(s17)).getTheWord());
		assertEquals("exsudèrent", f.matchWordWithDictionnary(new Word(s18)).getTheWord());
		assertEquals("dé", f.matchWordWithDictionnary(new Word(s19)).getTheWord());
		assertEquals("déparasitassions", f.matchWordWithDictionnary(new Word(s20)).getTheWord());
		assertEquals("goï", f.matchWordWithDictionnary(new Word(s21)).getTheWord());
		assertEquals("arrière-grand-tantes", f.matchWordWithDictionnary(new Word(s22)).getTheWord());
		assertEquals("mû", f.matchWordWithDictionnary(new Word(s23)).getTheWord());
		assertEquals("décléricaliserions", f.matchWordWithDictionnary(new Word(s24)).getTheWord());
		assertEquals("contre-révolutionnaire", f.matchWordWithDictionnary(new Word(s25)).getTheWord());
		assertEquals("courrouçassions", f.matchWordWithDictionnary(new Word(s26)).getTheWord());
		assertEquals("déchromeront", f.matchWordWithDictionnary(new Word(s27)).getTheWord());
		assertEquals("médico-pédagogiques", f.matchWordWithDictionnary(new Word(s28)).getTheWord());
		assertEquals("ôta", f.matchWordWithDictionnary(new Word(s29)).getTheWord());
		assertEquals("interchangeabilité", f.matchWordWithDictionnary(new Word(s30)).getTheWord());
		assertEquals("controversât", f.matchWordWithDictionnary(new Word(s31)).getTheWord());
		assertEquals("fédéraliseraient", f.matchWordWithDictionnary(new Word(s32)).getTheWord());
		assertEquals("tchécoslovaques", f.matchWordWithDictionnary(new Word(s33)).getTheWord());
		assertEquals("botanisé", f.matchWordWithDictionnary(new Word(s34)).getTheWord());
		assertEquals("pagnotèrent", f.matchWordWithDictionnary(new Word(s35)).getTheWord());
		assertEquals("rincés", f.matchWordWithDictionnary(new Word(s36)).getTheWord());
		assertEquals("insérât", f.matchWordWithDictionnary(new Word(s37)).getTheWord());
		assertEquals("désenflassions", f.matchWordWithDictionnary(new Word(s38)).getTheWord());
		assertEquals("désembourgeoisèrent", f.matchWordWithDictionnary(new Word(s39)).getTheWord());
		assertEquals("compréhensibilités", f.matchWordWithDictionnary(new Word(s40)).getTheWord());
		assertEquals("déchristianisassions", f.matchWordWithDictionnary(new Word(s41)).getTheWord());
		assertEquals("désapprovisionnassiez", f.matchWordWithDictionnary(new Word(s42)).getTheWord());
		assertEquals("sélénographiques", f.matchWordWithDictionnary(new Word(s43)).getTheWord());
		assertEquals("neutralisé", f.matchWordWithDictionnary(new Word(s44)).getTheWord());
		assertEquals("substantialités", f.matchWordWithDictionnary(new Word(s45)).getTheWord());
		assertEquals("institutionnalisèrent", f.matchWordWithDictionnary(new Word(s46)).getTheWord());
		assertEquals("égravillonnasses", f.matchWordWithDictionnary(new Word(s47)).getTheWord());
		assertEquals("dépersonnaliserait", f.matchWordWithDictionnary(new Word(s48)).getTheWord());
		assertEquals("interféromètre", f.matchWordWithDictionnary(new Word(s49)).getTheWord());
		assertEquals("radiotélégraphiassent", f.matchWordWithDictionnary(new Word(s50)).getTheWord());
	}
}
