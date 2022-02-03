import jdk.jfr.Category;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class UserPanel {
    public UserPanel() {
        JFrame jFrame = new JFrame("User Panel");
        FlowLayout flowLayout = new FlowLayout();
        JPanel panel = new JPanel(flowLayout);
        String columns[] = {"Product ID", "Product Name", "Price", "Category", "Amount"};
        String data[][] = new String[10][5];

        JButton jButtonExit = new JButton("Exit");
        panel.add(jButtonExit);


        jFrame.add(panel);
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((dim.width/2 -jFrame.getSize().width/2), dim.height/2-jFrame.getSize().height/2);

        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                LoginPage loginPage = new LoginPage();
            }
        });

        try {

            String url = "jdbc:sqlite:/C:\\Users\\LEGION\\Desktop\\WayToPayDB\\waytopay.db";
            Connection con = DriverManager.getConnection(url);

            String query = "SELECT * FROM products";

            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);


            int i = 0;
            while (res.next()) {

                int id = res.getInt("productID");
                String productID = res.getString("productName");
                Double price = res.getDouble("price");
                String category = res.getString("category");
                int amount = res.getInt("amount");
                data[i][0] = id + "";
                data[i][1] = productID;
                data[i][2] = price + "";
                data[i][3] = category + "";
                data[i][4] = amount + "";
                i++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            JScrollPane pane = new JScrollPane(table);
            panel.add(pane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


