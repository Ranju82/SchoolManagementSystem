package Frame;

import database.ConnectToDb;
import model.Student;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateStudent extends JFrame{

    private JButton logOutField;
    private JButton updateField;
    private JButton deleteField;
    private JButton HomeField;
    private JComboBox ClassField;
    private JComboBox IdField;
    private JTextField NameField;
    private JTextField GenderField;
    private JTextField FatherName;
    private JTextField MotherField;
    private JTextField DobField;
    private JTextField AddressField;
    private JTextField ContactField;
    private JPanel UpdatePanel;
    private JButton SearchField;

    int Class;
    int id;
    String name;
    String gender;
    String father;
    String mother;
    String dob;
    String address;
    String contact;


    public UpdateStudent(){

        JFrame updateFrame=new JFrame();
        ClassField.setSelectedIndex(0);
        ClassField.setEditable(true);
        IdField.setEditable(true);
        updateFrame.setContentPane(UpdatePanel);
        updateFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        updateFrame.pack();
        updateFrame.setVisible(true);

        ConnectToDb cndb=new ConnectToDb();
        Student student =new Student(cndb.con);

        ClassField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                IdField.removeAllItems();
                populate(cndb.con);
            }
        });

        SearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CheckId()) {
                    JOptionPane.showMessageDialog(null, "Please select an Id!!!!!");
                    updateFrame.dispose();
                    new UpdateStudent();
                } else {
                    populateTextField(cndb.con);
                }
            }
        });

        HomeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                updateFrame.dispose();
            }
        });

        logOutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                updateFrame.dispose();
            }
        });

        deleteField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class =(Integer.parseInt(String.valueOf(ClassField.getSelectedItem())));
                id=(Integer.parseInt(String.valueOf(IdField.getSelectedItem())));
                student.DeleteStudent(Class,id);
                JOptionPane.showMessageDialog(null,"Student deleted successfully from the database");
                updateFrame.dispose();
                new UpdateStudent();
            }
        });

        updateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field!!!!!");
                    updateFrame.dispose();
                    new UpdateStudent();
                }
                else if(CheckContactNumber()){
                    JOptionPane.showMessageDialog(null,"Please enter valid contact umber!!!!!");
                    updateFrame.dispose();
                    new UpdateStudent();
                }
                else {
                    Class = (Integer.parseInt(String.valueOf(ClassField.getSelectedItem())));
                    id = (Integer.parseInt(String.valueOf(IdField.getSelectedItem())));
                    name = NameField.getText();
                    gender = GenderField.getText();
                    father = FatherName.getText();
                    mother = MotherField.getText();
                    dob = DobField.getText();
                    address = AddressField.getText();
                    contact = ContactField.getText();
                    student.updateStudent(Class, id, name, gender, father, mother, dob, address, contact);
                    JOptionPane.showMessageDialog(null, "Student updated successfully");
                    updateFrame.dispose();
                    new UpdateStudent();
                }
            }
        });

    }

    private void populate(Connection con) {
        Statement st=null;
        ResultSet rs=null;
        Class =(Integer.parseInt(String.valueOf(ClassField.getSelectedItem())));
        String sql="";
        if(Class==1){
            sql="select studentid from classonestudent;";
        }
        else if(Class==2){
            sql="select studentid from classtwostudent;";
        }
        else if(Class==3){
            sql="select studentid from classthreestudent;";
        }
        else if(Class==4){
            sql="select studentid from classfourstudent;";
        }
        else if(Class==5){
            sql="select studentid from classfivestudent;";
        }
        try{
            st=con.createStatement() ;
            rs=st.executeQuery(sql);
            while(rs.next()){
                IdField.addItem(rs.getString(1));
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
    }

    public void populateTextField(Connection con){
        Class =(Integer.parseInt(String.valueOf(ClassField.getSelectedItem())));
        id=(Integer.parseInt(String.valueOf(IdField.getSelectedItem())));
        Statement st=null;
        ResultSet rs=null;
        String sql="";
        if(Class==1){
            sql="select * from classonestudent where studentid='"+id+"'";
        }
        else if(Class==2){
            sql="select * from classtwostudent where studentid='"+id+"'";
        }
        else if(Class==3){
            sql="select * from classthreestudent where studentid='"+id+"'";
        }
        else if(Class==4){
            sql="select * from classfourstudent where studentid='"+id+"'";
        }
        else if(Class==5){
            sql="select * from classfivestudent where studentid='"+id+"'";
        }

        try{
            st=con.createStatement() ;
            rs=st.executeQuery(sql);
            while(rs.next()){
                NameField.setText(rs.getString(2));
                GenderField.setText(rs.getString(3));
                FatherName.setText(rs.getString(4));
                MotherField.setText(rs.getString(5));
                DobField.setText(rs.getString(6));
                AddressField.setText(rs.getString(7));
                ContactField.setText(rs.getString(8));
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
    }
    public Boolean CheckId(){
        Boolean result=false;
        try{
            id = Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
        }catch (NumberFormatException ex){
            result=true;
        }
        return result;
    }

    public Boolean CheckNullValue(){
       name=NameField.getText();
       gender=GenderField.getText();
       father=FatherName.getText();
       mother=MotherField.getText();
       dob=DobField.getText();
       address=AddressField.getText();
        if(name.equals("")||gender.equals("")||father.equals("")||mother.equals("")||dob.equals("")||address.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean CheckContactNumber(){
        Boolean result=false;
        String regexStr = "^[0-9]*$";
        if(ContactField.getText().equals(regexStr)){
            result=true;
        }
        if(ContactField.getText().length()<10){
            result=true;
        }
        return result;
    }
}
