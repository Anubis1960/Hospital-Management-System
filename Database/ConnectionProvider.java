package Database;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class is used to connect to the database.
 * It uses the JDBC driver to connect to the database.
 * It returns the connection object.
 */
public class ConnectionProvider {
    public static Connection getCon() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
        }
        catch(Exception e){
            return null;
        }
    }
}
