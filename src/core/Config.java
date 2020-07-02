package core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import core.grammar.Language;
import core.grammar.LanguageFactory;
import core.grammar.UnknowLanguageException;
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
		this.language = props.getProperty("language", "FR");
		this.levelLog = props.getProperty("levelLog", "QUIET");
		this.applyCorrection = props.getProperty("applyCorrection", "TRUE");
	}

	public static Config getInstance() {
		return instance;
	}

	public Language getLanguage() throws UnknowLanguageException {
		LanguageFactory lf = new LanguageFactory();
		return lf.createLanguage(language);
	}

	public LevelLog getLevelLog() throws UnknowLevelLogException {
		return LevelLogFactory.createLevelLog(levelLog);
	}

	public boolean isApplyCorrection() {
		return Boolean.valueOf(applyCorrection);
	}
}
