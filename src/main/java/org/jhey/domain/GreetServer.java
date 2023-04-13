package org.jhey.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer extends AbstractServer{

   @Override
   public void start(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      clientSocket = serverSocket.accept();

      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      String whatClientSays = in.readLine();
      if(whatClientSays.equals("Hi")){
         out.println("Hello");
      } else{
         out.println("BITTTTCH");
      }
   }
}
