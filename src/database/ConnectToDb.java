package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDb {
    public final String DRIVER="com.mysql.jdbc.Driver";
    public final String URL="jdbc:mysql://localhost:3306/school";
    public final String USER="root";
    public final String PASSWORD="1234";
    public Connection con;

    public ConnectToDb(){
        try{
            Class.forName(DRIVER);
            this.con= DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(Exception ex){
            System.out.print(ex);
        }
    }
}
