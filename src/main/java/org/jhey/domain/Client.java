package org.jhey.domain;

import org.jhey.domain.server.ClientConsole;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {
   private final ClientConsole clientConsole;
   private final Socket serverSocket;
   private final Logger logger = Logger.getLogger(Client.class.getName());
   private final String userName;

   public Client(Socket serverSocket, String userName) throws IOException {
      clientConsole = new ClientConsole(
              new BufferedReader(new InputStreamReader(serverSocket.getInputStream())),
              new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream())));
      this.serverSocket = serverSocket;
      this.userName = userName;
   }

   public void sendMessage(){
      Scanner scanner = new Scanner(System.in);
      while(serverSocket.isConnected()){
         String messageToSend = scanner.nextLine();
         clientConsole.sendMessage( userName + " " + messageToSend);
      }
   }


   public void listenForMessages(){

      new Thread( () -> {
         String messageFromParty;
         while (serverSocket.isConnected()){
            messageFromParty = clientConsole.getClientMessage();
            if(messageFromParty.equals("null")){
               close();
            }
            System.out.println(messageFromParty);
         }
      }).start();
   }

   public void close(){
      try {
         clientConsole.close();
         serverSocket.close();
      }catch (IOException e){
         logger.severe("Couldn't start a client handler");
      }
   }

   public static void main(String[] args) throws IOException, InterruptedException {
//      Scanner scanner = new Scanner(System.in);
//      System.out.println("What's your name?");
//      String clientName = scanner.nextLine();
//      int port = getUserPort(scanner);

      Client client = new Client(new Socket("127.0.0.1", 6969), "clientName");
      client.listenForMessages();
      client.sendMessage();

      Thread.sleep(111);
      Thread.currentThread().interrupt();
   }
   private static int getUserPort(Scanner scanner){
      System.out.println("What's the port that you're trying to enter?");

      try{
         return scanner.nextInt();
      }catch (InputMismatchException e){
         System.out.println("You're an idiot");
         return getUserPort(scanner);
      }
   }
}
