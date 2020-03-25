package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.grammar.Language;
import core.grammar.LanguageFactory;
import core.grammar.Sentence;
import core.grammar.UnknowLanguageException;
import core.read.Finder;
import core.read.Reader;
import core.write.Creater;

/**
 * Classe contenant l'algorithme et la logique principale nécessaire au bon
 * fonctionnement de l'application.
 * 
 * @author Kévin Constantin
 *
 */
public class Core {

	private LanguageFactory lf;
	private Creater c;
	private Reader r;
	private Finder f;
	private Language lang;

	public Core(String pathDirectory, String language) throws UnknowLanguageException {
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
		Map<String, List<Sentence>> firstReading = new HashMap<>();
		Map<String, List<Sentence>> afterCorrection = new HashMap<>();
		List<String> filesWhoNeededCorrection = new ArrayList<>();

		// Step 1: Read and store all lines for all files
		for (String s : listPath) {
			firstReading.put(s, r.readFile(s));
		}

		// Step 2: Correct all lines who needs to be corrected for all files
		for (Map.Entry<String, List<Sentence>> hm : firstReading.entrySet()) {
			for (Sentence st : hm.getValue()) {
				if (st.needCorrection()) {
					System.out.println(st.getTheLine());
					filesWhoNeededCorrection.add(hm.getKey());
					putIntoMap(afterCorrection, hm.getKey(), lang.correctSentence(st.getWords()));
				}
			}
		}

		for (Map.Entry<String, List<Sentence>> hm : afterCorrection.entrySet()) {
			if (filesWhoNeededCorrection.contains(hm.getKey())) {
				for (Sentence st : hm.getValue()) {
					System.out.println(st.getTheLine());
				}
			}

		}

		// Step 3: ReWrite all files who needed correction
		for (String pathFile : filesWhoNeededCorrection) {

		}

	}
}
