package org.jhey;

import org.jhey.domain.GreetServer;

import java.io.IOException;

public class Main {
   public static void main(String[] args) throws IOException {
      double x = 2.1;
      int y = 3;
      System.out.println(y + x);
      GreetServer myServer = new GreetServer();
      myServer.start(6666);
   }
}