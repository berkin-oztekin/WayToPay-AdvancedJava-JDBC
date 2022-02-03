
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class SignUpPage  {
    private JTextField textFieldPassword ;
    private JTextField textFieldEmail ;
    private JTextField againPassword ;
    private JTextField telNoField ;
    private JTextField textFieldAddress ;
    public SignUpPage () {
        Frame frame = new Frame();
        frame.signUpFrame();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        SqlQuery sqlQuery = new SqlQuery();


        try {
            JLabel imageLabel = new JLabel();
            Image image = new ImageIcon(this.getClass().getResource("/Logo/WTPlogo.png")).getImage();
            imageLabel.setIcon(new ImageIcon(image));
            imageLabel.setBounds(75, 0, 450, 150);
            frame.jFrame.getContentPane().add(imageLabel);

        } catch (Exception ex) {
            System.out.println("Image could not found..");
        }


        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(10, 175, 80, 25);
        jPanel.add(emailLabel);

        JTextField textFieldEmail = new JTextField(20);
        textFieldEmail.setBounds(100, 175, 200, 25);
        String sqlEmail = textFieldEmail.getText();
        jPanel.add(textFieldEmail);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 210, 80, 25);
        jPanel.add(passwordLabel);


        textFieldPassword = new JTextField(20);
        textFieldPassword.setBounds(100, 210, 200, 25);
        jPanel.add(textFieldPassword);

        JLabel againLabel = new JLabel("Password (Again)");
        againLabel.setBounds(10, 245, 80, 25);
        jPanel.add(againLabel);

        JTextField againPassword = new JTextField(20);
        againPassword.setBounds(100, 245, 200, 25);
        jPanel.add(againPassword);

        JLabel againTelNO = new JLabel("Telephone Number");
        againTelNO.setBounds(10, 280, 80, 25);
        jPanel.add(againTelNO);

        JTextField telNoField = new JTextField(20);
        telNoField.setBounds(100, 280, 200, 25);
        jPanel.add(telNoField);

        JLabel addressLable = new JLabel("Address");
        addressLable.setBounds(10, 315, 80, 25);
        jPanel.add(addressLable);

        JTextField textFieldAddress = new JTextField(20);
        textFieldAddress.setBounds(100, 315, 200, 25);
        jPanel.add(textFieldAddress);

        JRadioButton radiobutton1 = new JRadioButton("Male");
        radiobutton1.setBounds(100, 350, 75, 50);
        radiobutton1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    String gender = radiobutton1.getText();
                    System.out.println(gender);
                }
            }
        });
        jPanel.add(radiobutton1);

        JRadioButton radioButton2 = new JRadioButton("Female");
        radioButton2.setBounds(200, 350, 75, 50);
        radioButton2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    String gender = radioButton2.getText();
                    System.out.println(gender);
                }
            }
        });
        jPanel.add(radioButton2);

        jPanel.setBackground(Color.white);
        frame.jFrame.add(jPanel);


        JButton signUpButton = new JButton("Sign-Up");
        signUpButton.setBounds(100, 425, 100, 30);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = textFieldEmail.getText();
                    String password = textFieldPassword.getText();
                    String password2 = againPassword.getText();
                    String telephone = telNoField.getText();
                    String address = textFieldAddress.getText();
                    String gender = radiobutton1.getText();

                    sqlQuery.loginQuery(email,  password); //This will handle it later
                    if (textFieldEmail.getText().equals("") || textFieldPassword.getText().equals("") || textFieldAddress.getText().equals("") || telNoField.getText().equals("")) {
                        new LoginPage.Dialog("Please enter the all blanks");
                    }
                    else if(!againPassword.getText().equals(password)) {
                        new LoginPage.Dialog("Please control your password ");
                    }else if(textFieldEmail.getText().equals(sqlQuery.queryEmail) || textFieldPassword.equals(sqlQuery.queryPassword)){
                        new LoginPage.Dialog("Password or username already using..");
                    }else{
                        sqlQuery.insert(email, password, telephone, address, gender);
                        new LoginPage.Dialog("You sign upped successfully");
                    }

                }catch (Exception ex ){                                          //Catch the appropirate Excepition
                    ex.printStackTrace();                                        //ATTENTION
                }
                //System.out.println("Hello new Customer ");
            }
        });
        jPanel.add(signUpButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200 , 425 , 100 , 30 );


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.jFrame.setVisible(false);
                    LoginPage loginPage = new LoginPage();

                }catch (Exception ex ){
                    ex.printStackTrace();                   //Catch the appropirate Exception
                    //ATTENTION
                }
                //System.out.println("Hello new Customer ");
            }
        });
        jPanel.add(loginButton);
    }

    public static class Frame {
        JFrame jFrame ;
        public void signUpFrame(){

            jFrame = new JFrame();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            jFrame.setLocation((dim.width/2 -jFrame.getSize().width/2), dim.height/4-jFrame.getSize().height/2);


            jFrame.setSize(350 , 500);
            jFrame.setTitle("WayToPay Sign Up");

            jFrame.setResizable(false);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        }
    }
}

