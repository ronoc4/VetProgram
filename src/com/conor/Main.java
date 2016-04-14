package com.conor;

import java.sql.*;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/vet";     //Connection string – where's the database?
    static final String USER = "conor";   // replace with your username
    static final String PASSWORD = "*****";   // replace with your password

    public static void main(String[] args) {

        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drivers and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }

        Statement statement = null;
        Connection conn = null;

        try {

            //You should have already created a database via terminal/command prompt OR MySQL Workbench

            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
            statement = conn.createStatement();

            //Create a table in the database, if it does not exist already
            String createTableSQL = "CREATE TABLE IF NOT EXISTS dogs (Name varchar(30), Age int)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Created Dogs table");

            //Add some data
            String addDataSQL = "INSERT INTO dogs VALUES ('Poppy', 3)";
            statement.executeUpdate(addDataSQL);

            addDataSQL = "INSERT INTO dogs VALUES ('Zelda', 5)";
            statement.executeUpdate(addDataSQL);
            System.out.println("Added two rows of data");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //A finally block runs whether an exception is thrown or not. Close resources and tidy up whether this code worked or not.
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed");
                }
            } catch (SQLException se){
                //Closing the connection could throw an exception too
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();  //Close connection to database
                    System.out.println("Database connection closed");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("End of program");
    }
}

