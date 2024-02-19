package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;
import tk.milkthedev.paradise.helper.NetHelper;
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
        if (!mc.getNetHandler().bungeeDumpExecuted || !mc.getNetHandler().seenDumpExecuted){
            mc.getNetHandler().seenDumpExecuted = true;
            NetHelper.sendPacket(new C14PacketTabComplete("/seen "));
        }
        else {
            ChatHelper.printMessage("Either of any two dumping method is running. Please wait till it finishes.");
        }
    }
}
