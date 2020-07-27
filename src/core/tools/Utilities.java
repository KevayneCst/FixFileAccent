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

	private static final String PROMPT = "$>";
	private static final BufferedReader INPUT_READER = new BufferedReader(new InputStreamReader(System.in));
	
	private Utilities() {
	}
	
	public static void showFiles(List<String> listPathFiles) {
		Log.printLog("Les fichiers suivants vont être corrigés", TypeLog.ESSENTIAL);
		int i = 1;
		for (String s : listPathFiles) {
			Log.printLog("Fichier n°"+i+ ":"+s,TypeLog.ESSENTIAL);
			i++;
		}
	}

	public static void waitConfirmationKey() {
		boolean confirmationGiven = false;
		Log.printLog("Veuillez saisir le mot "+Config.getInstance().getConfirmationKey()+ " pour commencer la correction", TypeLog.ESSENTIAL);
		while (!confirmationGiven) {
			try {
				System.out.print(PROMPT);
				String readedString = INPUT_READER.readLine();
				if (readedString.equalsIgnoreCase(Config.getInstance().getConfirmationKey())) {
					confirmationGiven = true;
					Log.printLog("Mot clé correct, démarrage de la correction...", TypeLog.ESSENTIAL);
				} else {
					Log.printLog("Mot clé incorrect: "+readedString+", veuillez réessayer", TypeLog.ESSENTIAL);
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
