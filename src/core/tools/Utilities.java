package core.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import core.Config;
import core.grammar.Word;
import core.log.Log;
import core.log.TypeLog;

public class Utilities {

	private Utilities() {
	}
	
	public static void showFiles(List<String> listPathFiles) {
		Log.printLog("Les fichiers suivants vont être corrigés", TypeLog.INFO);
		int i = 1;
		for (String s : listPathFiles) {
			Log.printLog("Fichier n°"+i+ ":"+s,TypeLog.INFO);
			i++;
		}
	}

	public static void waitConfirmation() {
		boolean confirmationGiven = false;
		Log.printLog("Veuillez saisir le mot "+Config.getInstance().getConfirmationKey()+ " pour commencer la correction", TypeLog.INFO);
		while (!confirmationGiven) {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			try {
				System.out.print("$>");
				String readedString = bufferRead.readLine();
				if (readedString.equalsIgnoreCase(Config.getInstance().getConfirmationKey())) {
					confirmationGiven = true;
					Log.printLog("Mot clé correct, démarrage de la correction...", TypeLog.INFO);
				} else {
					Log.printLog("Mot clé incorrect: "+readedString+", veuillez réessayer", TypeLog.INFO);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String debugStringList(List<Word> listWord) {
		StringBuilder sb = new StringBuilder();
		for (Word w : listWord) {
			String currentWord = "["+w.getTheWord()+"]";
			sb.append(currentWord + " ");
		}
		return sb.toString();
	}
}
