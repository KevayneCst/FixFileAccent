package core.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import core.grammar.Sentence;

/**
 * Classe utilitaire ayant pour but de lire un fichier dont on donnera le chemin
 * d'accès, afin de créer des phrases exploitables pour le reste des classes.
 * 
 * @author Kévin Constantin
 *
 */
public class Reader {

	public Reader() {
		// Ne fait rien
	}

	public List<Sentence> readFile(String pathFile) {
		List<Sentence> list = new ArrayList<>();
		File fichier = new File(pathFile);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fichier));
			String line;
			while ((line = br.readLine()) != null) {
				list.add(new Sentence(line));
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture du fichier: \"" + pathFile + "\":");
			e.printStackTrace();
		}
		return list;
	}
}
