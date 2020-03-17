package core;

import core.grammar.UnknowLanguage;

public class Launcher {
	public static void main(String[] args) throws UnknowLanguage {
		if (args.length == 2) {
			System.out.println("Lancement en cours...");
			Core c = new Core(args[0], args[1]);
			c.start();
			System.out.println("Travail terminé !");
		} else {
			System.err.println("Usage: Launcher [pathSrcDirectory] [correctionLanguage]");
			System.out.println("Languages supported: \"FR\"");
		}
	}
}
