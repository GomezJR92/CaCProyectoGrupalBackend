package edu.codoacodo.infrastucture.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

        private static final String URL = "jdbc:mysql://localhost:3306/login_db";
        private static final String USER = "root";
        private static final String PASSWORD = "root";

        public static Connection getConnection(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("CONEXION ESTABLECIDA...");
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
}
