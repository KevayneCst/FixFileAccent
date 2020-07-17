package core.grammar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestRegex {

	String a1 = "azertyuiopqsdfghjklmwxcvbn";
	String a2 = "azer tyuiopqsdfghjklmwxcvbn";
	String a3 = "azertyui opqsdfghjklmwxc vbn";
	String a4 = "az ertyuiopqsd fghjklmwxcvbn";
	String a5 = "Azertyuiopqsdfghjklmwxcvbn";
	String a6 = "azertyuiopqsdfghjklmwxcvbN";
	String a7 = "AzertyuiopqsdfghjklmwxcvbN";
	String a8 = "aZert yuiopqsdfghjkLmwxcvbn";
	String a9 = "azer TyuioPqsdfghjklm wxcvbN";
	
	@Test
	void testOnlyLetters() {
		String regex = Regex.REGEX_ONLY_LETTERS;
		assertTrue(a1.matches(regex));
		assertFalse(a2.matches(regex));
		assertFalse(a3.matches(regex));
		assertFalse(a4.matches(regex));
		assertTrue(a5.matches(regex));
		assertTrue(a6.matches(regex));
		assertTrue(a7.matches(regex));
		assertFalse(a8.matches(regex));
		assertFalse(a9.matches(regex));
	}

}
