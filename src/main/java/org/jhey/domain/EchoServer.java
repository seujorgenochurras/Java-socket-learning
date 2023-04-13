package org.jhey.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class EchoServer extends AbstractServer{

   @Override
   public void start(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      clientSocket = serverSocket.accept();

      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      String clientMessage;
      while((clientMessage = in.readLine()) != null){
         if(clientMessage.equals("close")){
            out.println("Closing...");
            stop();
            break;
         }
     out.println(clientMessage);
      }
   }
}
