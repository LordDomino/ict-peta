import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Main {

    public static final String sqlDb = "ict_peta";
    public static final String sqlTbl = "users_tbl";

    public static WF_UserRegistration root;
    public static Connection connection;

    public static void main(String[] args) {
        root = new WF_UserRegistration();

        try {
            root.tablePanel.loadFromDatabase();
            root.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                root,
                "SQL database offline. Please retry.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    /**
     * Registers the class from the JAR library and establishes a port
     * connection to access MySQL.
     */
    public static void establishSQLConnection() {
        try {
            // Here we register the referenced external library, which
            // is the .jar file, to allow us to use its internal
            // classes for accessing the SQL database.
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // The connection with the SQL database server is then
            // established using the class provided by the jar library.
            Main.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + Main.sqlDb, 
                "root",
                ""
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}