package group1.database_connectors;

import group1.ExceptionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by rnice01 on 11/21/2015.
 * This class is used for making DB connections, update, and select queries
 * Made for cleaning up the classes used for classes used to create the team and player objects
 * as they were getting messy
 */
public class DatabaseConnector {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/golf_final";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "CSC4500";

    Connection conn = null;
    Statement stmt = null;

DatabaseConnector(){}


    void MakeConnection() throws SQLException, ClassNotFoundException{
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
        }
        catch(SQLException ex){
            ExceptionHandler.sqlException();
        }
}
    Statement returnStatement(){
        return stmt;
    }

    Connection returnConnection(){
        return conn;
    }

    void CloseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
