package core.grammar;

import java.util.ArrayList;
import java.util.List;

import core.grammar.french.French;

/**
 * Classe implémentant le pattern factory, dont le seul but est de retourner le
 * <code>Language</code> correspondant à la chaîne de caractère passé en
 * paramètre de la fonction <code>createLanguage(String s)</code>. Dans le
 * futur, un potentiel développeur pourra rajouter de nouveaux languages dans
 * cette classe.
 *
 * @author Kévin Constantin
 *
 */
public class LanguageFactory {

	private final List<Language> nonNativeLanguage = new ArrayList<>();

	public LanguageFactory() {
		// Pattern Factory
	}

	public Language createLanguage(String s) throws UnknowLanguageException {
		if (s.equalsIgnoreCase("FR")) {
			return new French();
		} else {
			throw new UnknowLanguageException("\"" + s + "\": Ce language n'est pas supporté ou n'existe pas !");
		}
	}
}
