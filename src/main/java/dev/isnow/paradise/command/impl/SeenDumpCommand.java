package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.NetHelper;
import net.minecraft.network.play.client.C14PacketTabComplete;

@CommandInfo(
        alias = "seendump",
        description = "Dumps thru /seen",
        usage = ".seendump",
        aliases = {"sd"}
)
public class SeenDumpCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        mc.getNetHandler().seenDumpExecuted = true;
        NetHelper.sendPacket(new C14PacketTabComplete("/seen "));
    }
}
