package org.jhey;


import org.jhey.domain.server.Server;

public class Main {
   public static void main(String[] args) {
      Server server = new Server();
      server.startServer(6969);
   }
}