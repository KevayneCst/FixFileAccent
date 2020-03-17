package core.grammar;

import core.grammar.french.French;

public class LanguageFactory {

	public LanguageFactory() {
		//Pattern Factory
	}
	
	public Language createLanguage(String s) throws UnknowLanguage {
		if (s.equalsIgnoreCase("fr")) {
			return new French();
		} else {
			throw new UnknowLanguage("This language is not supported or do not exist !");
		}
	}
}
