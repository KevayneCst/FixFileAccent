package core.grammar;

public class WordDictionnary extends Word {

	public WordDictionnary(String s) {
		super(s);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WordDictionnary [");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
