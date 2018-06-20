package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Staff {

    private Connection con;

    public Staff(Connection con){
        this.con=con;
    }

    public void AddStaffToDatabase(String staffType,int id,String name,String father,String mother,String qualification,String address,String doj){
        Statement st=null;
        String sql=null;
        if(staffType.equals("Teacher")){
            sql="insert into teacher values('"+id+"','"+name+"','"+father+"','"+mother+"','"+qualification+"','"+address+"','"+doj+"')";
        }
        else if(staffType.equals("Cook")){
            sql="insert into cook values('"+id+"','"+name+"','"+father+"','"+mother+"','"+qualification+"','"+address+"','"+doj+"')";
        }
        else if(staffType.equals("Chowkidar")){
            sql="insert into chowkidar values('"+id+"','"+name+"','"+father+"','"+mother+"','"+qualification+"','"+address+"','"+doj+"')";
        }
        try{
            st=con.createStatement() ;
            st.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void UpdateStaffToDatabase(String staffType,int id,String name,String father,String mother,String qualification,String address,String doj){
        Statement st=null;
        String sql=null;
        if(staffType.equals("Teacher")){
            sql="update teacher set TeacherName='"+name+"',FatherName='"+father+"',MotherName='"+mother+"',Qualification='"+qualification+"',Address='"+address+"',DateOfJoining='"+doj+"' where TeacherId='"+id+"'";
        }
        else if(staffType.equals("Cook")){
            sql="update cook set CookName='"+name+"',FatherName='"+father+"',MotherName='"+mother+"',Qualification='"+qualification+"',Address='"+address+"',DateOfJoining='"+doj+"' where CookId='"+id+"'";
        }
        else if(staffType.equals("Chowkidar")){
            sql="update chowkidar set ChowkidarName='"+name+"',FatherName='"+father+"',MotherName='"+mother+"',Qualification='"+qualification+"',Address='"+address+"',DateOfJoining='"+doj+"' where ChowkidarId='"+id+"'";
        }
        try{
            st=con.createStatement() ;
            st.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void DeleteStaffFromDatabase(String staffType,int id){
        Statement st=null;
        String sql=null;
        if(staffType.equals("Teacher")){
            sql="delete  from teacher where TeacherId='"+id+"'";
        }
        else if(staffType.equals("Cook")){
            sql="delete  from cook where CookId='"+id+"'";
        }
        else if(staffType.equals("Chowkidar")){
            sql="delete  from chowkidar where ChowkidarId='"+id+"'";
        }
        try{
            st=con.createStatement() ;
            st.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}




