package client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import exceptions.ConnectionNotOpenedException;

public class Client {
	private Socket socket;
	private String serverName;
	private int serverPort;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private String serverResponse;
	private boolean connectionOpened;
	
	public Client(){
		serverName = "localhost";
		serverPort = 5000;
		connectionOpened = false;
	}
	
	public void setServer(String host, int port){
		serverName = host;
		serverPort = port;
	}
	
	public void open(){
		try {
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected with "+serverName+" in port:"+serverPort);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.flush();
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectionOpened = true;
	}
	
	public String send(String message) throws ConnectionNotOpenedException{
		if(!connectionOpened){
			throw new ConnectionNotOpenedException();
		}
		
		sendMessage(message);
		
		try{
			serverResponse = (String) objectInputStream.readObject();
		} catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverResponse;
	}
	
	public void close(){
		sendMessage("bye");
		connectionOpened = false;
	}
	
	private void sendMessage(String message){
		try{
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
			//System.out.println("client: "+message);

		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}
