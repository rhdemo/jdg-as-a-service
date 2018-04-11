package hello.commands;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import hello.commands.Command;
import hello.commands.impl.NotFoundCommand;

@Component
public class CommandParser {

   @Value("${cmds}")
   String fallbackCommands;

   @Autowired
   Set<Command> availableCommands;

   @Autowired
   NotFoundCommand fallBackCommand;

   private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

   public List<Command> obtainCommands(String commandString) {
      if (StringUtils.isEmpty(commandString)) {
         commandString = fallbackCommands;
      }

      List<Command> commands = new ArrayList<>();

      logger.debug("Parsing: " + commandString);

      List<String> individualCommands = splitCommands(commandString);

      individualCommands.forEach(command -> {

         String commandName = getCommand(command);
         String arguments = getArgument(command);

         Command commandToBeExecuted = availableCommands.stream()
               .filter(cmd -> cmd.getCommandName().equals(commandName))
               .findAny().orElse(fallBackCommand);

         commandToBeExecuted.setArgument(arguments);
         commands.add(commandToBeExecuted);
      });

      logger.debug("Obtained commands: " + commands);

      return commands;
   }

   List<String> splitCommands(String input) {
      return Arrays.asList(input.split(","));
   }

   String getCommand(String input) {
      if (input.contains("(")) {
         return input.substring(0, input.indexOf("("));
      }
      return input;
   }

   String getArgument(String argument) {
      if (argument.contains("(")) {
         return argument.substring(argument.indexOf("(") + 1, argument.indexOf(")"));
      }
      return null;
   }

}
