package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.SaveLoad;

@CommandInfo(
        alias = "banall",
        description = "Bans all online players from a file",
        usage = ".banall [command]"
)
public class BanAllCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        if (args.length < 1) {
            return;
        }
        String command = String.join(" ", args);
        command = command.replaceAll("/", "");
        final SaveLoad players = new SaveLoad("players.txt");
        players.load();
        final String stringarray = (String) players.item;
        String[] resplit = stringarray.split( ", ");
        for(String player : resplit) {
            mc.thePlayer.sendChatMessage(command.replaceAll("%player%", player));
        }
    }

}
