package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.NetHelper;
import net.minecraft.network.play.client.C14PacketTabComplete;

@CommandInfo(
        alias = "fetch",
        description = "Fetch all online players to a file",
        usage = ".fetch [command]"
)
public class FetchAllCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        String command;
        if(args.length < 0) {
            command = "msg";
        } else {
            command = args[0];
        }
        command = command.replaceAll("/", "");
        mc.getNetHandler().fetchExecuted = true;
        NetHelper.sendPacket(new C14PacketTabComplete("/" + command));
    }
}
