package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginHandler {
    public Connection con;
    Statement st=null;
    ResultSet rs=null;
    String sql;

    public LoginHandler(Connection con){
        this.con=con;
    }

    public boolean LoginCheck(String username, String passsword){
        sql="select * from user where username='"+username+"' and userpassword='"+passsword+"'";
        try{
            st=con.createStatement() ;
            rs=st.executeQuery(sql);
            if(rs.next()){
              return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                st.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
