package id.ac.unpas.pp2.modul10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    private static Connection mysqlconfig;

    public static Connection configDB() throws SQLException {
        try {
            if (mysqlconfig == null || mysqlconfig.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/kampus_db";
                String user = "root";
                String pass = "";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                mysqlconfig = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException e) {
            System.err.println("Koneksi Gagal: " + e.getMessage());
            throw e;
        }
        return mysqlconfig;
    }
}