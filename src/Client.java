import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String argv[] ) throws Exception{
        LoginPage loginPage = new LoginPage();

        Socket clientSocket = new Socket("localhost" , 3334);
        BufferedReader inFromUser  =new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

            System.out.println("Hello enter your ID : ");
            int id = Integer.parseInt(inFromUser.readLine());
            System.out.println(id);
            outputStream.writeInt(id);

           String response = inFromServer.readUTF();
           System.out.println("FROM SERVER: " + response);

           clientSocket.close();

        }
    }

