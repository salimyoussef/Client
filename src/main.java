import java.util.Scanner;

import exceptions.ConnectionNotOpenedException;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
		String host = "10.212.126.167";
		int port = 8500;
		client.setServer(host, port);
		
		Scanner s = new Scanner(System.in);

		client.open();
		String req, res="";
		try {
			do {
				
				req = s.nextLine();
				if(req.length()> 0)	res = client.send(req);
				System.out.println(res);
				if (res.equals("bye"))
					client.close();

			} while (client.isConected());
		} catch (ConnectionNotOpenedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
