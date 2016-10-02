import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String filePath = "";
		String option = "";
		File file;
		MyJFrame frame;
		if (args.length == 0 || args.length > 2) {
			System.out.println("Le nombre de paramètres est incorrect.");
		} else {
			filePath = args[args.length - 1];
			if (args.length < 1) {
				if (!args[0].equals("-s") || !args[0].equals("-f")) {
					System.out.println("L'option spécifiée est incorrect.");
				} else {
					option = args[0];
				}
			}
			file = new File(filePath);
			if (!file.exists()) {
				System.out.println("Le chemin spécifié est introuvable.");
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
		boolean checked = false;
		try {
			bufferedReader = new BufferedReader(new FileReader(plyFile));
			lineRead = bufferedReader.readLine();
			if (!lineRead.equals("ply")) {
				checked = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return checked;
	}
}
