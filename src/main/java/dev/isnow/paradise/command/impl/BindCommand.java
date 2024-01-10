package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.Paradise;
import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;

import javax.xml.bind.SchemaOutputResolver;

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
