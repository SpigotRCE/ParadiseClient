package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;
import dev.isnow.paradise.helper.NetHelper;
import net.minecraft.network.play.client.C14PacketTabComplete;

@CommandInfo(
        alias = "bungeedump",
        description = "Dumps thru /ip",
        usage = ".bungeedump",
        aliases = {"bd"}
)
public class BungeeDumpCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if (!mc.getNetHandler().bungeeDumpExecuted || !mc.getNetHandler().seenDumpExecuted){
            mc.getNetHandler().bungeeDumpExecuted = true;
            NetHelper.sendPacket(new C14PacketTabComplete("/ip "));
        }
        else {
            ChatHelper.printMessage("Either of any two dumping method is running. Please wait till it finishes.");
        }
    }
}
