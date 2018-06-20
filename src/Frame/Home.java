package Frame;

import database.ConnectToDb;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Home {
    private JPanel HomePanel;
    private JButton addStaffButton;
    private JButton AddStudentField;
    private JButton ExamField;
    private JButton AnalyseField;
    private JButton StaffField;
    private JButton StudentField;
    private JButton LogoutField;
    private JButton UpdateStudentField;
    private JButton UpdateStaffField;

    public Home(){
        JFrame home=new JFrame();
        home.setContentPane(HomePanel);
        home.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        home.pack();
        home.setVisible(true);
        ConnectToDb cndb=new ConnectToDb();

        StudentField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudent(cndb.con);
                home.dispose();
            }
        });

        AddStudentField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudent();
                home.dispose();
            }
        });

        UpdateStudentField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStudent();
                home.dispose();
            }
        });

        LogoutField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                home.dispose();
            }
        });

        StaffField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStaff();
                home.dispose();
            }
        });

        addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStaff();
                home.dispose();
            }
        });

        UpdateStaffField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStaff();
                home.dispose();
            }
        });

        ExamField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExamResult();
                home.dispose();
            }
        });

        AnalyseField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnalyseExam();
                home.dispose();
            }
        });
    }


}
