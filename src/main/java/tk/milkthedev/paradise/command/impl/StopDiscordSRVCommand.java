package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;

@CommandInfo(
        alias = "sdsrvs",
        description = "Stops the discordsrv spam.",
        usage = ".stopdiscordsrvspam",
        aliases = {"stopdiscordsrvspam", "sdsrvs", "stopdiscordspam", "sdcs"}
)
public class StopDiscordSRVCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if(DiscordSRVCommand.thread != null) {
            DiscordSRVCommand.thread.stop();
            DiscordSRVCommand.thread = null;
            ChatHelper.printMessage("Stopped!");
        } else {
            ChatHelper.printMessage("DiscordSRV spam is not running!");
        }
    }
}
