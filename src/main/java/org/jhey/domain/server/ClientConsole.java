package org.jhey.domain.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientConsole {
   private BufferedReader output;
   private BufferedWriter input;

   public BufferedReader getOutput() {
      return output;
   }

   public BufferedWriter getInput() {
      return input;
   }

   public String getClientMessage() throws IOException {
       return output.readLine();
   }

   public void close() {
      try {
         output.close();
         input.close();
      }catch (IOException e){
         ClientHandler.logger.severe("Couldn't close");
         Thread.currentThread().interrupt();
      }
   }

   public ClientConsole(BufferedReader output, BufferedWriter input) {
      this.output = output;
      this.input = input;
   }
   public void sendMessage(String message){
      try {
      this.input.write(message);
      this.input.newLine();
      this.input.flush();

      }catch (IOException e){
         ClientHandler.logger.severe("Couldn't send a message");
         Thread.currentThread().interrupt();
      }
   }
}
