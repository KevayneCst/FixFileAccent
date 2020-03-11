package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Sentence;
import core.grammar.french.French;
import core.read.Finder;
import core.read.Reader;

public class Core {

	private Finder f;
	private static French fr = new French();
	private static Reader r = new Reader();

	public Core(String pathDirectory) {
		this.f = new Finder(pathDirectory);
	}
	
	public void start() {
		List<String> listPath = f.getPathFiles();
		Map<String,List<Sentence>> beforeCorrection = new HashMap<>();
		Map<String,List<Sentence>> afterCorrection = new HashMap<>();
		for (String s : listPath) {
			beforeCorrection.put(s, r.readFile(s));
		}
		
		for (Map.Entry<String,List<Sentence>> hm : beforeCorrection.entrySet()) {
			for (Sentence st : hm.getValue()) {
				putIntoMap(afterCorrection, hm.getKey(),fr.correctSentence(st.getWords()));
			}
		}
	}
	
	private void putIntoMap(Map<String,List<Sentence>> map, String key, Sentence toAdd) {
		map.computeIfAbsent(key, k -> new ArrayList<>()).add(toAdd);
	}
	
	public static void main(String[] args) {
		
	}
}
