package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Language;
import core.grammar.LanguageFactory;
import core.grammar.Sentence;
import core.grammar.UnknowLanguage;
import core.read.Finder;
import core.read.Reader;
import core.write.Creater;

public class Core {

	private LanguageFactory lf;
	private Creater c;
	private Reader r;
	private Finder f;
	private Language lang;

	public Core(String pathDirectory, String language) throws UnknowLanguage {
		lf = new LanguageFactory();
		c = new Creater();
		r = new Reader();
		f = new Finder(pathDirectory);
		lang = lf.createLanguage(language);
		c.makeSave(pathDirectory);
	}

	private void putIntoMap(Map<String, List<Sentence>> map, String key, Sentence toAdd) {
		map.computeIfAbsent(key, k -> new ArrayList<>()).add(toAdd);
	}

	public void start() {
		List<String> listPath = f.getPathFiles();
		Map<String, List<Sentence>> beforeCorrection = new HashMap<>();
		Map<String, List<Sentence>> afterCorrection = new HashMap<>();
		for (String s : listPath) {
			beforeCorrection.put(s, r.readFile(s));
		}
		System.out.println("OULALALALAL");
		for (Map.Entry<String, List<Sentence>> hm : beforeCorrection.entrySet()) {
			for (Sentence st : hm.getValue()) {
				System.out.println(st);
				putIntoMap(afterCorrection, hm.getKey(), lang.correctSentence(st.getWords()));
			}
		}

		for (Map.Entry<String, List<Sentence>> hm : afterCorrection.entrySet()) {
			for (Sentence s : hm.getValue()) {
				System.out.println(s.toString());
			}
		}
	}
}
