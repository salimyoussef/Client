import java.util.Scanner;

import client.Client;
import exceptions.ConnectionNotOpenedException;


public class Main {
	
	public static void main(String[] args) {
		Client client = new Client();		

		Scanner in = new Scanner(System.in);
		
		
		client.open();
		
		String req;
		String res;
		
		try {

			res  = client.send("bonjour !");
			
			while (true) {
				
				// Make the req
				req = in.next();
				if (req.equals("ajouter")){
					String nom = in.next();
					String surnom = in.next();
					req = req + " " +  nom + " " + surnom;
					res  = client.send(req);
					System.out.println(res);
				}				
			}
			
		} catch (ConnectionNotOpenedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
