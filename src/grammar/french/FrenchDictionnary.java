package grammar.french;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import grammar.Word;

public class FrenchDictionnary {
	
	private static final String PATHFILEDICTIONNARY = "data/FrenchDico.in";
	private List<Word> dictionnary;
	
	public FrenchDictionnary() {
		dictionnary=new ArrayList<>();
		fillDictionnary();
	}
	
	public void fillDictionnary() {
		File fichier = new File(PATHFILEDICTIONNARY);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fichier));
			String line;
			while ((line = br.readLine()) != null) {
				dictionnary.add(new Word(line));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Word> getDictionnary() {
		return dictionnary;
	}
}
