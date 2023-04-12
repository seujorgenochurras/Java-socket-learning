package org.jhey;

import org.jhey.domain.GreetClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class MainTest {
   @Test
   void sendMesage() throws IOException {
   GreetClient client = new GreetClient();
      client.connectTo("127.0.0.1", 6666);
      System.out.println(client.sendMessage("Hi"));

   }
}
