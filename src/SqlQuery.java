import org.w3c.dom.Text;

import java.sql.*;

public class SqlQuery {
    public String queryEmail ;
    public String queryPassword ;

    public String adminQueryEmail ;
    public String adminQueryPassword ;

    String jdbcUrl = ("jdbc:sqlite:/C:\\Users\\LEGION\\Desktop\\WayToPayDB\\waytopay.db");

    public void insert(String email, String password , String telephone , String address , String gender) {
        String sql = "INSERT INTO users (email,password,telephone,address,gender) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, telephone);
            pstmt.setString(4, address);
            pstmt.setString(5, gender);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*public void signUpQuery(){

        Connection con = null ;
        String jdbcUrl = ("jdbc:sqlite:/C:\\Users\\LEGION\\wayto_pay.db");
        String email = "oztekinberkin5@gmail.com";
        try {
            con = DriverManager.getConnection(jdbcUrl);
            String sql =("INSERT INTO users (name , password , telephone , address , gender) VALUES  + email");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                String name = result.getString("NAME");
                String password = result.getString("PASSWORD");
                System.out.println(name +  " | " + password);
            }

        }catch (Exception ex){
            System.out.println(ex.getClass().getName() + " : " + ex.getMessage());
            System.out.println(0);
        }
        System.out.println("Database opened successfully");
    }

     */
    public String loginQuery(String email , String password){

        Connection con = null ;
        String sql = "SELECT email,password FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                queryEmail= resultSet.getString("email");
                queryPassword= resultSet.getString("PASSWORD");

                System.out.println(queryEmail + " | " + queryPassword);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return queryEmail + " "  + queryPassword ;
    }
    public void adminLoginQuery(String email , String password){

        Connection con = null ;
        String sql = "SELECT adminID,password FROM admins WHERE adminID = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                adminQueryEmail = resultSet.getString("adminID");
                adminQueryPassword = resultSet.getString("password");

                System.out.println(adminQueryEmail + " | " + adminQueryPassword);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void productInsert(String productName , String price , String category , String amount ){
        String sql = "INSERT INTO products (productName , price , category, amount) VALUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            pstmt.setString(2, price);
            pstmt.setString(3, category);
            pstmt.setString(4, amount);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateProduct(String price  , String amount , String id){
        String sql = " UPDATE products SET price = ? , amount = ? WHERE productID = ? " ;

        try (Connection conn = DriverManager.getConnection(jdbcUrl); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1 , id);
            pstmt.setString(3, price);
            pstmt.setString(5, amount);

            pstmt.executeUpdate(sql);
            System.out.println("Database updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
