package Frame;

import database.ConnectToDb;
import model.ExamMarks;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class AnalyseExam {
    private double totalmarks=400;
    private JComboBox ClassField;
    private JComboBox IdField;
    private JButton ShowField;
    private JTextField EnglishField;
    private JTextField MathField;
    private JTextField ArtField;
    private JButton LogOutField;
    private JButton AddField;
    private JButton ShowResultField;
    private JTextField EnvField;
    private JButton HomeField;
    private JButton UpdateField;
    private JPanel AnalysePanel;
    private JTextField NameField;
    private JButton DeleteField;

    ConnectToDb cndb=new ConnectToDb();
    ExamMarks examMarks=new ExamMarks(cndb.con);

    int Class,id,eng,math,env,art;
    String name;

    public AnalyseExam(){
        JFrame analyseFrame=new JFrame();
        analyseFrame.setContentPane(AnalysePanel);
        analyseFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        analyseFrame.pack();
        analyseFrame.setVisible(true);

        HomeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                analyseFrame.dispose();
            }
        });

        LogOutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                analyseFrame.dispose();
            }
        });

        ClassField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
             IdField.removeAllItems();
             populateId(cndb.con);
            }
        });

        ShowField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Please select student Id!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else{
                    populateStudentName(cndb.con);
                    populateTexfield(cndb.con);
                    if(CheckNullValue()){
                        JOptionPane.showMessageDialog(null,"Marks have not been added for this Student!!!!");
                    }
                }
            }
        });

        AddField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Please select student Id!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else if(checkMarks()){
                    JOptionPane.showMessageDialog(null,"Marks should be an Integer!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else {
                    Class=Integer.parseInt(String.valueOf(ClassField.getSelectedItem()));
                    id=Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
                    name=NameField.getText();
                    eng=Integer.parseInt(EnglishField.getText());
                    math=Integer.parseInt(MathField.getText());
                    env=Integer.parseInt(EnvField.getText());
                    art=Integer.parseInt(ArtField.getText());
                    examMarks.addMarks(Class,id,name,eng,math,env,art);
                    JOptionPane.showMessageDialog(null,"Marks has been entered successfully");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
            }
        });

        DeleteField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Please select student Id!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else {
                    Class = Integer.parseInt(String.valueOf(ClassField.getSelectedItem()));
                    id = Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
                    examMarks.deleteMarks(Class,id);
                    JOptionPane.showMessageDialog(null, "Marks has been Deleted successfully");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
            }
        });

        UpdateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Please select student Id!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else if(checkMarks()){
                    JOptionPane.showMessageDialog(null,"Marks should be an Integer!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else {
                    Class=Integer.parseInt(String.valueOf(ClassField.getSelectedItem()));
                    id=Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
                    eng=Integer.parseInt(EnglishField.getText());
                    math=Integer.parseInt(MathField.getText());
                    env=Integer.parseInt(EnvField.getText());
                    art=Integer.parseInt(ArtField.getText());
                    examMarks.updateMarks(Class,id,eng,math,env,art);
                    JOptionPane.showMessageDialog(null,"Marks has been Updated successfully");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
            }
        });

        ShowResultField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double  marksobtain;
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Please select student Id!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else if(checkMarks()){
                    JOptionPane.showMessageDialog(null,"Marks should be an Integer!!!!!");
                    analyseFrame.dispose();
                    new AnalyseExam();
                }
                else{
                    name=NameField.getText();
                    eng=Integer.parseInt(EnglishField.getText());
                    math=Integer.parseInt(MathField.getText());
                    env=Integer.parseInt(EnvField.getText());
                    art=Integer.parseInt(ArtField.getText());
                    marksobtain=eng+math+env+art;
                    String result=examMarks.CalculateResult(totalmarks,marksobtain);
                    JOptionPane.showMessageDialog(null,"Student name: "+name+"\n Total Marks: "+totalmarks+"\n Marks obtain: "+marksobtain+"\n Result: "+result);
                }
            }
        });

    }

    public void populateId(Connection con){
        Class=Integer.parseInt(String.valueOf(ClassField.getSelectedItem()));
        Statement st=null;
        ResultSet rs=null;
        String sql=null;
        if(Class==1){
            sql="select studentid from classonestudent";
        }
        else if(Class==2){
            sql="select studentid from classtwostudent";
        }
        else if(Class==3){
            sql="select studentid from classthreestudent";
        }
        else if(Class==4){
            sql="select studentid from classfourstudent";
        }
        else if(Class==5){
            sql="select studentid from classfivestudent";
        }
        try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                IdField.addItem(rs.getInt(1));
            }
        } catch (SQLException e) {
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
    }

    public void populateStudentName(Connection con){
        Class=Integer.parseInt(String.valueOf(ClassField.getSelectedItem()));
        Statement st=null;
        ResultSet rs=null;
        String sql=null;
        if(Class==1){
            sql="select studentname from classonestudent";
        }
        else if(Class==2){
            sql="select studentname from classtwostudent";
        }
        else if(Class==3){
            sql="select studentname from classthreestudent";
        }
        else if(Class==4){
            sql="select studentname from classfourstudent";
        }
        else if(Class==5){
            sql="select studentname from classfivestudent";
        }
        try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                NameField.setText(rs.getString(1));
            }
        } catch (SQLException e) {
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
    }


    public void populateTexfield(Connection con){
        Statement st=null;
        ResultSet rs=null;
        String sql=null;
        Class=Integer.parseInt(String.valueOf(ClassField.getSelectedItem()));
        id=Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
        EnglishField.setText("");
        MathField.setText("");
        EnvField.setText("");
        ArtField.setText("");
        if(Class==1){
            sql="select * from classoneresult where StudentId='"+id+"'";
        }
        else if(Class==2){
            sql="select * from classtworesult where StudentId='"+id+"'";
        }
        else if(Class==3){
            sql="select * from classthreeresult where StudentId='"+id+"'";
        }
        else if(Class==4){
            sql="select * from classfourresult where StudentId='"+id+"'";
        }
        else if(Class==5){
            sql="select * from classfiveresult where StudentId='"+id+"'";
        }
        try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                EnglishField.setText(rs.getString(3));
                MathField.setText(rs.getString(4));
                EnvField.setText(rs.getString(5));
                ArtField.setText(rs.getString(6));
            }
        } catch (SQLException e) {
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
    }

    public Boolean CheckId(){
        Boolean result=false;
        try{
            id=Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
        }catch (NumberFormatException ex){
            result=true;
        }
        return result;
    }

    public Boolean CheckNullValue(){
        if(EnglishField.getText().equals("")||MathField.getText().equals("")||EnvField.getText().equals("")||ArtField.getText().equals("")){
            return true;
        }
        else{return false;}
    }

    public boolean checkMarks(){
        Boolean result=false;
        try{
            id=Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
            eng=Integer.parseInt(EnglishField.getText());
            math=Integer.parseInt(MathField.getText());
            env=Integer.parseInt(EnvField.getText());
            art=Integer.parseInt(ArtField.getText());
    }catch (NumberFormatException ex){
        result=true;
        }
        return result;
    }

}
