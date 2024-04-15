package tk.milkthedev.paradise.hook;

import tk.milkthedev.paradise.Paradise;
import tk.milkthedev.paradise.helper.ColorHelper;
import tk.milkthedev.paradise.helper.DrawHelper;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.io.IOException;

import static optifine.Reflector.Minecraft;

public class MainMenuHook extends GuiScreen {

    public float mouseXOffset = 0, mouseYOffset = 0;
    public MainMenuHook() {

    }

    @Override
    public void initGui() {
        super.initGui();
        int var3 = this.height / 2;
        buttonList.add(new GuiButton(1, this.width / 2 - 100, var3 - 32, 98, 20, "Singleplayer"));
        buttonList.add(new GuiButton(2, this.width / 2 + 2, var3 - 32, 98, 20, "Multiplayer"));

        buttonList.add(new GuiButton(3, this.width / 2 - 100, var3 - 10, 200, 20, "Settings"));

        buttonList.add(new GuiButton(5, this.width / 2 - 100, var3 + 12, 98, 20, "Language"));
        buttonList.add(new GuiButton(69, this.width / 2 + 2, var3 + 12, 98, 20, "Quit"));

        this.addButtons(this.height / 2 + (48 - 70), 24);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1,1,1,1);
        mouseXOffset = 4;
        mouseYOffset = 4;

        GlStateManager.pushMatrix();
        if(Paradise.INSTANCE.wallpaper != null) {
            DrawHelper.draw2DImage(Paradise.INSTANCE.wallpaper, 0, 0, this.width, this.height, new Color(0, 0, 0));
        } else {
            Gui.drawRect(0,0,width,height, new Color(23,23,23).getRGB());
        }
        GlStateManager.popMatrix();


        GlStateManager.pushMatrix();
        GlStateManager.scale(1.2, 1.2, 1.2);
        mc.fontRendererObj.drawString("Welcome to Paradise! Current build: " + Paradise.INSTANCE.VER, 2, 4, -1);
        GlStateManager.popMatrix();

        mc.fontRendererObj.drawString("Code base by Isnow, continued by Milk", 2, 14, -1);

        char[] chars = "https://www.youtube.com/@SpigotRCE".toCharArray();

        int charOffset = 2; // init position
        int colorOffset = 0;
        for (char line : chars)
        {
            mc.fontRendererObj.drawString(String.valueOf(line), charOffset, this.height - mc.fontRendererObj.FONT_HEIGHT, ColorHelper.getChroma(colorOffset, 1, 1).getRGB());
            charOffset += mc.fontRendererObj.getCharWidth(line);
            colorOffset -= 100;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void addButtons(int p_73969_1_, int p_73969_2_) {
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch(button.id) {
            case 1:
                mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 2:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 3:
                mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;
            case 4:
                break;
            case 5:
                mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
                break;
            case 69:
                mc.shutdown();
                break;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void onGuiClosed() {

    }
}
