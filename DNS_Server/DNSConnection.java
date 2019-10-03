import java.net.Socket;

public class DNSConnection implements Runnable{

	private Socket	client;
	private static DNSLookUp dnsLookup = new DNSLookUp();
	
	public DNSConnection(Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
			dnsLookup.process(client);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
		
	}

}
