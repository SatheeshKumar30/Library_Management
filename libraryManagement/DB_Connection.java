package libraryManagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {


public  Connection connect()
	{
	try {
        Class.forName("com.mysql.cj.jdbc.Driver");
       
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/","root","");
        System.out.println("Connected to MySQL");
        return con;
		} 
	 catch (Exception ex) {
	        ex.printStackTrace();
	 }
	return null;
	}
}