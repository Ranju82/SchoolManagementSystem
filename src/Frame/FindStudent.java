package Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindStudent extends JFrame{
    private Connection con;
    private JPanel student;
    private JTable studentTable;
    private JButton Class5Field;
    private JButton Class1Field;
    private JButton Class2Field;
    private JButton Class3Field;
    private JButton Class4Field;
    private JScrollPane paneField;
    private JButton logOutField;
    private JButton HomeField;


    public FindStudent(Connection con){
        JFrame StudentFrame=new JFrame("Student");
        StudentFrame.setContentPane(student);
        StudentFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        StudentFrame.pack();
        StudentFrame.setVisible(true);
        this.con=con;

        Class1Field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from classonestudent";
                showStudent(sql);
            }
        });

        Class2Field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from classtwostudent";
                showStudent(sql);
            }
        });

        Class3Field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from classthreestudent";
                showStudent(sql);
            }
        });

        Class4Field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from classfourstudent";
                showStudent(sql);
            }
        });

        Class5Field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from classfivestudent";
                showStudent(sql);
            }
        });

        HomeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new Home();
              StudentFrame.dispose();
            }
        });

        logOutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new Login();
               StudentFrame.dispose();
            }
        });
    }

    public void showStudent(String sql){
        Statement st=null;
        ResultSet rs=null;
        String[] columnNames = {"StudentId", "Student Name","Gender","Father Name","Mother name","DOB","Address","Contact no."};
        DefaultTableModel model=new DefaultTableModel(columnNames,0);
        try{
            st=con.createStatement() ;
            rs=st.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                String Gender=rs.getString(3);
                String father=rs.getString(4);
                String mother=rs.getString(5);
                String dob=rs.getString(6);
                String address=rs.getString(7);
                String number=rs.getString(8);
                Object[] row={id,name,Gender,father,mother,dob,address,number};
                model.addRow(row);
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
        studentTable=new JTable(model);
        paneField.setViewportView(studentTable);
    }
}

