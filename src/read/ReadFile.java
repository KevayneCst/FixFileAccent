package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public ReadFile() {
	}
	
	public void readFile(String pathFile) throws IOException {
		File fichier = new File(pathFile);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fichier));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
