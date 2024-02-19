package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;

import java.util.Objects;

@CommandInfo(
        alias = "screenshot",
        description = "Hides the server ip and makes the screenshot automatically.",
        usage = ".screenshot [on/off]",
        aliases = {"ss"}
)
public class ScreenshotCommand extends Command {

    public static boolean hidden;
    public static boolean takess;

    @Override
    public void execute(String... args) throws CommandException {
        if(args.length == 0) {
            hidden = !hidden;
        } else if(Objects.equals(args[0], "off")){
            hidden = false;
        } else if(Objects.equals(args[0], "on")){
            hidden = true;
        }
        else {
            ChatHelper.printMessage("Please enter only [on/off]");
            return;
        }
        if (hidden){
            ChatHelper.printMessage("IP Hidden!");
        }
        else {
            ChatHelper.printMessage("IP Shown!");
        }
    }
}
