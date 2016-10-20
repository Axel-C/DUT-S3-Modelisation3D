package projetmode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GoodFormatPLY {

	static boolean vertexNbFound = false;
	static int vertexNb;
	static boolean faceNbFound = false;
	static int faceNb;

	static boolean check(File plyFile) {
		BufferedReader bufferedReader;
		String lineRead = "a";

		try {
			bufferedReader = new BufferedReader(new FileReader(plyFile));
			// On vérifie que la ligne element vertex est présente
			while (!(lineRead.contains("element vertex"))) {
				lineRead = bufferedReader.readLine();
			}
			vertexNbFound = true;
			vertexNb = Integer.valueOf(lineRead.split(" ")[2]);

			// On vérifie que la ligne element face est présente
			while (!lineRead.contains("element face")) {
				lineRead = bufferedReader.readLine();
			}
			faceNbFound = true;
			faceNb = Integer.valueOf(lineRead.split(" ")[2]);

			bufferedReader.close();
			return true;
		} catch (Exception e) {
			if (!vertexNbFound)
				System.out.println("Aucune ligne element vertex n'est présente");
			else if (!faceNbFound)
				System.out.println("Aucune ligne element face n'est présente");
			else
				System.out.println(e);
			return false;
		}
	}
}
