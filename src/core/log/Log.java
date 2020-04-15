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

	private static File logFile = new File("./log.txt");

	/**
	 * 
	 * @param message
	 * @param tl
	 */
	public static void printLog(String message, TypeLog tl) {
		if (Core.level.getLevel() != 2) { // Si le LevelLog n'est pas QUIET
			PrintWriter out;
			try {
				out = new PrintWriter(new FileWriter(logFile, true));
				Date today = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("'['yyyy-MM-dd | HH:mm:ss:SSSS']'");
				String callingClass = new Exception().getStackTrace()[1].getClassName();
				String[] spliter = callingClass.split("[\\.]");
				out.write(formater.format(today) + " [" + spliter[1] + "|" + tl + "] " + message);
				out.println();
				out.close();

				if (Core.level.getLevel() == 1) { // NORMAL
					if (!tl.equals(TypeLog.DEBUGGING)) {
						System.out.println(formater.format(today) + " [" + spliter[1] + "|" + tl + "] " + message);
					}
				} else { // DEBUG
					System.out.println(formater.format(today) + " [" + spliter[1] + "|" + tl + "] " + message);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
