package core.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.Core;

/**
 * Classe permettant de générer des logs dans un fichier et d'avoir un feedback
 * dans la console pour l'utilisateur
 * 
 * @author Kévin Constantin
 *
 */
public class Log {

	private Log() {
	}
	
	private static final long SLEEPING_TIME = 2000;
	private static final File LOG_FILE = new File("./log.txt");

	/**
	 * Écrit dans le fichier de log et la console le message passé en paramètre
	 * @param message Le message à afficher
	 * @param tl L
	 */
	public static void printLog(String message, TypeLog tl) {
		if (Core.level.getLevel() != 2 || tl.equals(TypeLog.CRITICAL)) { // Si le LevelLog n'est pas QUIET ou que c'est une erreur CRITIQUE
			PrintWriter out;
			try {
				out = new PrintWriter(new FileWriter(LOG_FILE, true));
				Date today = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("'['yyyy-MM-dd | HH:mm:ss:SSSS']'");
				String callingClass = new Exception().getStackTrace()[1].getClassName();
				String[] spliter = callingClass.split("[\\.]");
				out.write(formater.format(today) + " [" + spliter[1] + "|" + tl.toString() + "] " + message);
				out.println();
				out.close();
				if (tl.equals(TypeLog.CRITICAL)) {
					System.err.println("Le programme doit s'arrêter car il a rencontré une erreur trop importante pour pouvoir continuer normalement, voir ci-dessous");
					Thread.sleep(SLEEPING_TIME);
					System.out.println(formater.format(today) + " [" + spliter[1] + "|" + tl.toString() + "] " + message);
					Thread.sleep(SLEEPING_TIME);
					System.exit(1);
				} else if (Core.level.getLevel() == 1) { // NORMAL
					if (!tl.equals(TypeLog.DEBUGGING)) {
						System.out.println(formater.format(today) + " [" + spliter[1] + "|" + tl.toString() + "] " + message);
					}
				} else { // DEBUG
					System.out.println(formater.format(today) + " [" + spliter[1] + "|" + tl.toString() + "] " + message);
				}
			} catch (IOException e) {
				System.out.println("Erreur lors de la lecture du fichier de log, vérifiez que le fichier n'est pas manquant ou ouvert à l'emplacement suivant:\""+LOG_FILE.getAbsolutePath()+"\"");
			} catch (InterruptedException e) {
				System.out.println("Erreur d'interruption lors du sommeil du programme, aucune action n'est réquise");
				Thread.currentThread().interrupt();
			}
		}
	}
}
