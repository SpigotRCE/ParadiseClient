package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;
import dev.isnow.paradise.Paradise;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

@CommandInfo(
        alias = "copybroadcast",
        description = "Copies broadcast of Milk.",
        usage = ".copybroadcast",
        aliases = {"cb"}
)
public class CopyBroadcastCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        StringSelection stringSelection = new StringSelection(Paradise.INSTANCE.broadcastOfMilk);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        ChatHelper.printMessage("Milk's broadcast has been copied to your clipboard.");
    }
}
