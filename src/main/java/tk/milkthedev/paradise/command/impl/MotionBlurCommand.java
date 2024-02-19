package tk.milkthedev.paradise.command.impl;

import tk.milkthedev.paradise.command.Command;
import tk.milkthedev.paradise.command.CommandInfo;
import tk.milkthedev.paradise.exception.CommandException;
import tk.milkthedev.paradise.helper.ChatHelper;
import tk.milkthedev.paradise.helper.MotionBlur;
import tk.milkthedev.paradise.helper.SaveLoad;

@CommandInfo(
        alias = "motionblur",
        description = "Enables/Disables Motionblur and changes it's value.",
        usage = ".motionblur [OFF/ON/VALUE]",
        aliases = {"mb"}
)
public class MotionBlurCommand extends Command {
    @Override
    public void execute(String... args) throws CommandException {
        if(args.length < 1) {
            ChatHelper.printMessage("Usage: " + this.getUsage());
            return;
        }

        try {
            float value = Float.parseFloat(args[0]);
            value = value == 1 ? 0.99F : value;
            MotionBlur.setBlurFactor(value);
            SaveLoad saveLoad1 = new SaveLoad("motionValue.txt");
            saveLoad1.item = String.valueOf(value);
            saveLoad1.save();
            ChatHelper.printMessage("Value set!");
        } catch (NumberFormatException e) {
            MotionBlur.toggled = Boolean.parseBoolean(args[0]) || args[0].equalsIgnoreCase("on");
            ChatHelper.printMessage("Toggled: " + MotionBlur.toggled);
        }
    }
}
