package Frame;

import database.ConnectToDb;
import model.Staff;

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

public class UpdateStaff {
    private JComboBox StaffTypeField;
    private JComboBox IdField;
    private JTextField NameField;
    private JButton SearchField;
    private JTextField FatherField;
    private JTextField MotherField;
    private JTextField QualificationField;
    private JTextField AddressField;
    private JTextField DojField;
    private JButton UpdateField;
    private JButton DeleteField;
    private JButton LogOutField;
    private JButton HomeField;
    private JPanel UpdatePanel;

    ConnectToDb cndb=new ConnectToDb();
    Staff staff=new Staff(cndb.con);

    String staffType;
    int id;
    String name;
    String father;
    String mother;
    String qualification;
    String address;
    String doj;

    public UpdateStaff(){
        JFrame updateFrame=new JFrame();
        updateFrame.setContentPane(UpdatePanel);
        updateFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        updateFrame.pack();
        updateFrame.setVisible(true);

        HomeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFrame.dispose();
                new Home();
            }
        });

        LogOutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFrame.dispose();
                new Login();
            }
        });

        StaffTypeField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                IdField.removeAllItems();
                populateId(cndb.con);
            }
        });

        SearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Please select an id!!!!");
                    updateFrame.dispose();
                    new UpdateStaff();
                }
                else {
                    populateTexfield(cndb.con);
                }
            }

        });

        UpdateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field!!!!");
                    updateFrame.dispose();
                    new UpdateStaff();
                }
                else {
                    staffType = String.valueOf(StaffTypeField.getSelectedItem());
                    id = Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
                    name = NameField.getText();
                    father = FatherField.getText();
                    mother = MotherField.getText();
                    qualification = QualificationField.getText();
                    address = AddressField.getText();
                    doj = DojField.getText();
                    staff.UpdateStaffToDatabase(staffType, id, name, father, mother, qualification, address, doj);
                    JOptionPane.showMessageDialog(null, "''" + staffType + "' successfully updated");
                    updateFrame.dispose();
                    new UpdateStaff();
                }
            }
        });

        DeleteField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffType=String.valueOf(StaffTypeField.getSelectedItem());
                int id=Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
                staff.DeleteStaffFromDatabase(staffType,id);

                JOptionPane.showMessageDialog(null,"''"+staffType+"' successfully deleted");
                updateFrame.dispose();
                new UpdateStaff();
            }
        });
    }

    public void populateId(Connection con){
        Statement st=null;
        ResultSet rs=null;
        String sql=null;
        String staffType= String.valueOf(StaffTypeField.getSelectedItem());
        if(staffType.equals("Teacher")){
            sql="select TeacherId from teacher";
        }
        else if(staffType.equals("Cook")){
            sql="select CookId from cook";
        }
        else if(staffType.equals("Chowkidar")){
            sql="select ChowkidarId from chowkidar";
        }
        try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                IdField.addItem(rs.getString(1));
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
        String staffType=String.valueOf(StaffTypeField.getSelectedItem());
        int id=Integer.parseInt(String.valueOf(IdField.getSelectedItem()));
        if(staffType.equals("Teacher")){
            sql="select * from teacher where TeacherId='"+id+"'";
        }
        else if(staffType.equals("Cook")){
            sql="select * from cook where CookId='"+id+"'";
        }
        else if(staffType.equals("Chowkidar")){
            sql="select * from chowkidar where ChowkidarId='"+id+"'";
        }
        try{
            st=con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                NameField.setText(rs.getString(2));
                FatherField.setText(rs.getString(3));
                MotherField.setText(rs.getString(4));
                QualificationField.setText(rs.getString(5));
                AddressField.setText(rs.getString(6));
                DojField.setText(rs.getString(7));
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
        name=NameField.getText();
        father=FatherField.getText();
        mother=MotherField.getText();
        qualification=QualificationField.getText();
        doj=DojField.getText();
        address=AddressField.getText();
        if(name.equals("")||father.equals("")||mother.equals("")||qualification.equals("")||doj.equals("")||address.equals("")){
            return true;
        }
        else{
            return false;
        }
    }
}
