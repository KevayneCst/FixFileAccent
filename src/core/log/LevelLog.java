package core.log;

/**
 * Les différents niveaux de log (plus ou moins bavard)
 *
 * @author Kévin Constantin
 *
 */
public enum LevelLog {
	DEBUG(0), NORMAL(1), QUIET(2);

	private int level;

	LevelLog(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
