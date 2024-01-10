package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;
import dev.isnow.paradise.helper.NetHelper;
import net.minecraft.network.play.client.C14PacketTabComplete;

@CommandInfo(
        alias = "pl",
        description = "Checks for the plugins available on the server.",
        usage = ".plugins",
        aliases = {"plugins"}
)
public class PluginsCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if(args.length != 1) {
            ChatHelper.printMessage("Available modes: bypass, tab_complete");
            return;
        }

        Type type;

        try {
            type = Type.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            ChatHelper.printMessage("Available modes: bypass, tab_complete");
            return;
        }

        switch (type) {
            case BYPASS:
                mc.getNetHandler().pluginsBypassExecuted = true;
                NetHelper.sendPacket(new C14PacketTabComplete("/bukkit:version "));
                NetHelper.sendPacket(new C14PacketTabComplete("/bukkit:ver "));
                NetHelper.sendPacket(new C14PacketTabComplete("/bukkit:about "));
                NetHelper.sendPacket(new C14PacketTabComplete("/bukkit:? "));
            case TAB_COMPLETE:
                mc.getNetHandler().pluginsTabExecuted = true;
                NetHelper.sendPacket(new C14PacketTabComplete("/"));
        }
    }

    enum Type {
        BYPASS, TAB_COMPLETE
    }
}
