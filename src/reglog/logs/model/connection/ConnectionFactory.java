package reglog.logs.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	public Connection getConnection(){
        
	    try {
	    	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			return DriverManager.getConnection("jdbc:derby:reglog;create=false");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
	    return null;
	    
    }

}
