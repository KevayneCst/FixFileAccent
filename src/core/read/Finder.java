package core.read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire permettant de trouver l'ensemble des fichiers en
 * <code>.java</code> d'un répertoire de travail. <br>
 * <b>ATTENTION</b>: Si le <code>path</code> qui est donné est un dossier situé
 * en hauteur dans le stockage de votre espace de stockage (du genre le dossier
 * <code>Utilisateurs</code> le programme va fouiller l'ensemble du dossier et
 * donc risque de prendre beaucoup de temps selon la taille du dossier. <i>Soyez
 * judicieux dans le choix de votre dossier de départ</i>.
 * 
 * @author Kévin Constantin
 *
 */
public class Finder {

	private List<String> pathFiles;

	public Finder(String projectDirectory) {
		this.pathFiles = new ArrayList<>();
		File mainDir = new File(projectDirectory);
		File[] files = mainDir.listFiles();
		findFiles(files);
	}

	public void findFiles(File[] files) {
		for (File f : files) {
			if (f.isFile()) {
				if (f.getName().endsWith(".java")) {
					pathFiles.add(f.getAbsolutePath());
				}
			} else if (f.isDirectory()) {
				findFiles(f.listFiles());
			}
		}
	}

	public List<String> getPathFiles() {
		return pathFiles;
	}
}
