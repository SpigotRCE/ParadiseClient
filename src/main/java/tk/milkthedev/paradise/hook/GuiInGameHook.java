package tk.milkthedev.paradise.hook;

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
import viamcp.ViaMCP;

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

    List<String> debungInfo = new ArrayList<String>();

    if (!mc.isSingleplayer()) {
      long lastPacketMS = TimeHelper.getCurrentTime() - Holder.getLastPacketMS();
      if(TestCommand.exploit) {
        return;
      }
      if(!ScreenshotCommand.hidden) {
        debungInfo.add(ChatHelper.fix("&fServer: &d" + mc.getCurrentServerData().serverIP));
      }

      try {
        if (mc.thePlayer.getClientBrand() != null) {
          String brand = mc.thePlayer.getClientBrand().contains("<- ") ?
                  mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand()
                          .split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];
          debungInfo.add(ChatHelper.fix("&fEngine: &d" + brand));
        }
      } catch (Exception ignored) {
        debungInfo.add(ChatHelper.fix("&fEngine: &d" + "INVALID"));
      }

      if (Holder.getLastPacketMS() != -1) {
        debungInfo.add(ChatHelper.fix(String.format("&fLast packet: &d%sms", lastPacketMS)));
      }

      if (Holder.getTPS() != -1) {
        debungInfo.add(ChatHelper.fix(String.format("&fPredicted TPS: &d%s", Math.round(Holder.getTPS() * 100.0) / 100.0)));
      } else {
        debungInfo.add(ChatHelper.fix("&fPredicted TPS: &dInvalid"));
      }

      debungInfo.add(ChatHelper.fix("&fFPS: &d" + Minecraft.debugFPS));
      debungInfo.add(ChatHelper.fix("&fClient Protocol: &d" + ViaMCP.getInstance().getVersion()));
      debungInfo.add(ChatHelper.fix("&fClient Version: &d" + ProtocolVersion.getProtocol(ViaMCP.getInstance().getVersion()).getName()));
      debungInfo.add(ChatHelper.fix("&fNick: &d" + mc.thePlayer.getName()));

      if (Paradise.INSTANCE.bungeeHack) {
        debungInfo.add(ChatHelper.fix("&fIP-Forward: &dEnabled"));
        if (!ScreenshotCommand.hidden) {
          debungInfo.add(ChatHelper.fix("   &fForwarding IP: &d" + Paradise.INSTANCE.ipBungeeHack));
          debungInfo.add(ChatHelper.fix("   &fFake Nick: &d" + Paradise.INSTANCE.fakeNick));
          debungInfo.add(ChatHelper.fix((Paradise.INSTANCE.premiumUUID) ? "   &fType: &dPremium" : "    &fType: &dCracked"));
        }
      }
      else {
        debungInfo.add(ChatHelper.fix("&fIP-Forward: &dDisabled"));
      }
      if(mc.getNetHandler().bungeeDumpExecuted){
        debungInfo.add("&fDumping: &dBungeeDump");
        debungInfo.add("    &fCurrent Player: &d" + mc.getNetHandler().currentPlayer);
      }
      if(mc.getNetHandler().seenDumpExecuted){
        debungInfo.add("&fDumping: &dSeenDump");
        debungInfo.add("    &fCurrent Player: &d" + mc.getNetHandler().currentPlayer);
      }
    }

    for (int i = 0; i < debungInfo.size(); i++) {
      mc.fontRendererObj.drawString(ChatHelper.fix(debungInfo.get(i)), 6, 18 + i * 10, 0);
    }

  }
}
