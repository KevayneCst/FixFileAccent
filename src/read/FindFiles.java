package read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFiles {

	private List<String> pathFiles;

	public FindFiles(String projectDirectory) {
		this.pathFiles = new ArrayList<>();
		File mainDir = new File(projectDirectory);
		File[] files = mainDir.listFiles();
		findFiles(files, 0);
	}

	public void findFiles(File[] files, int level) {
		for (File f : files) {
			if (f.isFile()) {
				if (f.getName().endsWith(".java")) {
					pathFiles.add(f.getAbsolutePath());
				}
			} else if (f.isDirectory()) {
				findFiles(f.listFiles(), level + 1);
			}
		}
	}

	public List<String> getPathFiles() {
		return pathFiles;
	}
}
