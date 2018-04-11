package hello.commands.impl;

import java.time.Instant;

import org.springframework.stereotype.Component;

import hello.commands.Command;

@Component
public class WaitCommand implements Command {

   int waitMs;

   @Override
   public String getCommandName() {
      return "wait";
   }

   @Override
   public StringBuilder invoke(StringBuilder stringBuilder) {
      stringBuilder.append("[" + Instant.now() + "] Wait start");
      stringBuilder.append("\n");

      try {
         Thread.sleep(waitMs);
      } catch (InterruptedException e) {
         stringBuilder.append("[" + Instant.now() + "] Exception in wait: " + e);
      }

      stringBuilder.append("[" + Instant.now() + "] Wait end, total wait [ms]: " + waitMs);
      stringBuilder.append("\n");
      return stringBuilder;
   }

   @Override
   public void setArgument(String argument) {
      this.waitMs = Integer.valueOf(argument);
   }
}
