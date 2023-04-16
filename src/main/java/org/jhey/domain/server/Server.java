package org.jhey.domain.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server{
   private ServerSocket serverSocket;
   private final Logger logger = Logger.getLogger(Server.class.getName());

   public void startServer(int port) {
     generateServer(port);
      while(!serverSocket.isClosed()){
         try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("A client has joined");
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.start();
            clientHandler.broadcastMessage("A wild " + clientHandler.getClientName() + " has entered the party");
         }catch (IOException e){
            logger.severe("Something went terrible wrong when adding a client");
         }
      }
   }

   /**
    * Tries to generate a new server
    * and if successful will set the serverSocket variable to the new server instance
    * */
   private void generateServer(int port){
      try {
         serverSocket = new ServerSocket(port);
      }catch (IOException e){
         logger.severe("Couldn't start the server");
         Thread.currentThread().interrupt();
      }
   }

   public void close(){
      try {
      serverSocket.close();
      }catch (IOException e){
         logger.severe("Couldn't close the server");
         Thread.currentThread().interrupt();
      }

   }


}
