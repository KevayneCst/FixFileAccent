package core.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import core.Config;
import core.grammar.Regex;
import core.grammar.Word;
import core.grammar.WordCorrupted;
import core.log.Log;
import core.log.TypeLog;

/**
 * Classe contenant des méthodes utilitaires comme de l'affichage, saisie au
 * clavier...
 *
 * @author Kévin Constantin
 *
 */
public class Utilities {

	private static final String PROMPT = "$>";
	private static final BufferedReader INPUT_READER = new BufferedReader(new InputStreamReader(System.in));

	private Utilities() {
	}

	/**
	 * Méthode qui va lister l'ensemble des éléments de la liste passée en paramètre
	 * <code>listPathFiles</code> et les afficher en colonne sous le format suivant
	 * :<br>
	 * <ul>
	 * <li>Fichier n°1:mydir/myseconddir/myfile.java</li>
	 * <li>Fichier n°2:mydir/myseconddir/amazing.java</li>
	 * <li>...</li>
	 * <li>Fichier n°23:mydir/myotherdir/wow.java</li>
	 * </ul>
	 *
	 * @param listPathFiles La liste des chemins des fichiers à afficher
	 */
	public static void showFiles(List<String> listPathFiles) {
		Log.printLog("Les fichiers suivants vont être corrigés", TypeLog.ESSENTIAL);
		int i = 1;
		for (final String s : listPathFiles) {
			Log.printLog("Fichier n°" + i + ":" + s, TypeLog.ESSENTIAL);
			i++;
		}
	}

	public static void waitEntry() {
		Log.printLog("Appuyez sur la touche \"ENTRÉE\" pour continuer...", TypeLog.ESSENTIAL);
		while (true) {
			System.out.print(PROMPT);
			try {
				if (INPUT_READER.readLine().isBlank()) {
					return;
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Méthode qui va demander de saisir le mot de confirmation (trouvable et
	 * paramètrable dans <code>config.properties</code>). Tant que le mot n'aura pas
	 * été saisi ou que la saisie est incorrecte, le programme se "bloque" dans
	 * cette méthode.
	 *
	 */
	public static void waitConfirmationKey() {
		boolean confirmationGiven = false;
		Log.printLog("Veuillez saisir le mot \"" + Config.getInstance().getConfirmationKey()
				+ "\" pour commencer la correction", TypeLog.ESSENTIAL);
		while (!confirmationGiven) {
			try {
				System.out.print(PROMPT);
				final String readedString = INPUT_READER.readLine();
				if (readedString.equalsIgnoreCase(Config.getInstance().getConfirmationKey())) {
					confirmationGiven = true;
					Log.printLog("Mot clé correct, mise en application de la correction...", TypeLog.ESSENTIAL);
				} else {
					Log.printLog("Mot clé incorrect: " + readedString + ", veuillez réessayer", TypeLog.ESSENTIAL);
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Méthode qui va demander à l'utilisateur (si dans
	 * <code>config.properties</code>, <code>confirmWord = true</code>) de choisir
	 * parmis tous les mots de la liste <code>wordsToDisplay</code>, lequel
	 * correspond le mieux à une correction de <code>toCorrect</code>.<br>
	 * <br>
	 *
	 * Pour rappel, tous les mots de <code>wordsToDisplay</code> sont "corrects" si
	 * on les appliquent à <code>toCorrect</code>, exemple : <br>
	 * <i>toCorrect</i> = <code>�tre</code><br>
	 * <i>wordsToDisplay</i> = <code>âtre</code> , <code>être</code><br>
	 * <br>
	 * On remarque bien que les mots qui composent <code>wordsToDisplay</code> sont
	 * cohérant avec un remplacement de l'un d'entre eux avec <code>toCorrect</code>
	 *
	 * @param toCorrect      Le mot corrompu cible
	 * @param wordsToDisplay Les mots corrects pour la correction entrenant une
	 *                       ambiguïté, une hésitation sur qui choisir pour corriger
	 *                       <code>toCorrect</code>
	 * @return <code>Word</code>
	 */
	public static WordCorrupted waitConfirmationWord(Word toCorrect, List<WordCorrupted> wordsToDisplay) {
		Log.printLog("Veuillez saisir le numéro correspondant au mot voulu pour la correction de \""
				+ toCorrect.getTheWord() + "\"", TypeLog.ESSENTIAL);
		while (true) {
			try {
				int index = 1;
				for (final WordCorrupted w : wordsToDisplay) {
					Log.printLog(index + ":\"" + w.getTheWord() + "\"", TypeLog.ESSENTIAL);
					index++;
				}
				System.out.print(PROMPT);
				final String readedString = INPUT_READER.readLine();
				if (readedString.matches(Regex.REGEX_ONLY_DIGITS)) {
					final int readedValue = Integer.parseInt(readedString);
					if (readedValue < 1 || readedValue > index - 1) {
						Log.printLog(
								"Le nombre saisi est au-deça ou au-delà des numéros correspondant aux mots affichés: "
										+ readedValue,
								TypeLog.ESSENTIAL);
					} else {
						return wordsToDisplay.get(readedValue - 1);
					}
				} else {
					Log.printLog("Saisie incorrecte: " + readedString + " n'est pas un chiffre", TypeLog.ESSENTIAL);
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static WordCorrupted manualCorrection(WordCorrupted w) {
		Log.printLog("Correction manuelle pour le mot \"" + w.getTheWord()
				+ "\", si vous ne savez pas, appuyez sur la touche \"ENTRÉE\" sans rien saisir au clavier avant",
				TypeLog.ESSENTIAL);
		System.out.print(PROMPT);
		try {
			final String readedString = INPUT_READER.readLine();
			return readedString.isBlank() ? null
					: new WordCorrupted(readedString, w.getIndexBeginInSentence(), w.getIndexEndInSentence());
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String debugStringList(List<WordCorrupted> listWord) {
		final StringBuilder sb = new StringBuilder();
		for (final Word w : listWord) {
			final String currentWord = "[" + w.getTheWord() + "]";
			sb.append(currentWord + " ");
		}
		return sb.toString();
	}
}
