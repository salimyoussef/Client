import client.Client;
import exceptions.ConnectionNotOpenedException;


public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		client.open();
		
		
		try {
			String rep = client.send("bonjour !");
		} catch (ConnectionNotOpenedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		client.close();
		
		
	}
}
