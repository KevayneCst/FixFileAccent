package core.read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Finder {

	private List<String> pathFiles;

	public Finder(String projectDirectory) {
		this.pathFiles = new ArrayList<>();
		File mainDir = new File(projectDirectory);
		File[] files = mainDir.listFiles();
		findFiles(files);
	}

	public void findFiles(File[] files) {
		for (File f : files) {
			if (f.isFile()) {
				if (f.getName().endsWith(".java")) {
					pathFiles.add(f.getAbsolutePath());
				}
			} else if (f.isDirectory()) {
				findFiles(f.listFiles());
			}
		}
	}

	public List<String> getPathFiles() {
		return pathFiles;
	}
	
	public static void main(String[] args) {
		Finder f = new Finder("C:\\Users\\Kévin\\Desktop\\Classeur\\IUT\\S3T\\M315 - CPOO\\Choisir ce dossier en tant que workspace");
		System.out.println(f.getPathFiles());
	}
}
