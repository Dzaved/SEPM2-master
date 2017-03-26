package sample.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.dao.impl.DAOException;

import java.sql.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil is a class that is handling the connection to the database.
 */
public class DBUtil {
    private static Connection connection = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);

    public DBUtil() {
    }

    private static Connection openConnection() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        }

        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:h2:tcp://127.0.0.1/~/test8","sa","");
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return con;
    }

    public static Connection getConnection() {
        if(connection == null) {
            connection = openConnection();
        }

        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException var1) {
                var1.printStackTrace();
            }
        }

    }

    public static Connection reconnectIfConnectionToDatabaseLost() throws DAOException {
        try {
            if(connection!=null && !connection.isValid(3)){
                connection = null; //set con to null so the getConnection() will try to connect to the database when called again.
                LOGGER.warn("Connection to the database lost!");
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DAOException(e.getMessage());
        }
        connection = DBUtil.getConnection();
        return connection;
    }


    public static void reset(String path) throws DAOException{
        try {
            if(System.getProperty("os.name").startsWith("Windows")){
                path = path.substring(1);
            }
            CallableStatement initCall = connection.prepareCall("RUNSCRIPT FROM '" + path + "'");
            initCall.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

    }


}
