package core.write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import core.grammar.Sentence;

public class Creater {

	private static final String PATH_SAVE = "./save/";

	public Creater() {
		File tmp = new File(PATH_SAVE);
		if (!tmp.exists()) {
			tmp.mkdir();
		}
	}

	public void writeFile(String pathFile, List<Sentence> list) {
		try {
			FileWriter myWriter = new FileWriter(pathFile);
			for (Sentence line : list) {
				myWriter.write(line.getTheLine());
			}
			myWriter.close();
			//System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			//System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public void makeSave(String pathSrcDirectory) {
		SimpleDateFormat customDate = new SimpleDateFormat("'Save' yyyy_MM_dd '-' HH_mm_ss_SSSS");
		File newDir = new File(PATH_SAVE+customDate.format(new Date()));
		if (newDir.mkdir()) {
			copyFolder(new File(pathSrcDirectory).toPath(), newDir.toPath());
		} else {
			System.out.println("ERREUR CREATION SAUVEGARDE");
		}
		
	}

	private void copyFolder(Path src, Path dest) {
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
