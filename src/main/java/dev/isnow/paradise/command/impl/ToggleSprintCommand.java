package dev.isnow.paradise.command.impl;

import dev.isnow.paradise.command.Command;
import dev.isnow.paradise.command.CommandInfo;
import dev.isnow.paradise.exception.CommandException;
import dev.isnow.paradise.helper.ChatHelper;

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
