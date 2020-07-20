package core;

import core.log.Log;
import core.log.TypeLog;

/**
 * Classe de lancement.
 * 
 * @author Kévin Constantin
 *
 */
public class Launcher {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		if (args.length == 1) {
			System.out.println("Lancement en cours...");
			Core c = new Core(args[0]);
			c.start();
			Log.printLog("Travail terminé en " + (System.currentTimeMillis() - startTime) / (double) 1000 + " s", TypeLog.INFO);
		} else {
			System.err.println("Usage: Launcher [pathSrcDirectory]");
			System.out.println("\nVoir le README.md pour toute information complémentaire.");
		}
	}
}
