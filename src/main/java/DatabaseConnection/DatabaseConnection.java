/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author User
 */
public class DatabaseConnection {
    private static Connection conn = null;
    
    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://"
                        + "DESKTOP-53DQKVJ\\DUNG0603:1433;databaseName=PerfumeShop;"
                        + "user=sa;password=123456;encrypt=true;"
                        + "trustServerCertificate= true;");
        //conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-DBIQC0O4:1433;databaseName=PerfumeShopV4;"
          //      + "user=sa;password=222;encrypt=true;trustServerCertificate=true;");

        return conn;
    }
}
