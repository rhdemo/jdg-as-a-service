package hello.commands.impl;

import org.springframework.stereotype.Component;

import hello.commands.Command;

@Component
public class NotFoundCommand implements Command {

   @Override
   public String getCommandName() {
      return "CommandNotFound";
   }

   @Override
   public StringBuilder invoke(StringBuilder stringBuilder) {
      return stringBuilder;
   }
}
