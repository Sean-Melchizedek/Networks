/**
 * This is the separate thread that services each
 * incoming echo client request.
 *
 * @author Greg Gagne 
 */

import java.net.*;

public class ProxyConnection implements Runnable
{
	private Socket client;
	private static ProxyHandler p_handler = new ProxyHandler();
	
	public ProxyConnection(Socket client) {
		this.client = client;
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {
			p_handler.process(client);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}

	}
}

