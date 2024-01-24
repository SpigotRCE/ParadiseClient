package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;

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
