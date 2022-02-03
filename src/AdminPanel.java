
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminPanel {
    DefaultTableModel model ;

    public AdminPanel(){
        SqlQuery sqlQuery = new SqlQuery();
        JFrame frame = new JFrame();
        JTable table = new JTable();

        Object[] columns = {"ID","Product Name","Price","Category","Amount"};
        String data[][] = new String[10][5];


        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);


        JTextField textProductName = new JTextField();
        JTextField textPrice = new JTextField();
        JTextField textCategory = new JTextField();
        JTextField textAmount = new JTextField();
        JTextField textId = new JTextField();

        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");

        textId.setBounds(20 , 220 , 100 , 25);
        textProductName.setBounds(20, 250, 100, 25);
        textPrice.setBounds(20, 280, 100, 25);
        textCategory.setBounds(20, 310, 100, 25);
        textAmount.setBounds(20 , 340 , 100 , 25 );

        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);




        frame.setLayout(null);
        frame.add(pane);
        frame.add(textProductName);
        frame.add(textPrice);
        frame.add(textCategory);
        frame.add(textAmount);
        frame.add(textId);
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);



        Object[] row = new Object[5];

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
            model = new DefaultTableModel(data ,columns);
            table.setModel(model);
            table.setBackground(Color.LIGHT_GRAY);
            table.setForeground(Color.black);
        }catch (Exception e){
            e.printStackTrace();
        }

        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = textId.getText();
                row[1] = textProductName.getText();
                row[2] = textPrice.getText();
                row[3] = textCategory.getText();
                row[4] = textAmount.getText();

                sqlQuery.productInsert(textProductName.getText(),textPrice.getText() ,textCategory.getText()  , textAmount.getText());
                model.addRow(row);
            }
        });

        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if(i >= 0){
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });

        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){

                int i = table.getSelectedRow();

                textId.setText(model.getValueAt(i , 0).toString());
                textProductName.setText(model.getValueAt(i, 1).toString());
                textPrice.setText(model.getValueAt(i, 2).toString());
                textCategory.setText(model.getValueAt(i, 3).toString());
                textAmount.setText(model.getValueAt(i , 4).toString());
            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if(i >= 1) {
                    model.setValueAt(textId.getText(), i , 0);
                    model.setValueAt(textPrice.getText(), i, 1);
                    model.setValueAt(textAmount.getText(), i , 3);

                }

                    try {
                        sqlQuery.updateProduct(textPrice.getText() , textAmount.getText() ,textId.getText());

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }


            }
        });

        frame.setSize(850,425);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);

    }
}






