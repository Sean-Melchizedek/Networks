import java.net.*;
import java.io.*;

public class ChatServerConnection implements Runnable
{
	private Socket client;
	private static ChatServerHandler chatHandler = new ChatServerHandler();
	public ChatServerConnection(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

    /**
     * This method runs in a separate thread.
     */
	public void run() {
		try {
			chatHandler.process(clientSocket);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}