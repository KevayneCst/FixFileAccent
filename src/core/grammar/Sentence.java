package core.grammar;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	
	private String theLine;
	private List<Word> words;
	
	public Sentence(String s) {
		this.theLine=s;
		this.words=sentenceIntoWords();
	}
	
	private List<Word> sentenceIntoWords() {
		List<Word> list = new ArrayList<>();
		String[] wordsSplit = theLine.split("[[ ]*|[,]*|[;]*|[:]*|[']*|[’]*|[\\.]*|[:]*|[#]*|[$]*|[-]*|[\"]*|[/]*|[!]*|[?]*|[+]*]+");
		for (int i = 0; i < wordsSplit.length; i++) {
		    list.add(new Word(wordsSplit[i]));
		}
		return list;
	}

	public String getTheLine() {
		return theLine;
	}

	public void setTheLine(String theLine) {
		this.theLine = theLine;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "Sentence [theLine=" + theLine + ", words=" + words + "]";
	}
}
