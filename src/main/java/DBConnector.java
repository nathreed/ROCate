//Sifan Ye (sye8)
//Credit to Pankaj on JournalDev

import java.sql.*;

public class DBConnector {

	private Connection connection;
	
	public DBConnector() throws ClassNotFoundException, SQLException {
	    String dbURL = "jdbc:mysql://database:3306";
	    String user = "rocate";
	    String pass = "rocate123";
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(dbURL, user, pass);
	}
	
	public Connection getConnection(){
		return this.connection;
	}

	public String testTableContents() {
	    try {
            Statement statement = this.connection.createStatement();
            ResultSet results = statement.executeQuery("select * from rocate.testTable");
            StringBuilder sb = new StringBuilder();
            while(results.next()) {
                String name = results.getString("name");
                String email = results.getString("email");
                sb.append(String.format("Name: %s, Email: %s%n", name, email));
            }
            return sb.toString();
        } catch (SQLException e) {
	        return "SQL Error: " + e.getMessage();
        }
    }
}
