/**
 * A simple program demonstrating a proxy server that services client requests for web pages originating from origin servers
 * This is a detailed handler for the ProxyServer
 *
 * @author Haiming
 */

import java.io.*;
import java.net.*;

public class ProxyHandler
{
    static final int SIZE = 5000000;
	public void process(Socket client) throws java.io.IOException {

        BufferedReader in_FromClient = null;
        BufferedReader in_FromOri_server = null;
        String host_address = null;
        String resouce_address = null;

		try {
            // 1.Read request from the clients and parse it into 3 different pieces
            in_FromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String requestfromClient = in_FromClient.readLine();
            in_FromClient.readLine();
            
            String[] fromClientArr = requestfromClient.split(" ");
            
            int host_start = 1;
            int host_end = fromClientArr[1].indexOf("/",host_start+1);

            System.out.println(requestfromClient);
            
            // the resource address stop right before the "space".
            // if the source address is empty then we return the default source address: "/"
            if (host_end == -1) {
                host_end = fromClientArr[1].length();
                host_address = fromClientArr[1].substring(host_start, host_end);
                resouce_address = "/";
            }
            else{
                host_address = fromClientArr[1].substring(host_start, host_end);
                int resource_start = fromClientArr[1].indexOf("/", host_start+1);
                int resource_end =  fromClientArr[1].length();
                resouce_address = fromClientArr[1].substring(resource_start, resource_end);
            }
            
            // 2.Open a socket connection to the original host
            InetAddress addr = InetAddress.getByName(host_address);
            Socket ori_server = new Socket(addr, 80);
            PrintWriter toserver_PWriter = new PrintWriter(ori_server.getOutputStream(), true);

            // 3.Make a HTTP 1.1 request to the origin host for the resources
            System.out.println("sending " + "GET " + resouce_address + " HTTP/1.1");
            System.out.println("sending " + "Host: " + host_address + "");
            toserver_PWriter.println("GET " + resouce_address + " HTTP/1.1");
            toserver_PWriter.println("Host: " + host_address);
            toserver_PWriter.println("Connection: Close");
            toserver_PWriter.println();
            toserver_PWriter.flush();
                   
            // 4.Read the response from the origin host 
            in_FromOri_server = new BufferedReader(new InputStreamReader(ori_server.getInputStream()));
            boolean loop = true;
            StringBuilder sb = new StringBuilder(SIZE);
            while (loop) {
                if (in_FromOri_server.ready()) {
                    int j = 0;
                    while (j != -1) {
                        j = in_FromOri_server.read();
                        sb.append((char) j);
                    }
                loop = false;
                }
            }
            String retunedFromServer = sb.toString();
            System.out.println("recieved data from the original sever");

            // 5.Write the response back to the requesting client.
            PrintWriter toClient_PWriter = new PrintWriter(client.getOutputStream(), true);
            toClient_PWriter.println(retunedFromServer);
            toClient_PWriter.println();

            System.out.println("sent data back to the client");
            toserver_PWriter.close();
            toClient_PWriter.close();
            ori_server.close();
            
        }

		catch (UnknownError uhe){
            System.out.println(uhe);
        }
        catch (NullPointerException nul){
            System.out.println("requestfromClient doesn't exsist");
        }

		finally {
			// closing the input/output streams 
			if (in_FromClient!= null)
                in_FromClient.close();
            if (in_FromOri_server!= null)
                in_FromOri_server.close();
		}
		
	}
	
}