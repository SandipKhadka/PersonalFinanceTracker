package org.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql-1811694f-kcc-f35b.a.aivencloud.com:26743/FinanceTracker";
            String user = "avnadmin";
            String pass = "AVNS_trZ5xJAGf3zShPRCasI";
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}