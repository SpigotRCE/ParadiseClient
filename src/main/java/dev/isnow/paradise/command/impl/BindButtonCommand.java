package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.Paradise;
import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@CommandInfo(
        alias = "bindbutton",
        description = "Binds a command to a keybind",
        usage = ".bindbutton [key] [command]",
        aliases = {"bb", "bindkey"}
)
public class BindButtonCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if(args.length < 2) {
            throw new CommandException(getUsage());
        }

        final int keyCode = Keyboard.getKeyIndex(args[0].toUpperCase(Locale.ROOT));
        if(keyCode == 0) {
            ChatHelper.printMessage("Invalid key!");
            return;
        }
        final List<String> copiedList = new LinkedList<>(Arrays.asList(args));
        copiedList.remove(0);
        Paradise.INSTANCE.keyToolBinds.put(keyCode, String.join(" ", copiedList));

        ChatHelper.printMessage("Bound command to " + args[0] + "!");

    }
}
