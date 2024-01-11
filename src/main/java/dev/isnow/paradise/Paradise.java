package dev.isnow.paradise;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.isnow.paradise.command.CommandManager;
import dev.isnow.paradise.command.impl.*;
import dev.isnow.paradise.exploit.ExploitManager;
import dev.isnow.paradise.exploit.impl.flood.ChannelExploit;
import dev.isnow.paradise.exploit.impl.flood.CommandBlockExploit;
import dev.isnow.paradise.exploit.impl.flood.LuckpermsExploit;
import dev.isnow.paradise.exploit.impl.nbt.*;
import dev.isnow.paradise.exploit.impl.other.*;
import dev.isnow.paradise.rpc.DiscordRichPresenceManager;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.item.Item;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;
import dev.isnow.paradise.exploit.impl.creative.AnvilExploit;
import dev.isnow.paradise.exploit.impl.flood.AttackExploit;
import viamcp.ViaMCP;

public enum Paradise {
  INSTANCE;

  public String broadcastOfMilk = "&1Server hacked by &4&lhttps://www.youtube.com/@SpigotRCE &7https://discord.gg/Mmfq4RCtyE";
  public final CommandManager commandManager;
  private final ExploitManager exploitManager;
  private DiscordRichPresenceManager discordRichPresence;

  public final String VER = "PRIVATE";

  public Session orginalSession;
  public String PreUUID;
  public boolean bungeeHack;
  public boolean premiumUUID;
  public boolean sessionPremium;

  public boolean autoVersion;

  public String ipBungeeHack = "1.1.1.1";
  public String fakeNick = "NIGGERS ON TOP, NIGGA";
  public boolean autoIP = false;

  public final HashMap<Item, String> toolBinds = new HashMap<>();
  public final HashMap<Integer, String> keyToolBinds = new HashMap<>();

  public DynamicTexture wallpaper;

  public boolean dev = true;

  Paradise() {
    System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "false");
    try
    {
      ViaMCP.getInstance().start();
      ViaMCP.getInstance().setVersion(47);
      ViaMCP.getInstance().initAsyncSlider(5, 5, 100, 20);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    // Removed for sometime
//    try {
//      discordRichPresence = new DiscordRichPresenceManager();
//    } catch (Exception ignored) {}

    commandManager = new CommandManager(
        new ExploitCommand(),
        new HelpCommand(),
        new OnlineCommand(),
        new FakeGamemodeCommand(),
        new BindCommand(),
        new PluginsCommand(),
        new RainbowSpamCommand(),
        new StopRainbowSpamCommand(),
        new ScreenshotCommand(),
        new ToggleSprintCommand(),
        new BindButtonCommand(),
        new BaltopDumpCommand(),
        new DiscordSRVCommand(),
        new StopDiscordSRVCommand(),
        new HolographicDisplaysCommand(),
        new TestCommand(),
        new MotionBlurCommand(),
        new VClipCommand(),
        new BanAllCommand(),
        new FetchAllCommand(),
        new FakeBroadcast(),
        new CopyBroadcastCommand(),
        new BungeeDumpCommand()
    );

    exploitManager = new ExploitManager(
        new AnvilExploit(),
        new AttackExploit(),
        new BookExploit(),
        new InvalidItem(),
        new SpamExploit(),
        new CommandBlockExploit(),
        new FaweExploit(),
        new ExploitFixer2Exploit(),
        new ExploitFixerExploit(),
        new OnePacketExploit(),
        new MultiverseExploit(),
        new PEXExploit(),
        new ChannelExploit(),
        new LuckpermsExploit()
    );

    if(orginalSession == null) {
      orginalSession = Minecraft.getMinecraft().getSession();
      fakeNick = orginalSession.getUsername();
    }

    File dir = new File(Minecraft.getMinecraft().mcDataDir, "Paradise");
    File configDir = new File(dir, "SaveableItems");
    if(!dir.exists()) {
      dir.mkdir();
    }
    if(!configDir.exists()) {
      configDir.mkdir();
    }
    Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
  }

  public void setDisplay() {
    Display.setTitle("ParadiseClient | " + VER);
  }

  public void shutDown() {
    DiscordRPC.discordShutdown();
  }

  public CommandManager getCommandManager() {
    return commandManager;
  }

  public ExploitManager getExploitManager() {
    return exploitManager;
  }

  public DiscordRichPresenceManager getDiscordRichPresence() {
    return discordRichPresence;
  }
}
