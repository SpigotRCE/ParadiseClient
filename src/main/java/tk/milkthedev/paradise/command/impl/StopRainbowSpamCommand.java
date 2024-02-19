package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;

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
