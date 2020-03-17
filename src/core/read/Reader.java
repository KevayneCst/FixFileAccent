package core.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.grammar.Sentence;

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
			e.printStackTrace();
		}
		return list;
	}
}
