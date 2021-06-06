package core.log;

/**
 * Énumération représentant les différents types de log qu'on peut retrouver
 * dans le programme. Voici une description pour chacun d'entre eux :
 * <ul>
 * <li><code>DEBUGGING</code> informations
 * <ul>
 * <li>Affecté par le mode silence : <b>OUI</b></li>
 * </ul>
 * </li>
 * <li><code>INFO</code> : informations générales sur l'avancement de la
 * correction, pour indiquer globalement ce que fait le programme à quel moment.
 * <ul>
 * <li>Affecté par le mode silence : <b>OUI</b></li>
 * </ul>
 * </li>
 * <li><code>ESSENTIAL</code> : informations essentiels à visualiser, elles sont
 * présentes lorsque l'utilisateur demander à visualiser les fichiers à
 * corriger, ou encore le moment où il doit saisir le mot de confirmation, en
 * bref, toutes les informations où l'utilisateur est le commenditaire
 * d'affichage.
 * <ul>
 * <li>Affecté par le mode silence : <b>NON</b></li>
 * </ul>
 * </li>
 * <li><code>WARNING</code> : erreur relativement faible qui ne nécessite aucune
 * action de la part de l'utilisateur. Néanmoins, certaines opérations risquent
 * de ne pas s'éxecuter normalement
 * <ul>
 * <li>Affecté par le mode silence : <b>OUI</b></li>
 * </ul>
 * </li>
 * <li><code>SEVERE</code> : erreur importante mais qui ne nécessite aucune
 * action immédiate de la part de l'utilisateur.
 * <ul>
 * <li>Affecté par le mode silence : <b>OUI</b></li>
 * </ul>
 * </li>
 * <li><code>CRITICAL</code> : erreur qui, si elle apparaît, doit faire
 * s'arrêter le programme. Il est donc essentiel de visualiser cette information
 * pour que l'utilisateur comprenne ce qu'il s'est passé.
 * <ul>
 * <li>Affecté par le mode silence : <b>NON</b></li>
 * </ul>
 * </li>
 * </ul>
 *
 * @author Kévin Constantin
 *
 */
public enum TypeLog {
	DEBUGGING, INFO, ESSENTIAL, WARNING, SEVERE, CRITICAL;

	/**
	 * <code>WARNING</code> OU <code>SEVERE</code>
	 *
	 * @return
	 */
	public boolean isErrorTypeLog() {
		return equals(WARNING) || equals(SEVERE);
	}

	/**
	 * <code>ESSENTIAL</code> OU <code>CRITICAL</code>
	 *
	 * @return
	 */
	public boolean isPriorityTypeLog() {
		return equals(ESSENTIAL) || equals(CRITICAL);
	}
}
