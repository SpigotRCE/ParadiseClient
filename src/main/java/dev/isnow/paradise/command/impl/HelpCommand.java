package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.Paradise;
import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;

@CommandInfo(
    alias = "help"
)
public class HelpCommand extends Command {

  @Override
  public void execute(String... args) throws CommandException {
    if (args.length > 0) {
      ChatHelper.printMessage("\n", false);
      Command command = Paradise.INSTANCE.getCommandManager().getCommand(args[0])
          .orElseThrow(
              () -> new CommandException(String.format("Command \"%s\" not found.\n", args[0])));

      ChatHelper
          .printMessage(String.format("&5%s&f: &c%s\n", command.getAlias(), command.getUsage()));

      return;
    }

    ChatHelper.printMessage("\n", false);
    Paradise.INSTANCE.getCommandManager().getCommands().stream()
        .filter(command -> !(command instanceof HelpCommand))
        .forEach(command -> ChatHelper.printMessage(String.format("&5%s &f- &7%s", command.getAlias(), command.getDescription())));

    ChatHelper.printMessage("\n", false);
  }
}
