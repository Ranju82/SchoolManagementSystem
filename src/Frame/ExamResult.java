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

public class ExamResult {
    private JButton ClassOneField;
    private JTable ResultTable;
    private JButton ClassFiveField;
    private JButton ClassFourField;
    private JButton ClassTwoField;
    private JButton ClassThreeField;
    private JButton LogOutField;
    private JButton HomeField;
    private JPanel ResultPanel;
    private JScrollPane ResultScrollPane;

    private Connection con;
    ConnectToDb cndb=new ConnectToDb();

    public ExamResult(){
        JFrame resultFrame=new JFrame();
        resultFrame.setContentPane(ResultPanel);
        resultFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        resultFrame.pack();
        resultFrame.setVisible(true);

        HomeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                resultFrame.dispose();
            }
        });

        LogOutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                resultFrame.dispose();
            }
        });

        ClassOneField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="Select * from classoneresult";
                ShowResult(cndb.con,sql);
            }
        });

        ClassTwoField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="Select * from classtworesult";
                ShowResult(cndb.con,sql);
            }
        });

        ClassThreeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="Select * from classthreeresult";
                ShowResult(cndb.con,sql);
            }
        });

        ClassFourField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="Select * from classfourresult";
                ShowResult(cndb.con,sql);
            }
        });

        ClassFiveField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="Select * from classfiveresult";
                ShowResult(cndb.con,sql);
            }
        });
    }

    public void ShowResult(Connection con,String sql){
        String[] columnNames = {"Studebt Id", "Student Name","English","Mathematics","Environment Science","Art"};
        DefaultTableModel model=new DefaultTableModel(columnNames,0);
        Statement st=null;
        ResultSet rs=null;
        try{
            st=con.createStatement() ;
            rs=st.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                int english=rs.getInt(3);
                int math=rs.getInt(4);
                int env=rs.getInt(5);
                int art=rs.getInt(6);
                Object[] row={id,name,english,math,env,art};
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
        ResultTable=new JTable(model);
        ResultScrollPane.setViewportView(ResultTable);
    }
}
