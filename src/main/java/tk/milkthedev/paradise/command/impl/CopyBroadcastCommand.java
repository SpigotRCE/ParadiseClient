package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;
import tk.milkthedev.paradise.Paradise;

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
