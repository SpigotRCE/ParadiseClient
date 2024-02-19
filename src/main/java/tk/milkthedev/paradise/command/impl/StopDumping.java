package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;

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