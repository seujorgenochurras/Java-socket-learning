package org.jhey;

import org.jhey.domain.GreetClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class MainTest {
   static GreetClient client;
   static GreetClient client1;
   static GreetClient client2;
   static GreetClient client3;

   @BeforeEach
   void init() throws IOException {
      client = new GreetClient();
      client.connectTo("127.0.0.1", 6666);

      client1 = new GreetClient();
      client1.connectTo("127.0.0.1", 6666);

      client2 = new GreetClient();
      client2.connectTo("127.0.0.1", 6666);

      client3 = new GreetClient();
      client3.connectTo("127.0.0.1", 6666);
   }
   @Test
   void sendMesage() throws IOException {
      System.out.println(client.sendMessage("hi"));
      System.out.println(client.sendMessage("hedd"));
      System.out.println(client.sendMessage("heas"));
      System.out.println(client.sendMessage("hgd"));
      System.out.println(client.sendMessage("."));
      System.out.println(client.sendMessage("close"));

      System.out.println(client1.sendMessage("hegy"));
      System.out.println(client1.sendMessage("heyjgyjgyjdd"));
      System.out.println(client1.sendMessage("hjgyjgyjgeas"));
      System.out.println(client1.sendMessage("hgyjgyjgyjgd"));
      System.out.println(client1.sendMessage("awdawdaw."));
      System.out.println(client1.sendMessage("close"));

      System.out.println(client2.sendMessage("hi"));
      System.out.println(client2.sendMessage("heazxczxczxcdd"));
      System.out.println(client2.sendMessage("hawdawdeas"));
      System.out.println(client2.sendMessage("hzxczxvxcvxcgd"));
      System.out.println(client2.sendMessage("asd."));
      System.out.println(client2.sendMessage("close"));

      System.out.println(client3.sendMessage("hi"));
      System.out.println(client3.sendMessage("hedd"));
      System.out.println(client3.sendMessage("heas"));
      System.out.println(client3.sendMessage("hgd"));
      System.out.println(client3.sendMessage("."));
      System.out.println(client3.sendMessage("close"));
   }
}
