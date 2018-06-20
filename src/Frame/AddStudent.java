package Frame;

import com.toedter.calendar.JDateChooser;
import database.ConnectToDb;
import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudent extends JFrame  {

    JDateChooser dateChooser=new JDateChooser();
    private JPanel AddStudentPanel;
    private JPanel DatePanel;

    private JComboBox ClassField;
    private JComboBox GenderField;
    private JTextField MotherField;
    private JTextField NameField;
    private JTextField IdField;
    private JTextField FatherField;
    private JTextField AddressField;
    private JTextField ContactField;
    private JButton AddStudentField;
    private JButton CancelField;
    private JButton logOutField;
    private JButton HomeField;

    int classes;
    int id;
    String name;
    String gender;
    String father;
    String mother;
    String dob;
    String address;
    String contact;

    public AddStudent(){
      JFrame addStudent=new JFrame("Add Student");
      DatePanel.add(dateChooser);
      ClassField.setSelectedIndex(0);
      ClassField.setEditable(true);
      GenderField.setSelectedIndex(0);
      GenderField.setEditable(true);
      addStudent.setContentPane(AddStudentPanel);
      addStudent.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      addStudent.pack();
      addStudent.setVisible(true);


AddStudentField.addActionListener(new ActionListener() {
@Override
            public void actionPerformed(ActionEvent e) {
                if(CheckId()){
                    JOptionPane.showMessageDialog(null,"Id should be a number");
                    addStudent.dispose();
                    new AddStudent();
                }
                else if(CheckNullValue()){
                    JOptionPane.showMessageDialog(null,"Please enter all the field");
                    addStudent.dispose();
                    new AddStudent();
                } else if (CheckContactNumber()) {
                    JOptionPane.showMessageDialog(null,"Please a valid contact number");
                    addStudent.dispose();
                    new AddStudent();
                }
                else{
                    classes= Integer.parseInt(String.valueOf(ClassField.getSelectedItem()));
                    id = Integer.parseInt(IdField.getText());
                    name=NameField.getText();
                    gender=String.valueOf(GenderField.getSelectedItem());
                    father=FatherField.getText();
                    mother=MotherField.getText();
                    dob=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
                    address=AddressField.getText();
                    contact=ContactField.getText();
                    ConnectToDb cndb=new ConnectToDb();
                    Student student=new Student(cndb.con);
                    student.addStudent(classes,id,name,gender,father,mother,dob,address,contact);
                    JOptionPane.showMessageDialog(null,"Student added successfully");
                    addStudent.dispose();
                    new AddStudent();
                }
            }
        });

        CancelField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
             addStudent.dispose();
            }
        });

    HomeField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Home();
            addStudent.dispose();
        }
    });

    logOutField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Login();
            addStudent.dispose();
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
        gender=String.valueOf(GenderField.getSelectedItem());
        father=FatherField.getText();
        mother=MotherField.getText();
        dob=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
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
        if(ContactField.getText().length()!=10){
            result=true;
        }
        return result;
    }
}
