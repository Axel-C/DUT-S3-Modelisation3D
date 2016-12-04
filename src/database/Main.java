package database;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Main {
	static ArrayList<Fichier> fichiers;
	static JFrame frame = new JFrame();

	public static void main(String[] args) {
		fichiers = Data.list();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (args.length == 0) {
			System.out.println("Aucune option passée");
		} else {
			if (args[0].equals("--all")) {
				all();
			} else if (args[0].equals("--add")) {
				add();
			} else if (args.length > 1) {
				if (args[0].equals("--name")) {
					name(args[1]);
				} else if (args[0].equals("--find")) {
					String liste = "";
					for (int i = 0; i < args.length; i++) {
						liste += args[i] + " ";
					}
					find(liste);
				} else if (args[0].equals("--delete")) {
					delete(args[1]);
				} else if (args[0].equals("--edit")) {
					edit(args[1]);
				}

			}
		}

	}

	/**
	 * fenetre avec une liste (JList) des informations sur tous les modeles de
	 * la bibliotheque
	 */
	public static void all() {
		String data[] = new String[fichiers.size()];
		for (int i = 0; i < data.length; i++) {
			data[i] = fichiers.get(i).toString();
		}
		JList<String> list = new JList<>(data);
		frame.add(list);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

	/**
	 * fenetre avec les informations du modele specifie en parametre. Un message
	 * d’erreur approprie sera affiche si le modele nexiste pas.
	 * 
	 * @param nom
	 *            Nom du fichier
	 */
	public static void name(String nom) {
		Fichier trouve = null;
		for (Fichier f : fichiers) {
			if (f.nom.equals(nom)) {
				trouve = f;
			}
		}
		if (trouve != null) {
			frame.add(new JLabel(trouve.toString()));
			frame.setSize(500, 100);
			frame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Impossible de trouver le modele demande");
		}

	}

	/**
	 * fenetre avec une liste contenant uniquement les mod`eles qui sont decrits
	 * par au moins un des mots cles
	 * 
	 * @param motscle
	 */
	public static void find(String motscle) {
		String[] tags = motscle.split(" ");
		ArrayList<Fichier> list = new ArrayList<>();
		for (Fichier f : fichiers) {
			for (int i = 0; i < f.tags.length; i++) {
				for (int j = 0; j < tags.length; j++) {
					if (f.tags[i].equals(tags[j])) {
						list.add(f);
					}
				}
			}
		}
		for (Fichier f : list) {
			
		}
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Aucun resultat trouve");
		} else {
			JList<Object> liste = new JList<>(list.toArray()) ;
			frame.add(liste);

			frame.setSize(400, 150);
			frame.setVisible(true);
		}

	}

	/**
	 * supprime de la base de donnees le mod`ele dont le nom est donne en
	 * parametre. Le seule option qui n’ouvre pas
	 * 
	 * @param name
	 */
	public static void delete(String name) {
		boolean retour = Data.delete(name);
		if (retour) {
			System.out.println("Suppression reussie");
		} else {
			System.out.println("Fichier introuvable dans la base de donnee");
		}
	}

	/**
	 * fenetre avec formulaire de saisie des informations concernant un modele.
	 * Un bouton permet d’ajouter le modele
	 */
	public static void add() {
		new Menu();
	}

	/**
	 * fenetre avec formulaire de saisie pour modifier les informations
	 * concernant le modele dont le nom est donne en parametre. Si certaines
	 * informations ne peuvent pas etre changees (l’identifiant), le formulaire
	 * ne doit pas le permettre. Un bouton permet d’enregistrer les
	 * modifications dans la base de donnees.
	 * 
	 * @param nom
	 */
	public static void edit(String nom) {
		Fichier f = Data.find(nom);
		if (f == null) {
			JOptionPane.showMessageDialog(null, "Fichier introuvable");

		} else {
			new Menu(f.nom, f.path, f.getTagsToString(), Menu.MODIFIER);
		}

	}
}
