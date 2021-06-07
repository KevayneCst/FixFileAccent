package core.log;

/**
 * Pour créer un objet {@code LevelLog}
 *
 * @author Kévin Constantin
 *
 */
public class LevelLogFactory {

	private LevelLogFactory() {
	}

	public static LevelLog createLevelLog(String s) throws UnknowLevelLogException {
		if (s.equalsIgnoreCase("debug")) {
			return LevelLog.DEBUG;
		} else if (s.equalsIgnoreCase("normal")) {
			return LevelLog.NORMAL;
		} else if (s.equalsIgnoreCase("quiet")) {
			return LevelLog.QUIET;
		} else {
			throw new UnknowLevelLogException("\"" + s + "\": Ce niveau de log n'existe pas !");
		}
	}
}
