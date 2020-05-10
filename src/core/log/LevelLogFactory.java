package core.log;

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
