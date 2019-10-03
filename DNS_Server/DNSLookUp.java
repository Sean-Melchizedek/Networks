/**
 * A simple program demonstrating DNS lookup
 *
 *
 * @author Haiming
 */

import java.io.*;
import java.net.*;

public class DNSLookUp
{
	public static final int BUFFER_SIZE = 256;
	
	public void process(Socket client) throws java.io.IOException {


		BufferedReader fromClient = null;
		InetAddress hostAddress;
		DataOutputStream toClient = null;
	
		try {
			
				fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));		
				String line = fromClient.readLine();
				fromClient.close();
				System.out.print("recieved IP name: " + line + "\n");
				
				hostAddress = InetAddress.getByName(line);
				System.out.println(hostAddress.getHostAddress());
				
				toClient = new DataOutputStream(client.getOutputStream());
				while (fromClient.readLine() != null) {
					toClient.writeBytes(line + "\r\n");
					toClient.flush();
					toClient.close();
					System.out.println("the address has been sent back to the client.");
				}
		}
		finally {
			

		}
		
		
		
		
	}
	
}
