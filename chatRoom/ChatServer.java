import java.net.*;
import java.io.*;
import java.util.concurrent.*;


public class ChatServer {
    // base on our chat room protocol
    public static final int PORT = 1337;
    // construct a thread pool for concurrency
    private static final Executor executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // establish the socket
            serverSocket = new ServerSocket(PORT);

            while (true){
                // now listen for connections and service the connection in a separate thread.
                Runnable chateConnectRunnable = new ChatConnection(serverSocket.accept());
                executor.execute(chateConnectRunnable);
            }
        }
        catch(IOException ioe){
            System.err.println(ioe);
            ioe.printStackTrace();
        }
        finally{
            if (serverSocket != null){
                try {
                    serverSocket.close();
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }

            }


        }

    }





}