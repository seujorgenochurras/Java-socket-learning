package org.jhey.domain.server;


import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Logger;

public class ClientHandler extends Thread{
    static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    protected static final HashSet<ClientHandler> clientHandlers = new HashSet<>();
    private final Socket serverSocket;
    private ClientConsole clientConsole;

    private String clientName;
   public ClientHandler(Socket serverSocket) {
      this.serverSocket = serverSocket;

      //This need EMERGENT refactoring, the guy that I'm using as a guide is a total idiot
      //When user enters, the client side will ask for a name
      //Then it'll log into the server, so that's the first thing that he'll type
      this.clientName = clientConsole.getClientMessage();


      try {
         clientConsole = new ClientConsole(
                 new BufferedReader(new InputStreamReader(serverSocket.getInputStream())),
                 new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream())));
         clientConsole.sendMessage("Entering");
      }catch (IOException e){
      logger.severe("Couldn't start a client handler");
         interrupt();
      }

      if(clientHandlers.stream().anyMatch(client -> client.getName().equals(this.getName()))){
          this.clientName = clientName.concat("1");
      }
      clientHandlers.add(this);
   }

   public void close(){
      try {
         clientConsole.close();
         serverSocket.close();
         clientHandlers.remove(this);
      }catch (IOException e){
         logger.severe("Couldn't start a client handler");
         interrupt();
      }
   }
   @Override
   public void run() {
      String messageFromClient;
      while(serverSocket.isConnected()){
            messageFromClient = clientConsole.getClientMessage();
            if(messageFromClient.equals("null")) {
               close();
               break;
            }
            broadcastMessage(messageFromClient);
      }
   }

   private void broadcastMessage(String message){
      clientHandlers.forEach(clientHandler -> {
         clientConsole.sendMessage(message);
      });
   }
   public void leaveParty(){
      broadcastMessage("User " + this.getName() + " Hash left the party");
      close();
   }
}
