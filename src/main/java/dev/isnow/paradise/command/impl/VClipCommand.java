package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;

@CommandInfo(
        alias = "vclip",
        description = "Teleports you few blocks up or down",
        usage = ".vclip [AMOUNT]",
        aliases = {"vc"}
)
public class VClipCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        if(args.length == 0) {
            ChatHelper.printMessage("Usage:" + this.getUsage());
            return;
        }
        mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + Integer.parseInt(args[0]), mc.thePlayer.posZ);
        ChatHelper.printMessage("Clipped!");
    }
}
