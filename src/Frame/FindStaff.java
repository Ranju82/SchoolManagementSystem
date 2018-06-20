package Frame;

import database.ConnectToDb;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class FindStaff {
    private JTable StaffTable;
    private JButton TeacherField;
    private JButton ChowkidarField;
    private JButton CookField;
    private JButton LogoutField;
    private JButton HomeField;
    private JPanel StaffPanel;
    private JScrollPane StaffScrollPane;

    private Connection con;
    ConnectToDb cndb=new ConnectToDb();

    public FindStaff(){
        JFrame findStaff=new JFrame("search staff");
        findStaff.setContentPane(StaffPanel);
        findStaff.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        findStaff.pack();
        findStaff.setVisible(true);

        HomeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                findStaff.dispose();
            }
        });

        LogoutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                findStaff.dispose();
            }
        });

        TeacherField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from teacher";
                ShowStaff(cndb.con,sql);
            }
        });

        CookField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from cook";
                ShowStaff(cndb.con,sql);
            }
        });

        ChowkidarField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="select * from chowkidar";
                ShowStaff(cndb.con,sql);
            }
        });
    }

    public void ShowStaff(Connection con,String sql){
        String[] columnNames = {"Stafft Id", "Staff Name","Father Name","Mother name","Qualification","Address","Joining date"};
        DefaultTableModel model=new DefaultTableModel(columnNames,0);
        Statement st=null;
        ResultSet rs=null;
        try{
            st=con.createStatement() ;
            rs=st.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                String father=rs.getString(3);
                String mother=rs.getString(4);
                String qualification=rs.getString(5);
                String address=rs.getString(6);
                String doj=rs.getString(7);
                Object[] row={id,name,father,mother,qualification,address,doj};
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
        StaffTable=new JTable(model);
        StaffScrollPane.setViewportView(StaffTable);
    }
}
