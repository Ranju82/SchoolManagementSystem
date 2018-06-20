package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExamMarks {
    private Connection con;

    public ExamMarks(Connection con){
        this.con=con;
    }

    public void addMarks(int Class,int id,String name,int eng,int math,int env,int art){
        Statement st=null;
        String sql=null;
        if(Class==1){
            sql="insert into classoneresult values('"+id+"','"+name+"','"+eng+"','"+math+"','"+env+"','"+art+"')";
        }
        else if(Class==2){
            sql="insert into classtworesult values('"+id+"','"+name+"','"+eng+"','"+math+"','"+env+"','"+art+"')";
        }
        else if(Class==3){
            sql="insert into classthreeresult values('"+id+"','"+name+"','"+eng+"','"+math+"','"+env+"','"+art+"')";
        }
        else if(Class==4){
            sql="insert into classfourresult values('"+id+"','"+name+"','"+eng+"','"+math+"','"+env+"','"+art+"')";
        }
        else if(Class==5){
            sql="insert into classfiveresult values('"+id+"','"+name+"','"+eng+"','"+math+"','"+env+"','"+art+"')";
        }
        try{
            st=con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
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

    public void deleteMarks(int Class,int id){
        Statement st=null;
        String sql=null;
        if(Class==1){
            sql="delete from classoneresult where StudentId='"+id+"'";
        }
        else if(Class==2){
            sql="delete from classtworesult where StudentId='"+id+"'";
        }
        else if(Class==3){
            sql="delete from classthreeresult where StudentId='"+id+"'";
        }
        else if(Class==4){
            sql="delete from classfourresult where StudentId='"+id+"'";
        }
        else if(Class==5){
            sql="delete from classfiveresult where StudentId='"+id+"'";
        }
        try{
            st=con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
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


    public void updateMarks(int Class,int id, int eng,int math,int env,int art) {
        Statement st=null;
        String sql=null;
        if(Class==1){
            sql="update classoneresult set english='"+eng+"',mathematics='"+math+"',envscience='"+env+"',art='"+art+"'";
        }
        else if(Class==2){
            sql="update classtworesult set english='"+eng+"',mathematics='"+math+"',envscience='"+env+"',art='"+art+"'";
        }
        else if(Class==3){
            sql="update classthreeresult set english='"+eng+"',mathematics='"+math+"',envscience='"+env+"',art='"+art+"'";
        }
        else if(Class==4){
            sql="update classfourresult set english='"+eng+"',mathematics='"+math+"',envscience='"+env+"',art='"+art+"'";
        }
        if(Class==5){
            sql="update classfiveresult set english='"+eng+"',mathematics='"+math+"',envscience='"+env+"',art='"+art+"'";
        }
        try{
            st=con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }        }
    }

    public String CalculateResult(double totalmarks,double obtainmarks){
        double result=(obtainmarks/totalmarks)*100.0;
        if(result<50){
            return "Failed";
        }
        else{
            return "passed";
        }
    }
}
