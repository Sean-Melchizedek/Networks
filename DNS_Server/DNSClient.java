import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class DNSClient {
	// the default port
	public static final int PORT = 6052;

	
	public static void main(String[] args) throws java.io.IOException {
		String host = args[0];
		
		DataOutputStream toServer = null;
		BufferedReader fromServer = null;
		Socket server = null;
		
		try {
			
			// create socket and connect to default port 
			server = new Socket(host, PORT);
			
			toServer = new DataOutputStream(server.getOutputStream());
			toServer.writeBytes(args[1] + "\r\n");
			toServer.flush();
			toServer.close();
			System.out.println("Sucessfully send the name");
			
			
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			String line;
			line = fromServer.readLine();
			fromServer.close();
			System.out.print(line);
			
			
			
		} 
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			// let's close streams and sockets
			// closing the input stream closes the socket as well
			if (fromServer!= null)
				fromServer.close();
			if (server != null)
				server.close();
		}
	}

}
