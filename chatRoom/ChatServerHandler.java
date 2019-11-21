import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerHandler{
    // Message limit 512 characters
    // Username limit 15 characters
    private final int MASSAGE_BUFFER_SIZE = 512;
    private final int USERNAME_BUFFER_SIZE = 15;
    private byte[] messageBuffer = new byte[MASSAGE_BUFFER_SIZE];
    private byte[] usernameBuffer = new byte[USERNAME_BUFFER_SIZE];

    private HashMap username_socket_HashMap = new HashMap();

    public void process(Socket clientSocket){
        try{

        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{

        }
    }

    private String stringParser(Sting stringFromClient){
        
    }
    private String streamListener(Socket clientSocket){
        InputStream  streamFromClient = new BufferedInputStream(clientSocket.getInputStream());
        
        
        return requesString;
    }






}