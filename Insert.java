package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Insert {

    
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

    
    public void insert(String name,String actor,String actress,String director, double year) {
        String sql = "INSERT INTO Movies(name,actor,actress,director,year) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
	    pstmt.setString(2, actor);
	    pstmt.setString(3, actress);
	    pstmt.setString(4, director);
        pstmt.setDouble(5, year);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        Insert app = new Insert();
        // insert three new rows
        app.insert("Going My Way","Albert","Foster","Leo McCarey",1984);
        app.insert("The Scarlet and the Black", "Alex Dawn","Lilian","Michael Tuchnen",1965);
        app.insert("The Hunchback of Notre Dame", "Richard Dame","Riley","Alan Hume",1990);
    }

}