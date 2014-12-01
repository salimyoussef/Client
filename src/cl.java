
import java.io.*;
import java.net.*;


/**
 * La classe client se connecte au serveur par le port 8500 
 * et envoie en ligne de commande les requêtes en string.
 * Il affiche directement tous les messages qu'il reçoit.
 * 
 * @author Castede Olivier - Richter Romain
 *
 */
public class cl extends Thread {
    /**
     * Contient l'ip du serveur
     */
    private String host;
    
    /**
     * Contient le port auquel le client va se connecter
     */
    private int port;
    /**
     * Buffer d'entrée de caractère
     */
    private BufferedReader cin;
    /**
     * Buffer de sortie de caractère
     */
    private PrintWriter cout;
    /**
     * Socket
     */
    private Socket s;

   /**
    * Constructeur avec un ip et port par défaut
    */
   public cl () {
      host = "10.212.126.167"; 
      port = 8500;
   }
   
   /**
    * Constructeur avec l'ip et le port en paramètre
    * @param host Contient l'ip du serveur
    * @param port Contient le port
    */
   public cl (String host, int port) {
     this.host= host; 
     this.port= port;
   }
   
   /**
    * Permet la connexion au serveur
    * @return true si initStream s'est bien passé
    */
   private boolean connect() {
      s = null;
      try {
          s = new Socket(host, port);
      }
      catch (Exception e) {}
 
      return initStream();
   }
   
   /**
    * Initialise les buffer d'entrée et de sortie
    * @return true si les initialisations se sont bien passées
    */
   private boolean initStream() {
       InputStream streamIn = null;
       InputStreamReader bufferIn;
       OutputStream streamOut = null;
       OutputStreamWriter bufferOut;
   
       if (s == null)
           return false;
   
      try{streamIn = s.getInputStream();}
      catch (Exception e) {return false;}
      
      bufferIn = new InputStreamReader(streamIn);

      cin = new BufferedReader(bufferIn);
      
      try {
          streamOut = s.getOutputStream();
      }
      catch(Exception e){return false;}
      
      bufferOut = new OutputStreamWriter(streamOut);
      cout = new PrintWriter(new BufferedWriter(bufferOut), true);
      
      return true;
   }
   
   /**
    * Envoi au serveur une String
    * @param msg la String qui va être envoyé au serveur
    * @return true si l'envoi s'est bien passé
    */
   public boolean send (String msg) {
      if (cout == null)
          return false;

      cout.println(msg);
      return true;
   }
   
   /**
    * Reçoit du serveur une String
    * @return la String reçue
    */
   public String receive() {
   String msg="?";
      try{
           while (true) {
              msg= cin.readLine();
              if (!msg.equals("?")) break;
              Thread.currentThread().sleep(50);
           }
      }
      catch (Exception e){}
      return msg;
   }
   
   /**
    * Appelée à la fermeture du client.
    * Ferme les buffers d'entrée et de sortie
    */
   public void close () {
    try {
        cin.close();
        cout.close();
        s.close();
    }
    catch(Exception e){}
   }
   
   public static void main(String args[]) {
       cl client = new cl();
       boolean connected = client.connect();
       
       if (!connected){
           System.out.println("Erreur de connexion au serveur");
           System.exit(-1);
       }
       
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String cmd = "";
        String ans = "";
        String[] lines;
       
       while (true) {
          ans = client.receive();
          lines = ans.split(";");
          
          for(String line : lines)
              System.out.println(line);
          
          System.out.print("> ");
          try {
              cmd = console.readLine();
          } catch (IOException e) {}
            
          client.send(cmd);
          
          if(cmd.equals("exit") || cmd.equals("killserver"))
              break;
       }
   }
}