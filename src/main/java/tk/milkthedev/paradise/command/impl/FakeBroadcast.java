package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import net.minecraft.util.ChatComponentText;

@CommandInfo(
        alias = "fakebroadcast",
        description = "Sends fake text to chat",
        usage = ".fb [text]",
        aliases = {".fb"}
)
public class FakeBroadcast extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        StringBuilder stringBuilder = new StringBuilder();
        for(String string : args) {
            if(stringBuilder.length() != 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(string.replaceAll("&", "§"));
        }
        mc.thePlayer.addChatMessage(new ChatComponentText(stringBuilder.toString()));
    }
}
