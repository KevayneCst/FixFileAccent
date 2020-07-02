package core;

import core.grammar.UnknowLanguageException;
import core.log.UnknowLevelLogException;

/**
 * Classe de lancement.
 * 
 * @author Kévin Constantin
 *
 */
public class Launcher {
	public static void main(String[] args) throws UnknowLanguageException, UnknowLevelLogException {
		long startTime = System.currentTimeMillis();
		if (args.length == 2) {
			System.out.println("Lancement en cours...");
			Core c = new Core(args[0], args[1]);
			c.start();
			System.out.println("Travail terminé en " + (System.currentTimeMillis() - startTime) / (double) 1000 + " s");
		} else if (args.length == 3) {
			System.out.println("Lancement en cours...");
			Core c = new Core(args[0], args[1], args[2]);
			c.start();
			System.out.println("Travail terminé en " + (System.currentTimeMillis() - startTime) / (double) 1000 + " s");
		} else {
			System.err.println("Usage: Launcher [pathSrcDirectory] [correctionLanguage] [[OPTIONAL]levelLog]");
			System.out.println("Languages supportés: \"FR\"");
			System.out.println("Niveaux de logs: \"DEBUG\" OU \"NORMAL\" OU \"QUIET\"");
			System.out.println("\nVoir le README.md pour toute information complémentaire.");
		}
	}
}
