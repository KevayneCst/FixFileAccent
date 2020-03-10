package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Sentence;
import core.read.Finder;
import core.read.Reader;

public class Core {

	private Finder f;
	private static Reader r = new Reader();

	public Core(String pathDirectory) {
		this.f = new Finder(pathDirectory);
	}
	
	public void start() {
		List<String> listPath = f.getPathFiles();
		Map<String,List<Sentence>> map = new HashMap<>();
		for (String s : listPath) {
			map.put(s, r.readFile(s));
		}
	}
}
