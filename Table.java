package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Table {

    
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/sqlite/db/Movies.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Movies (\n" + "	name text PRIMARY KEY,\n"
                                                            + "	actor text NOT NULL,\n"
                                                            + "	actress text NOT NULL,\n"
                                                            + "	director text NOT NULL,\n"
                                                            + "	year integer NOT NULL\n"
                                                            + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
	System.out.println("Successful");
            // create a new table
            stmt.execute(sql);
	System.out.println("Successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewTable();
    }

}
