import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    public JTextField textFieldEmail;
    public JTextField textFieldPassword;

    public LoginPage() {
        SqlQuery sqlQuery = new SqlQuery();
        JFrame jFrame = new JFrame("WayToPay");
        JPanel jPanel = new JPanel();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((dim.width/2 -jFrame.getSize().width/2), dim.height/4-jFrame.getSize().height/2);

        jFrame.setSize(360, 360);
        jPanel.setLayout(null);


        JLabel imageLabel = new JLabel();

        Image image = new ImageIcon(this.getClass().getResource("/Logo/WTPlogo.png")).getImage();
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setBounds(75 , 0 , 450 , 150);
        jFrame.getContentPane().add(imageLabel);


        JLabel userLabel = new JLabel("Email");
        userLabel.setBounds(10 , 175 , 80 , 25);
        jPanel.add(userLabel);

        textFieldEmail = new JTextField(20);
        textFieldEmail.setBounds(100 , 175 , 200 , 25);
        jPanel.add(textFieldEmail);
        textFieldEmail.getText();

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 210 , 80 , 25);
        jPanel.add(passwordLabel);

        textFieldPassword = new JTextField(20);
        textFieldPassword.setBounds(100 , 210 , 200 , 25);
        jPanel.add(textFieldPassword);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100 , 250 , 100 , 30 );
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textFieldEmail.getText();
                String password = textFieldPassword.getText();

                sqlQuery.loginQuery(email, password);
                    if (textFieldEmail.getText().equals("") || textFieldPassword.getText().equals("")) {
                        new Dialog("Please enter username or password");

                    } else if (!textFieldPassword.getText().equals(sqlQuery.queryPassword) || !textFieldEmail.getText().equals(sqlQuery.queryEmail)) {
                        new Dialog("Your password or email wrong");

                    } else if (textFieldPassword.getText().equals(sqlQuery.queryPassword) && textFieldPassword.getText().equals(sqlQuery.queryPassword)) {
                        System.out.println("You logged inside ");
                        jFrame.setVisible(false);
                        UserPanel userPanel = new UserPanel();
                    }

                    sqlQuery.adminLoginQuery(email , password);

                    if(textFieldPassword.getText().equals(sqlQuery.adminQueryPassword) && textFieldEmail.getText().equals(sqlQuery.adminQueryEmail) ){
                        jFrame.setVisible(false);
                        AdminPanel adminPanel = new AdminPanel();
                    }

            }
        });
        jPanel.add(loginButton);


        JButton signUpButton = new JButton("Register");
        signUpButton.setBounds(200 , 250 , 100 , 30 );

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jFrame.setVisible(false);
                    SignUpPage singUpPage = new SignUpPage();

                }catch (Exception ex ){
                    ex.printStackTrace();
                }
                //System.out.println("Hello new Customer ");

            }
        });
        jPanel.add(signUpButton);

        jPanel.setBackground(Color.white);
        jFrame.setResizable(false);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
    public static class Dialog{
        private static JDialog jDialog ;
        public String attention;
        public Dialog(String attention){
            this.attention = attention ;

            JFrame jFrame = new JFrame();
            jDialog = new JDialog(jFrame ,"Dialog" , true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            jDialog.setLayout(new FlowLayout());
            JButton jButton = new JButton("OK");
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jDialog.setVisible(false);
                }
            });
            jDialog.add(new JLabel(attention));
            jDialog.add(jButton);
            jDialog.setSize(250,100);
            jDialog.setLocation((dim.width/2 - jFrame.getSize().width / 2), dim.height / 3 - jFrame.getSize().height/4);
            jDialog.setVisible(true);
        }
    }
}

