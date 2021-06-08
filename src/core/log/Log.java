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
		if (!Core.level.equals(LevelLog.QUIET) || tl.isPriorityTypeLog()) {
			try {
				final Date today = new Date();
				final SimpleDateFormat formater = new SimpleDateFormat("'['yyyy-MM-dd | HH:mm:ss:SSSS']'");
				final String finalMessage = formater.format(today) + " [" + tl.toString() + "] " + message;
				writeOnNormalLog(finalMessage);
				if (tl.equals(TypeLog.CRITICAL)) {
					System.err.println(
							"Le programme doit s'arrêter car il a rencontré une erreur trop importante pour pouvoir continuer normalement, voir ci-dessous");
					writeOnErrorLog(finalMessage);
					Thread.sleep(SLEEPING_TIME);
					System.out.println(finalMessage);
					Thread.sleep(SLEEPING_TIME);
					System.exit(1);
				} else if (tl.isErrorTypeLog()) {
					System.err.println(finalMessage);
					writeOnErrorLog(finalMessage);
				} else if (tl.equals(TypeLog.ESSENTIAL) || tl.equals(TypeLog.INFO)
						|| tl.equals(TypeLog.DEBUGGING) && Core.level.equals(LevelLog.DEBUG)) {
					System.out.println(finalMessage);
				}
			} catch (final InterruptedException e) {
				System.out.println("Erreur d'interruption lors du sommeil du programme, aucune action n'est requise");
				Thread.currentThread().interrupt();
			}
		}
	}

	private static void writeOnNormalLog(String message) {
		writeOnFile(LOG_FILE, message);
	}

	private static void writeOnErrorLog(String message) {
		writeOnFile(LOG_ERRORS_FILE, message);
	}

	private static void writeOnFile(File file, String message) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(file, true));
			pw.write(message);
			pw.println();
			pw.close();
		} catch (final IOException e) {
			System.out.println(
					"Erreur lors de la lecture du fichier de log, vérifiez que le fichier n'est pas manquant ou ouvert à l'emplacement suivant:\""
							+ file.getAbsolutePath() + "\"");
		}
	}
}
