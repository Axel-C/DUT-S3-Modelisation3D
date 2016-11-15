package database;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Main {
	static ArrayList<Fichier>fichiers ;
	static JFrame frame = new JFrame();

	public static void main(String[] args) {
		fichiers = Data.list();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		find("rogedddd");

	}
	
	
	public static void all(){
		String data[] = new String[fichiers.size()];
		for(int i = 0 ; i <  data.length ; i++ ){
			data[i] = fichiers.get(i).toString();
		}
		JList<String> list = new JList<>(data);
		frame.add(list);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	
	public static void name(String nom){
		Fichier trouve = null ;
		for(Fichier f:fichiers){
			if(f.nom.equals(nom)){
				trouve = f ;
			}
		}
		if(trouve != null){
			frame.add(trouve.panel());
			frame.setSize(500, 100);
			frame.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "Impossible de trouver le modele demande");
		}
		
		
	}
	
	public static void find(String motscle){
		String[] tags = motscle.split(" ");
		ArrayList<Fichier> list = new ArrayList<>();
		for(Fichier f :fichiers){
			for(int i = 0 ; i < f.tags.length ; i++){
				for(int j =0 ; j < tags.length ; j++){
					if(f.tags[i].equals(tags[j])){
						list.add(f);
					}
				}
			}
		}
		for(Fichier f:list){
			frame.add(f.panel());
		}
		if(list.isEmpty()){
			JOptionPane.showMessageDialog(null, "Aucun résultat trouvé");
		}else{
		
		frame.setSize(800, 800);
		frame.setVisible(true);
		}
		
	}

}
