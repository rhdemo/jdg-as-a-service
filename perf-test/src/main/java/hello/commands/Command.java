package hello.commands;

public interface Command {

   String getCommandName();

   StringBuilder invoke(StringBuilder stringBuilder);

   default void setArgument(String argument) {

   }

}
