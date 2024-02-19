package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.Paradise;
import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;

@CommandInfo(
        alias = "bind",
        description = "Binds a command to a tool",
        usage = ".bind [command]",
        aliases = {"b"}
)
public class BindCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        if(args.length == 0){
            try {
                Paradise.INSTANCE.toolBinds.remove(mc.thePlayer.inventory.getCurrentItem().getItem());
            }
            catch (Exception e){
                return;
            }
            ChatHelper.printMessage("Removed command from " + mc.thePlayer.inventory.getCurrentItem().getItem().getUnlocalizedName());
            return;
        }
        if(mc.thePlayer.inventory.getCurrentItem() != null) {
            Paradise.INSTANCE.toolBinds.put(mc.thePlayer.inventory.getCurrentItem().getItem(), String.join(" ", args));
            ChatHelper.printMessage("Bound command to " + mc.thePlayer.inventory.getCurrentItem().getItem().getUnlocalizedName());
        }
    }
}
