package core;

import java.util.ArrayList;
import java.util.List;

import core.grammar.Sentence;

/**
 * Classe représentant un fichier et son contenu.
 *
 * @author Kévin Constantin
 *
 */
public class FileContent {

	private final String path;
	private final List<Sentence> content;

	public FileContent(String filePath) {
		path = filePath;
		content = new ArrayList<>();
	}

	public FileContent(String filePath, List<Sentence> fileContent) {
		path = filePath;
		content = fileContent;
	}

	public void addSentence(Sentence toAdd) {
		content.add(toAdd);
	}

	public String getPath() {
		return path;
	}

	public List<Sentence> getContent() {
		return content;
	}

}
