package tk.milkthedev.paradise.command.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;


@CommandInfo(
        alias = "connect",
        description = "Initiate a connection to the specified server",
        usage = ".connect <ip> <port>",
        aliases = {".con"}
)
public class ConnectCommand extends Command
{
    @Override
    public void execute(String... args) throws CommandException
    {
        String hostName = args[0];
        int port = Integer.parseInt(args[1]);


        ServerData serverData = new ServerData("temp", hostName + ":" + port, false);
        Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(null, Minecraft.getMinecraft(), serverData));
    }
}
