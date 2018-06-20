package Frame;

import com.toedter.calendar.JDateChooser;
import database.ConnectToDb;
import model.Staff;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class AddStaff {

    JDateChooser dateChooser=new JDateChooser();
    private JPanel AddStaffPanel;
    private JPanel DatePanel;

    private JComboBox StaffField;
    private JTextField IdField;
    private JTextField NameField;
    private JTextField FatherField;
    private JTextField MotherField;
    private JTextField QualificationField;
    private JTextField AddressField;
    private JButton cancelButton;
    private JButton addButton;
    private JButton LogOutField;
    private JButton HomeField;

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


    public AddStaff(){
        JFrame addStaff=new JFrame();
        DatePanel.add(dateChooser);
        StaffField.setSelectedIndex(0);
        StaffField.setEditable(true);
        addStaff.setContentPane(AddStaffPanel);
        addStaff.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addStaff.pack();
        addStaff.setVisible(true);

        HomeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                addStaff.dispose();
            }
        });

        LogOutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                addStaff.dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Id should be a number!!!!");
                    addStaff.dispose();
                    new AddStaff();
                }
                else if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field!!!!");
                    addStaff.dispose();
                    new AddStaff();
                }
                else {
                    staffType = String.valueOf(StaffField.getSelectedItem());
                    id = Integer.parseInt(IdField.getText());
                    name = NameField.getText();
                    father = FatherField.getText();
                    mother = MotherField.getText();
                    qualification = QualificationField.getText();
                    address = AddressField.getText();
                    doj = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
                    staff.AddStaffToDatabase(staffType, id, name, father, mother, qualification, address, doj);
                    JOptionPane.showMessageDialog(null, "'" + staffType + " Successfully added");
                    addStaff.dispose();
                    new AddStaff();
                }
            }
        });
    }

    public Boolean CheckId(){
        Boolean result=false;
        try{
            id = Integer.parseInt(IdField.getText());
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
        doj=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        address=AddressField.getText();
        if(name.equals("")||father.equals("")||mother.equals("")||qualification.equals("")||doj.equals("")||address.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

}
