package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;

@CommandInfo(
        alias = "srs",
        description = "Stops the rainbow spam.",
        usage = ".stoprainbowspam",
        aliases = {"stoprainbowspam"}
)
public class StopRainbowSpamCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        if(RainbowSpamCommand.thread != null) {
            RainbowSpamCommand.thread.stop();
            RainbowSpamCommand.thread = null;
            ChatHelper.printMessage("Stopped!");
        } else {
            ChatHelper.printMessage("Rainbow spam is not running!");
        }
    }
}
