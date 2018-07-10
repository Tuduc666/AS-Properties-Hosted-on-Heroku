package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	
 // Postgres JDBC local connection - worked!!!!!
//    private final static String url = "jdbc:postgresql://localhost/dvdrental";
//    private final static String user = "postgres";
//    private final static String password = "Tamduc1166";
//    
//    public static Connection Connect() {
//        Connection conn = null;
//        try {
////        	 this next line is needed for TomCat to work!!!!!!!
//        	Class.forName("org.postgresql.Driver");            
//            conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected to the PostgreSQL server successfully.");
//        } catch (SQLException | ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
// 
//        return conn;
//    }
    
	
 // Postgres JDBC Hosted on Heroku - worked!!!!!   
    public static Connection Connect() {
        Connection conn = null;
        try {
        	URI dbUri = new URI(System.getenv("DATABASE_URL"));
        	String user = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
            
        	// this next line is needed for TomCat to work!!!!!!!
        	Class.forName("org.postgresql.Driver");       
        	
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
    
    
 // Heroku java connection - never tested this!!!!
// 	public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
// 		String dbUrl = System.getenv("JDBC_DATABASE_URL");
// 		return DriverManager.getConnection(dbUrl);
// 	}
    
}
