package Frame;

import database.ConnectToDb;
import model.LoginHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private JPanel Login;
    private JTextField UserNameField;
    private JPasswordField PasswordField;
    private JButton LoginField;
    private JButton CancelField;

    public Login(){

        JFrame login=new JFrame("Login");
        login.setContentPane(Login);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.pack();
        login.setVisible(true);

        LoginField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String UserName=UserNameField.getText();
                String Password= String.valueOf(PasswordField.getPassword());
                ConnectToDb cndb=new ConnectToDb();
                LoginHandler lg=new LoginHandler(cndb.con);
                boolean result=lg.LoginCheck(UserName,Password);
                if(result) {
                    new Home();
                    login.dispose();

                }
                else{
                    JOptionPane.showMessageDialog(null,"User Name or Password is incorrect!!!!!");
                }
            }
        });

        CancelField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            login.dispose();
            }
        });
    }

}
