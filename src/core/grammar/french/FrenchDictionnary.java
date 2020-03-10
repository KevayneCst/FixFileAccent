package core.grammar.french;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Word;

public class FrenchDictionnary {
	
	private static final String PATHFILEDICTIONNARY = "data/FrenchDico.in";
	private Map<Integer,List<Word>> dictionnary;
	
	public FrenchDictionnary() {
		dictionnary=new HashMap<>();
		fillDictionnary();
	}
	
	public void fillDictionnary() {
		File fichier = new File(PATHFILEDICTIONNARY);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fichier));
			String line;
			while ((line = br.readLine()) != null) {
				dictionnary.computeIfAbsent(line.length(), k -> new ArrayList<>()).add(new Word(line));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<Integer,List<Word>> getDictionnary() {
		return dictionnary;
	}
}
