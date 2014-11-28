import java.util.LinkedList;
import java.util.Scanner;

import client.Client;
import client.ProtocolClient;
import exceptions.ConnectionNotOpenedException;

public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		Scanner s = new Scanner(System.in);
		String req;
		String resp = "e";
		ProtocolClient pc = new ProtocolClient();
		LinkedList<String> parametres = new LinkedList<>();

		do {
			try {
				req = s.next();
				if (req.equals("ajouter")) {
					String nom = s.next();
					String surnom = s.next();
					parametres.add(nom);
					parametres.add(surnom);
					req = pc.message(req, parametres);
					// client.setServer("10.212.102.245", 5000);
					client.open();
					resp = client.send(req);
					System.out.println(resp);
					client.close();
				}
				if (req.equals("enregistrer")) {
					String nom = s.next();
					String surnom = s.next();
					parametres.add(nom);
					parametres.add(surnom);
					req = pc.message(req, parametres);
					// client.setServer("10.212.102.245", 5000);
					client.open();
					resp = client.send(req);
					System.out.println(resp);
					client.close();
				}
			} catch (ConnectionNotOpenedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!resp.equals("bye"));

	}
}
