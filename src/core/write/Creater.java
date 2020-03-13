package core.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Creater {

	private static final String PATH_RESULT = "./result/";

	public Creater() {

	}

	public boolean createNewFile(String nameFile) {
		try {
			File f = new File(PATH_RESULT + nameFile);
			if (f.createNewFile()) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void writeFile() {
		try {
			FileWriter myWriter = new FileWriter("filename.txt");
			myWriter.write("Files in Java might be tricky, but it is fun enough!");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (int i=0; i<args.length; i++) {
			System.out.print("["+i+"]: "+args[i]+"\t");
		}/*
		String source = "C:\\Users\\Kévin\\Desktop\\oui\\source";
		File srcDir = new File(source);

		String destination = "./save";
		File destDir = new File(destination);

		copyFolder(srcDir.toPath(), destDir.toPath());*/

	}

	public static void copyFolder(Path src, Path dest) {
		try {
			Files.walk(src).forEach(s -> {
				try {
					Path d = dest.resolve(src.relativize(s));
					if (Files.isDirectory(s)) {
						if (!Files.exists(d))
							Files.createDirectory(d);
						return;
					}
					Files.copy(s, d);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
