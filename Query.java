package net.sqlitetutorial;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class Query {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/Movies.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(){
        String sql = "SELECT name, actor, actress, director, year FROM Movies";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("name") +  "\t" + 
                                   rs.getString("actor") + "\t" +
				                   rs.getString("actress") + "\t" +
			                       rs.getString("director") + "\t" +
                                   rs.getDouble("year"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   
    public void getMovies(String actor){
               String sql = "SELECT name "
                          + "FROM Movies WHERE actor = ?";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,actor);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(
                                   rs.getString("name") + "\t" );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   
    public static void main(String[] args) {
        System.out.println("Enter the actor's name");
        Scanner sc= new Scanner(System.in);
        String a= sc.next();
        Query app = new Query();
        app.selectAll();
	    app.getMovies(a);
    }

}