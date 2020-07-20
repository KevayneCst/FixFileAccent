package core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import core.grammar.Language;
import core.grammar.LanguageFactory;
import core.grammar.UnknowLanguageException;
import core.grammar.french.French;
import core.log.LevelLog;
import core.log.LevelLogFactory;
import core.log.Log;
import core.log.TypeLog;
import core.log.UnknowLevelLogException;

/**
 * Classe Singleton, contient l'ensemble des informations stockés dans le
 * fichier <code>config.properties</code>, relatif au comportement du programme.
 * 
 * @author Kévin Constantin
 *
 */
public class Config {

	private static Config instance = new Config();
	private File configFile;
	private Properties props;

	private String language;
	private String levelLog;
	private String applyCorrection;
	private String confirmFiles;
	private String confirmKey;

	private Config() {
		this.configFile = new File("./config.properties");
		this.props = new Properties();
		loadProperties();
	}

	private void loadProperties() {
		try {
			FileReader reader = new FileReader(configFile);
			props.load(reader);
			getProperties();
			reader.close();
		} catch (IOException e) {
			Log.printLog(
					"Erreur lors de la lecture du fichier de configuration, vérifiez l'existance du fichier à l'emplacement suivant:\""
							+ configFile.getAbsolutePath() + "\"",
					TypeLog.CRITICAL);
		}
	}

	private void getProperties() {
		this.language = getProperty("language", "FR");
		this.levelLog = getProperty("levelLog", "QUIET");
		this.applyCorrection = getProperty("applyCorrection", "TRUE");
		this.confirmFiles = getProperty("confirmFiles", "TRUE");
		this.confirmKey = getProperty("confirmKey", "CONFIRM");
	}

	private String getProperty(String key, String defaultValue) {
		String value = props.getProperty(key, defaultValue);
		return value.isBlank() ? defaultValue : value;
	}

	public static Config getInstance() {
		return instance;
	}

	public void showProperties() {
		Log.printLog("====== Paramètres de configuration ======", TypeLog.DEBUGGING);
		Log.printLog(String.format("Langage utilisé      %s %s", ":", language), TypeLog.DEBUGGING);
		Log.printLog(String.format("Niveau de log        %s %s", ":", levelLog), TypeLog.DEBUGGING);
		Log.printLog(String.format("Correction activée   %s %s", ":", applyCorrection), TypeLog.DEBUGGING);
		Log.printLog(String.format("Confirmation activée %s %s", ":", confirmFiles), TypeLog.DEBUGGING);
		Log.printLog(String.format("Mot de confirmation  %s %s", ":", confirmKey), TypeLog.DEBUGGING);
	}

	public Language getLanguage() {
		LanguageFactory lf = new LanguageFactory();
		Language l;
		try {
			l = lf.createLanguage(language);
		} catch (UnknowLanguageException e) {
			l = new French();
		}
		return l;
	}

	public LevelLog getLevelLog() {
		try {
			return LevelLogFactory.createLevelLog(levelLog);
		} catch (UnknowLevelLogException e) {
			return LevelLog.QUIET;
		}
	}

	public boolean isApplyCorrection() {
		return Boolean.valueOf(applyCorrection);
	}
	
	public boolean isConfirmFiles() {
		return Boolean.valueOf(confirmFiles);
	}
	
	public String getConfirmationKey() {
		return confirmKey;
	}
}
