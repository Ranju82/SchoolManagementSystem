package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
    public Connection con;
    String sql;

    public Student(Connection con){ this.con=con; }

    public void addStudent(int classes,int id,String name,String gender,String father,String mother,String dob,String address,String contact){
        Statement st=null;
        if(classes==1){
            sql="insert into classonestudent values('"+id+"','"+name+"','"+gender+"','"+father+"','"+mother+"','"+dob+"','"+address+"','"+contact+"')";
        }
        else if(classes==2){
            sql="insert into classtwostudent values('"+id+"','"+name+"','"+gender+"','"+father+"','"+mother+"','"+dob+"','"+address+"','"+contact+"')";
        }
        else if(classes==3){
            sql="insert into classthreestudent values('"+id+"','"+name+"','"+gender+"','"+father+"','"+mother+"','"+dob+"','"+address+"','"+contact+"')";
        }
        else if(classes==4){
            sql="insert into classfourstudent values('"+id+"','"+name+"','"+gender+"','"+father+"','"+mother+"','"+dob+"','"+address+"','"+contact+"')";
        }
        else if(classes==5){
            sql="insert into classfivestudent values('"+id+"','"+name+"','"+gender+"','"+father+"','"+mother+"','"+dob+"','"+address+"','"+contact+"')";
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

    public void DeleteStudent(int Class,int id) {
        Statement st=null;
        if(Class==1){
            sql="delete  from classonestudent where StudentId ='"+id+"'";
        }
        else if(Class==2){
            sql="delete  from classtwostudent where StudentId='"+id+"'";
        }
        else if(Class==3){
            sql="delete  from classthreestudent where studentid='"+id+"'";
        }
        else if(Class==4){
            sql="delete  from classfourstudent where studentid='"+id+"'";
        }
        else if(Class==5){
            sql="delete  from classfivestudent where studentid='"+id+"'";
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

    public void updateStudent(int Class,int id,String name,String gender,String father,String mother,String dob,String address,String contact){
        Statement st=null;
        if(Class==1){
            sql="UPDATE classonestudent SET StudentName='"+name+"',StudentGender='"+gender+"',FatherName='"+father+"',MotherName='"+mother+"',DateOfBirth='"+dob+"',StudentAddress='"+address+"',ContactNumber='"+contact+"' where StudentId='"+id+"'";
        }
        else if(Class==2){
            sql="UPDATE classtwostudent SET StudentName='"+name+"',StudentGender='"+gender+"',FatherName='"+father+"',MotherName='"+mother+"',DateOfBirth='"+dob+"',StudentAddress='"+address+"',ContactNumber='"+contact+"' where StudentId='"+id+"'";
        }
        else if(Class==3){
            sql="UPDATE classthreestudent SET StudentName='"+name+"',StudentGender='"+gender+"',FatherName='"+father+"',MotherName='"+mother+"',DateOfBirth='"+dob+"',StudentAddress='"+address+"',ContactNumber='"+contact+"' where StudentId='"+id+"'";
        }
        else if(Class==4){
            sql="UPDATE classfourstudent SET StudentName='"+name+"',StudentGender='"+gender+"',FatherName='"+father+"',MotherName='"+mother+"',DateOfBirth='"+dob+"',StudentAddress='"+address+"',ContactNumber='"+contact+"' where StudentId='"+id+"'";
        }
        else if(Class==5){
            sql="update classfivestudent set StudentName='"+name+"',StudentGender='"+gender+"',FatherName='"+father+"',MotherName='"+mother+"',DateOfBirth='"+dob+"',StudentAddress='"+address+"',ContactNumber='"+contact+"' where StudentId='"+id+"'";
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
