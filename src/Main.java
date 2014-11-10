import java.util.Scanner;

import client.Client;
import exceptions.ConnectionNotOpenedException;


public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		Scanner s = new Scanner(System.in);
		String req;
		
		
		
		try {
			while(true){
				req = s.next();
				if(req.equals("ajouter")){
					String nom =s.next();
					String surnom = s.next();
					req = req + " " + nom + " " + surnom;
					client.setServer("10.212.102.245", 5000);
					client.open();
					String resp = client.send(req);
					System.out.println(resp);
					client.close();
				}
			}
		} catch (ConnectionNotOpenedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
