package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;
import dev.isnow.paradise.helper.NetHelper;
import net.minecraft.network.play.client.C14PacketTabComplete;

@CommandInfo(
        alias = "seendump",
        description = "Dumps thru /seen",
        usage = ".seendump",
        aliases = {"sd"}
)
public class StopDumping extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        if (!mc.getNetHandler().bungeeDumpExecuted || !mc.getNetHandler().seenDumpExecuted){
            mc.getNetHandler().seenDumpExecuted = false;
            mc.getNetHandler().bungeeDumpExecuted = false;
            ChatHelper.printMessage("Stopped all dumping method.");

        }
        else {
            ChatHelper.printMessage("No dumping method is running.");
        }
    }
}