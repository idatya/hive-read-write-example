package sh.samar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Main {
    
    private static final Logger logger = Logger.getLogger("sh.samar");
    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
    private static String connectionUrl;

    public static void main(String[] args) throws IOException {
        logger.info("start");
       /* if (args.length < 1) {
            logger.info("1 arg is required :\n\t- connectionurl ex: jdbc:hive2://hiveserver:10000/;ssl=false");
            System.err.println("1 arg is required :\n\t-connectionurl  ex: jdbc:hive2://hiveserver:10000/;ssl=false");
            System.exit(128);
        }*/
        // Get url Connection
        connectionUrl = "jdbc:hive2://192.168.218.63:10000/v_catalog";

        // Init Connection
        Connection con = null;

        // ==== Select data from Hive Table
        String sqlStatementDrop = "DROP TABLE IF EXISTS helloworld";
        String sqlStatementCreate = "CREATE TABLE helloworld (message String)";
        String sqlStatementInsert = "INSERT INTO helloworld VALUES (\'hello-world\')";
        String sqlStatementSelect = "SELECT * from helloworld";
        try {
            // Set JDBC Hive Driver
            Class.forName(JDBC_DRIVER_NAME);
            // Connect to Hive
            con = DriverManager.getConnection(connectionUrl, "impadmin", "impetus");
            // Init Statement
            Statement stmt = con.createStatement();
            // Execute DROP TABLE Query
            stmt.execute(sqlStatementDrop);
            logger.info("Drop Hive table : OK");
            // Execute CREATE Query
            stmt.execute(sqlStatementCreate);
            logger.info("Create Hive table : OK");
            // Execute INSERT Query
            stmt.execute(sqlStatementInsert);
            logger.info("Insert into Hive table : OK");
            // Execute SELECT Query
            ResultSet rs = stmt.executeQuery(sqlStatementSelect);
            while (rs.next()) {
                logger.info(rs.getString(1));
            }
            logger.info("Select from Hive table : OK");

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                // swallow
            }
        }

    }
}
