package org.jhey.domain.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientConsole {
   private BufferedReader out;
   private BufferedWriter in;

   public String getClientMessage() {
      try {
       return out.readLine();
      }catch (IOException e){
         close();
         return null;
      }
   }

   public void close() {
      try {
         out.close();
         in.close();
      }catch (IOException e){
         ClientHandler.logger.severe("Couldn't close");
         Thread.currentThread().interrupt();
      }
   }

   public ClientConsole(BufferedReader out, BufferedWriter in) {
      this.out = out;
      this.in = in;
   }
   public void sendMessage(String message){
      try {
      this.in.write(message);
      this.in.newLine();
      this.in.flush();

      }catch (IOException e){
         ClientHandler.logger.severe("Couldn't send a message");
         Thread.currentThread().interrupt();
      }
   }
}
