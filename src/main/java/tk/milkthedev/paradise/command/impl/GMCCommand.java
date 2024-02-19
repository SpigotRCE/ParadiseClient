package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;

@CommandInfo(
        alias = "gamemodecreative",
        description = "Sends all possible gamemode to creative commands.",
        usage = ".gmc",
        aliases = {"gm", "gmc"}
)
public class GMCCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        mc.thePlayer.sendChatMessage("/minecraft:gamemode creative");
        mc.thePlayer.sendChatMessage("/cmi:gamemode creative");
        mc.thePlayer.sendChatMessage("/essentials:gamemode creative");
        mc.thePlayer.sendChatMessage("/gmc");
        mc.thePlayer.sendChatMessage("/gm 1");
    }

}
