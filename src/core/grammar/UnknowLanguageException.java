package core.grammar;

/**
 * Classe dérivant <code>Exception</code>, appelée dans le cas où la classe
 * <code>LanguageFactory</code> ne parviendrait pas à résoudre le language qu'on
 * essaie de lui faire créer.
 *
 * @author Kévin Constantin
 *
 */
public class UnknowLanguageException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -613430681718548396L;

	public UnknowLanguageException(String s) {
		super(s, null);
	}
}
