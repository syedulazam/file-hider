package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    public static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/File_hider?useSSL=false", "root", "gems2009");
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        System.out.println("Connected to the database successfully");
        return connection;
    }

    public static void closeConnection(){
        if(connection!=null){
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        MyConnection.getConnection();
    }
}
