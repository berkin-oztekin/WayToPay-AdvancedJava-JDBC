import org.sqlite.SQLiteDataSource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {

    public static void main(String [] args) throws SQLException, IOException {
    LoginPage loginPage = new LoginPage();
    ServerSocket welcomeSocket = new ServerSocket(3334);
    String URL = "jdbc:sqlite:/C:\\Users\\LEGION\\Desktop\\WayToPayDB\\waytopay.db";
    SQLiteDataSource ds = null ;

    try{
        ds = new SQLiteDataSource();
        ds.setUrl(URL);

    }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Database opened Successfully");

        String query = "SELECT * FROM users WHERE id = (?) ";
        ResultSet rs = null ;
        Connection conn = ds.getConnection();
        PreparedStatement preparedStatement =conn.prepareStatement(query);


        Boolean exit = false ;

        while (!exit){
            System.out.println("waiting..");
            Socket connectionSocket = welcomeSocket.accept();
            DataInputStream informClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            System.out.println("Connection accepted");
            int id = informClient.readInt();

            if (id == -1){
                System.out.println("break");
                break;
            }
            preparedStatement.setInt(1 , id );

            try {
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    String name = rs.getString("email");
                    outToClient.writeUTF(name);
                    break;
                }
            }catch (Exception e ){
                e.printStackTrace();
            }finally {
                outToClient.close();
                informClient.close();
                connectionSocket.close();
            }
        }
        System.out.println("Closing");

    }
}

