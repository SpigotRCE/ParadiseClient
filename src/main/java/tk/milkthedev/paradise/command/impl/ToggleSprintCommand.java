package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;

@CommandInfo(
        alias = "togglesprint",
        description = "Automatically sprints for you.",
        usage = ".togglesprint",
        aliases = {"ts"}
)
public class ToggleSprintCommand extends Command {

    public static boolean toggled;
    @Override
    public void execute(String... args) throws CommandException {
        toggled = !toggled;
        ChatHelper.printMessage("Toggled: " + toggled);
    }
}
