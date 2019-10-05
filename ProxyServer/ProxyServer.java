
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

/**
 * This server will operate as follows:
 * When a connection is made, is will service the client in a separate thread.
 * Once the server reads the IP address from the client, it looks up its corresponding IP name, and writes it back to the client. The IP name will be written as a string, and terminated by a newline \r\n.
 * The server will then close the socket connection.
 * If the server cannot resolve the IP name to an address, it will write the message Unknown Host followed by a newline \r\n.
 * 
 */

/**
 * @author Haiming
 *
 */
public class ProxyServer {
	//listen to port 8080
	public static final int PORT = 8080;
	
	// construct a thread pool for concurrency	
	private static final Executor exec = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		ServerSocket sock = null;
		
		try {
			// establish the socket
			sock = new ServerSocket(PORT);
			
			while (true) {
				/**
				 * now listen for connections
				 * and service the connection in a separate thread.
				 */
				Runnable task = new ProxyConnection(sock.accept());
				exec.execute(task);
			}
		}
		catch (IOException ioe) { System.err.println(ioe); }
		finally {
			if (sock != null)
				try {
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

}