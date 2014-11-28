package client;

import java.util.LinkedList;

public class ProtocoleClient extends Protocol {
	
	@Override
	public String message(String type, LinkedList<String> parametres){
		String message;
		message = ""+type;
		for(String param : parametres){
				message = message + " ";
			message = message+param;
		}
		
		return message;
	}
	
}
