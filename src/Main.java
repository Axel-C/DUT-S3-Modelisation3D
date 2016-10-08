package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String filePath = "";
		String option = "";
		File file;
		MyJFrame frame;
		if (args.length == 0 || args.length > 2) {
			System.out.println("Le nombre de param�tres est incorrect.");
		} else {
			filePath = args[args.length - 1];
			if (args.length > 1) {
				if (!args[0].equals("-s") && !args[0].equals("-f")) {
					System.out.println("L'option sp�cifi�e est incorrect.");
				} else {
					option = args[0];
				}
			}
			file = new File(filePath);
			if (!file.exists()) {
				System.out.println("Le chemin sp�cifi� est introuvable.");
			} else if (!isAPlyFile(file)) {
				System.out.println("Le fichier n'est pas au format PLY.");
			} else {
				if(!checkPlyFile(file)) {
					System.out.println("Le fichier PLY est incorrect.");
				} else {
					if(option.equals("-s")) {
						frame = new MyJFrame(new Space(new Model(file), Space.ONLY_SEGMENTS));
					} else if(option.equals("-f")) {
						frame = new MyJFrame(new Space(new Model(file), Space.ONLY_FACES));
					} else {
						frame = new MyJFrame(new Space(new Model(file), Space.SEGMENTS_AND_FACES));
					} frame.refresh();
				}
			}
		}

	}

	private static boolean isAPlyFile(File file) {
		String ext = "";
		if (file.getName().lastIndexOf(".") > 0) {
			ext = file.getName().substring(file.getName().lastIndexOf("."));
		}
		return ext.equals(".ply");
	}

	private static boolean checkPlyFile(File plyFile) {
		BufferedReader bufferedReader;
		String lineRead = "";
		String[] splitedLineRead;
		boolean checked = true;
		int nVertices, nFaces;
		try {
			bufferedReader = new BufferedReader(new FileReader(plyFile));
			lineRead = bufferedReader.readLine();
			if (!lineRead.equals("ply")) {
				checked = false;
			}
			bufferedReader.readLine();
			bufferedReader.readLine();
			lineRead = bufferedReader.readLine();
			splitedLineRead = lineRead.split(" ");
			if(splitedLineRead.length != 3) {
				checked = false;
			} else {
				if(!splitedLineRead[0].equals("element") || !splitedLineRead[1].equals("vertex") || !isAnInteger(splitedLineRead[2])) {
					checked = false;
				} else {
					nVertices = Integer.parseInt(splitedLineRead[2]);
					if(nVertices < 0){
						checked = false;
					}
				}
			}
			bufferedReader.readLine();
			bufferedReader.readLine();
			bufferedReader.readLine();
			lineRead = bufferedReader.readLine();
			splitedLineRead = lineRead.split(" ");
			if(splitedLineRead.length != 3) {
				checked = false;
			} else {
				if(!splitedLineRead[0].equals("element") || !splitedLineRead[1].equals("face") || !isAnInteger(splitedLineRead[2])) {
					checked = false;
				} else {
					nFaces = Integer.parseInt(splitedLineRead[2]);
					if(nFaces < 0){
						checked = false;
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} return checked;
	}
	
	private static boolean isAnInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
}
