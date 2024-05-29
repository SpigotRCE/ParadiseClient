package tk.milkthedev.paradise.hook;

import de.florianmichael.vialoadingbase.ViaLoadingBase;
import tk.milkthedev.paradise.Paradise;
import tk.milkthedev.paradise.command.impl.ScreenshotCommand;
import tk.milkthedev.paradise.command.impl.TestCommand;
import tk.milkthedev.paradise.helper.*;
import tk.milkthedev.paradise.holder.Holder;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import tk.milkthedev.paradise.helper.ChatHelper;
import tk.milkthedev.paradise.helper.ColorHelper;
import tk.milkthedev.paradise.helper.TimeHelper;
import de.florianmichael.viamcp.ViaMCP;

import java.util.ArrayList;
import java.util.List;

public class GuiInGameHook extends GuiIngame {

  private static final Minecraft mc = Minecraft.getMinecraft();

  public GuiInGameHook(Minecraft mcIn) {
    super(mcIn);
  }

  @Override
  public void renderGameOverlay(float partialTicks) {
    super.renderGameOverlay(partialTicks);


    char[] chars = "ParadiseClient".toCharArray();

    int charOffset = 6; // init position
    int colorOffset = 0;
    for (char line : chars)
    {
      mc.fontRendererObj.drawString(String.valueOf(line), charOffset, 7, ColorHelper.getChroma(colorOffset, 1, 1).getRGB());
      charOffset += mc.fontRendererObj.getCharWidth(line);
      colorOffset -= 100;
    }

    List<String> lines = new ArrayList<String>();

    if (!mc.isSingleplayer()) {
      long lastPacketMS = TimeHelper.getCurrentTime() - Holder.getLastPacketMS();
      if(TestCommand.exploit) {
        return;
      }
      if(!ScreenshotCommand.hidden) {
        lines.add(ChatHelper.fix("&fServer: " + mc.getCurrentServerData().serverIP));
      }

      try {
        if (mc.thePlayer.getClientBrand() != null) {
          String brand = mc.thePlayer.getClientBrand().contains("<- ") ?
                  mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand()
                          .split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];
          lines.add(ChatHelper.fix("&fEngine: " + brand));
        }
      } catch (Exception ignored) {
        lines.add(ChatHelper.fix("&fEngine: " + "INVALID"));
      }

      if (Holder.getLastPacketMS() != -1) {
        lines.add(ChatHelper.fix(String.format("&fLast packet: %sms", lastPacketMS)));
      }

      if (Holder.getTPS() != -1) {
        lines.add(ChatHelper.fix(String.format("&fPredicted TPS: %s", Math.round(Holder.getTPS() * 100.0) / 100.0)));
      } else {
        lines.add(ChatHelper.fix("&fPredicted TPS: Invalid"));
      }

      lines.add(ChatHelper.fix("&fFPS: " + Minecraft.debugFPS));

      ViaLoadingBase viaLoadingBase = ViaLoadingBase.getInstance();
      ProtocolVersion protocolVersion = viaLoadingBase.getTargetVersion();

      lines.add(ChatHelper.fix("&fClient Protocol: " + protocolVersion.getVersion()));
      lines.add(ChatHelper.fix("&fClient Version: " + protocolVersion.getName()));
      lines.add(ChatHelper.fix("&fNick: " + mc.thePlayer.getName()));

      if (Paradise.INSTANCE.bungeeHack) {
        lines.add(ChatHelper.fix("&fIP-Forward: Enabled"));
        if (!ScreenshotCommand.hidden) {
          lines.add(ChatHelper.fix("   &fForwarding IP: " + Paradise.INSTANCE.ipBungeeHack));
          lines.add(ChatHelper.fix("   &fFake Nick: " + Paradise.INSTANCE.fakeNick));
          lines.add(ChatHelper.fix((Paradise.INSTANCE.premiumUUID) ? "   &fType: Premium" : "    &fType: Cracked"));
        }
      }
      else {
        lines.add(ChatHelper.fix("&fIP-Forward: Disabled"));
      }
      if(mc.getNetHandler().bungeeDumpExecuted){
        lines.add("&fDumping: BungeeDump");
        lines.add("    &fCurrent Player: " + mc.getNetHandler().currentPlayer);
      }
      if(mc.getNetHandler().seenDumpExecuted){
        lines.add("&fDumping: SeenDump");
        lines.add("    &fCurrent Player: " + mc.getNetHandler().currentPlayer);
      }
    }

    int yOffset = 0;
    for (String line : lines) {
      String[] segments = line.split(":");
      String segmentOne = segments[0];
      String segmentTwo = segments[1];
      mc.fontRendererObj.drawString(ChatHelper.fix(segmentOne), 6, 18 + yOffset * 10, 0);

      int xOffset = 0;
      for (char c : segmentTwo.toCharArray())
      {
        mc.fontRendererObj.drawString(String.valueOf(c), 6 + mc.fontRendererObj.getStringWidth(segmentOne) + xOffset, 18 + yOffset * 10, ColorHelper.getChroma(yOffset * -300, 1, 1).getRGB());
        xOffset += mc.fontRendererObj.getCharWidth(c);
      }
      yOffset ++;
    }
  }
}
