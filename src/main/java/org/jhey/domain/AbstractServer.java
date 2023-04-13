package org.jhey.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractServer {
   public ServerSocket serverSocket;
   public Socket clientSocket;
   public PrintWriter out;
   public BufferedReader in;


   public void stop() throws IOException {
      in.close();
      out.close();
      clientSocket.close();
      serverSocket.close();
   }

   public ServerSocket getServerSocket() {
      return serverSocket;
   }

   public Socket getClientSocket() {
      return clientSocket;
   }

   public PrintWriter getOut() {
      return out;
   }

   public BufferedReader getIn() {
      return in;
   }

   public abstract void start(int port) throws IOException;
}
