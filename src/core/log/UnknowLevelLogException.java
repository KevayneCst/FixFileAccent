package core.log;

/**
 * Classe dérivant <code>Exception</code>, appelée dans le cas où la classe
 * <code>LevelLogFactory</code> ne parviendrait pas à résoudre le niveau de log
 * qu'on essaie de lui faire créer.
 *
 * @author Kévin Constantin
 *
 */
public class UnknowLevelLogException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -6607079321857741052L;

	public UnknowLevelLogException(String s) {
		super(s, null);
	}
}
