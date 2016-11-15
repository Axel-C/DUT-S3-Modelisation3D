package database;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Fichier>fichiers = Data.list();
		for(Fichier f:fichiers){
			System.out.println(f);
		}

	}

}
