package core;

public class Launcher {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: Launcher [pathSrcDirectory]");
		} else {
			System.out.println("Lancement en cours...");
			Core c = new Core(args[0]);
			c.start();
			System.out.println("Travail terminé !");
		}
	}
}
