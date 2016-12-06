package projetmode;

import java.io.File;

import Model.Model;
import View.Fenetre;
import View.Space;

public class Main {
	public static void main(String[] args) {
		String filePath = "";
		String option = "";
		File file;
		if (args.length == 0 || args.length > 2) {
			System.out.println("Le nombre de parametres est incorrect.");
		} else {
			filePath = args[args.length - 1];
			if (args.length > 1) {
				if (!args[0].equals("-s") && !args[0].equals("-f")) {
					System.out.println("L'option specifiee est incorrect.");
				} else {
					option = args[0];
				}
			}
			file = new File(filePath);
			if (!file.exists()) {
				System.out.println("Le chemin du fichier est introuvable.");
			} else if (!isAPlyFile(file)) {
				System.out.println("Le fichier n'est pas au format PLY.");
			} else {
				try {
					if (option.equals("-s")) {
						new Fenetre(new Space(new Model(file), Space.ONLY_SEGMENTS));
					} else if (option.equals("-f")) {
						new Fenetre(new Space(new Model(file), Space.ONLY_FACES));
					} else {
						new Fenetre(new Space(new Model(file), Space.SEGMENTS_AND_FACES));
					}
				} catch (Exception e) {
					System.out.println("Le fichier PLY est incorrect.");
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
}
