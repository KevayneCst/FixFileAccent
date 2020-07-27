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
	private static final File LOG_ERRORS_FILE = new File("./errors.txt");

	/**
	 * Écrit dans le fichier de log et la console le message passé en paramètre
	 * 
	 * @param message Le message à afficher
	 * @param tl      L
	 */
	public static void printLog(String message, TypeLog tl) {
		if (Core.level.getLevel() != LevelLog.QUIET.getLevel() || tl.isPriorityTypeLog()) {
			try {
				Date today = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("'['yyyy-MM-dd | HH:mm:ss:SSSS']'");
				String callingClass = new Exception().getStackTrace()[0].getClassName();
				String[] spliter = callingClass.split("[\\.]");
				String finalMessage = formater.format(today) + " [" + spliter[1] + "|" + tl.toString() + "] " + message;

				writeOnNormalLog(finalMessage);
				if (tl.equals(TypeLog.CRITICAL)) {
					System.err.println(
							"Le programme doit s'arrêter car il a rencontré une erreur trop importante pour pouvoir continuer normalement, voir ci-dessous");
					writeOnErrorLog(finalMessage);
					Thread.sleep(SLEEPING_TIME);
					System.out.println(finalMessage);
					Thread.sleep(SLEEPING_TIME);
					System.exit(1);
				} else if (tl.equals(TypeLog.ESSENTIAL) || (tl.equals(TypeLog.DEBUGGING) && Core.level.equals(LevelLog.DEBUG)) || !Core.level.equals(LevelLog.QUIET)) {
					System.out.println(finalMessage);
					if (tl.isErrorTypeLog()) {
						writeOnErrorLog(finalMessage);
					}
				} 
			} catch (InterruptedException e) {
				System.out.println("Erreur d'interruption lors du sommeil du programme, aucune action n'est requise");
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private static void writeOnNormalLog(String message) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(LOG_FILE, true));
			pw.write(message);
			pw.println();
			pw.close();
		} catch (IOException e) {
			System.out.println(
					"Erreur lors de la lecture du fichier de log, vérifiez que le fichier n'est pas manquant ou ouvert à l'emplacement suivant:\""
							+ LOG_FILE.getAbsolutePath() + "\"");
		}
	}
	
	private static void writeOnErrorLog(String message) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(LOG_ERRORS_FILE, true));
			pw.write(message);
			pw.println();
			pw.close();
		} catch (IOException e) {
			System.out.println(
					"Erreur lors de la lecture du fichier de log d'erreurs, vérifiez que le fichier n'est pas manquant ou ouvert à l'emplacement suivant:\""
							+ LOG_ERRORS_FILE.getAbsolutePath() + "\"");
		}
	}
}
