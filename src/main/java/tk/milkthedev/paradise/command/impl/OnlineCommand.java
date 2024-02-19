package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;
import tk.milkthedev.paradise.helper.NetHelper;
import tk.milkthedev.paradise.helper.PlayerHelper;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.server.MinecraftServer;

@CommandInfo(
    alias = "online",
    description = "Checks for the amount of online players.",
    usage = ".online [method]",
    aliases = {"players", "realplayers"}
)
public class OnlineCommand extends Command {

  @Override
  public void execute(String... args) throws CommandException {
    Type type;
    if(args.length != 1) {
      ChatHelper.printMessage("Available modes: player_data, tab_complete");
      return;
    }
    try {
       type = Type.valueOf(args[0].toUpperCase());
    } catch (IllegalArgumentException e) {
      ChatHelper.printMessage("Available modes: player_data, tab_complete");
      return;
    }
    int onlinePlayers = 0;

    switch (type) {
      case PLAYER_DATA:
        onlinePlayers = PlayerHelper.getOnlinePlayer().size();
        break;
      case TAB_COMPLETE:
        if(mc.isIntegratedServerRunning()) {
          onlinePlayers += (long) MinecraftServer.getServer().getTabCompletions(mc.thePlayer.getCommandSenderEntity(), " ", mc.objectMouseOver.getBlockPos()).size();
        }
        else {
          mc.getNetHandler().onlineExecuted = true;
          NetHelper.sendPacket(new C14PacketTabComplete(" "));

          return;
        }
    }

    ChatHelper.printMessage("Online players: &7" + onlinePlayers);
  }

  enum Type {
    TAB_COMPLETE, PLAYER_DATA
  }
}
