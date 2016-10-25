 package database;
	
 import java.sql.*;
 import java.util.ArrayList;
 
 public class Data
 {
 	
   public static ArrayList<Fichier> getData()
   {
     Connection c = null;
     Statement stmt = null;
     ArrayList<Fichier> fichiers = new ArrayList<>();
     try {
     	
     	// OUVERTURE BASE DE DONNEE
     	
       Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:test.db");
       System.out.println("Ouverture de la base reussie");
       
       // REQUETE
       
       stmt = c.createStatement();
       String querry = "SELECT * FROM Files ;";
       ResultSet rs = stmt.executeQuery(querry);
       
       // STOCKAGE
       
       while(rs.next()){
     	  fichiers.add(new Fichier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
       }
       
       // FERMETURE
       
       stmt.close();
       c.close();
     } catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
     }
     
     // RETOUR 
     
     System.out.println("Donne recuperee");
     return fichiers ;
     
   }
   
   
 }