package com.wipro.retail.util;

public class DBUtil {

    public static java.sql.Connection getDBConnection() {

        java.sql.Connection con = null;

        try {

           
            Class.forName("oracle.jdbc.driver.OracleDriver");

           
            con = java.sql.DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "1905"   
            );

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {
            System.out.println("Problem while connecting to database");
            e.printStackTrace();
        }

        return con;
    }
}
