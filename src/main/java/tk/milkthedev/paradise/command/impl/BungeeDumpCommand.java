package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;
import tk.milkthedev.paradise.helper.NetHelper;
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
