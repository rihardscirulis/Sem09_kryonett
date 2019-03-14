package Networking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ActionsWithDB {
static Connection connect=null;
	
	public void createConnection() throws ClassNotFoundException, SQLException {          
    	Class.forName("org.sqlite.JDBC"); // load sqlite driver
    	connect = DriverManager.getConnection("jdbc:sqlite:numberDB.db");     // create a connection
           						//numberDB.db will be the name of our database (an empty database will be created if it does not exist).
    	System.out.println("Created connection / Opened database successfully");
    }

	public void endConnection() throws SQLException {
    	connect.close(); 
    }

	public void createTableForVariations() throws SQLException {
		Statement statement=connect.createStatement();
		String sql="CREATE TABLE IF NOT EXISTS VariationDB("+
		"id INTEGER nut null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
		"variationNumber INTEGER,"+
		"email VARCHAR(255),"+
		"variationFirstNumber INTEGER,"+
		"variationSecondNumber INTEGER,"+
		"variationThirdNumber INTEGER,"+
		"variationFourthNumber INTEGER,"+
		"variationFifthNumber INTEGER);";
		statement.executeUpdate(sql);
		statement.close();
		System.out.println("Table created successfully");
	}
	
	public void insertNewRecord(Variation var) throws SQLException{
		Statement statement=connect.createStatement();
		statement.executeUpdate("INSERT INTO VariationDB(id, variationNumber, email, variationFirstNumber, variationSecondNumber, variationThirdNumber, variationFourthNumber, variationFifthNumber)"+
		"VALUES("+var.getVariationNr()+", '"+var.getEmail()+"', "+var.getSelectedNumbers().get(0)+", "+var.getSelectedNumbers().get(1)+", "+var.getSelectedNumbers().get(2)+", "+var.getSelectedNumbers().get(3)+", "+var.getSelectedNumbers().get(4)+")");
		statement.close();
	}
}
