package client;

import java.util.LinkedList;

public class Protocol {
	
	
	public String message(String type, LinkedList<String> parametres){
		String message;
		message = "{\"type\" : \""+type+"\", \"parametres\" : [";
		int i = 0;
		for(String param : parametres){
			if(i != 0) 
				message = message + ",";
			message = message + "\""+param+"\"";
			i++;
		}
		message = message + "]}";
		return message;
	}
}
