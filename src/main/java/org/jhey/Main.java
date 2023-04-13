package org.jhey;

import org.jhey.domain.EchoMultiClientServer;
import org.jhey.domain.EchoServer;
import org.jhey.domain.GreetServer;

import java.io.IOException;

public class Main {
   public static void main(String[] args) throws IOException {
      EchoMultiClientServer myServer = new EchoMultiClientServer();
      myServer.start(6666);
   }
}