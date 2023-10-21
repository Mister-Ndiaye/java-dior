/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diordiouf;

import diordiouf.Utilisateur;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement ;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dior
 */
public class ConnectionDb {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dior_diouf";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    
    /*public static void main(String[] args)  {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =  DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement stmt = conn.createStatement(); 
            ResultSet resultSet = stmt.executeQuery("select * from user");
            while(resultSet.next())
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "
                        + " "+resultSet.getString(3));
            conn.close();
        } catch (Exception e) {
            System.out.println("in exception");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

        }
        
    }*/
    public static Utilisateur addUser(Utilisateur user){
      Connection  conn = connection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into user(username,password) values(?,?)";
            PreparedStatement  statement =conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            
            statement.executeUpdate();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       return null;
    }
    
    public static Connection connection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
             conn =  DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
         
           
            //conn.close();
        } catch (Exception e) {
            System.out.println("in exception");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

        }
        return conn;
    }
    
    public static boolean verifieUtilisateur(Utilisateur utilisateur){
        Connection  connection = connection();
        try {
            String sql = "select * from user where username = ? and password = ?";
            // Préparer la requête SQL
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, utilisateur.getUsername());
            pstmt.setString(2, utilisateur.getPassword());
            
            // Exécuter la requête
            ResultSet resulSet = pstmt.executeQuery();
            
            // Vérifier si l'utilisateur existe dans la base de données
            if(resulSet.next()){
                System.out.println("connection reussie");
                return true;
            }else{
                System.out.println("verifier vos données saisies");
                
            }
                
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       return false;
    }
}

    

