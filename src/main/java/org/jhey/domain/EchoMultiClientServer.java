package org.jhey.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoMultiClientServer extends AbstractServer{

   @Override
   public void start(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      while (true){
         new MultiClientHandler(serverSocket.accept()).start();
   }
   }
   private class MultiClientHandler extends Thread{
      private final Socket clientSocket;
      public MultiClientHandler(Socket clientSocket) {
         this.clientSocket = clientSocket;
      }

      @Override
      public void run() {
         try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         } catch (IOException e) {
            throw new RuntimeException(e);
         }

         String clientMessage = null;
         for(;;){
            try {
               clientMessage = in.readLine();
               if(clientMessage == null) break;
            } catch (IOException e) {

            }
            if(clientMessage.equals("close")){
               out.println("Closing...");
               break;
            }
            out.println(clientMessage);
         }
         try {
            in.close();
            out.close();
            clientSocket.close();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
   }
}
