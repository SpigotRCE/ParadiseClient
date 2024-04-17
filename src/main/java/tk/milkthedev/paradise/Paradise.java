package tk.milkthedev.paradise;

import java.io.File;
import java.util.HashMap;

import tk.milkthedev.paradise.command.CommandManager;
import tk.milkthedev.paradise.command.impl.*;
import tk.milkthedev.paradise.exploit.ExploitManager;
import tk.milkthedev.paradise.exploit.impl.flood.ChannelExploit;
import tk.milkthedev.paradise.exploit.impl.flood.CommandBlockExploit;
import tk.milkthedev.paradise.exploit.impl.flood.LuckpermsExploit;
import tk.milkthedev.paradise.exploit.impl.nbt.*;
import tk.milkthedev.paradise.rpc.DiscordRichPresenceManager;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.item.Item;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;
import tk.milkthedev.paradise.exploit.impl.creative.AnvilExploit;
import tk.milkthedev.paradise.exploit.impl.flood.AttackExploit;
import tk.milkthedev.paradise.exploit.impl.other.FaweExploit;
import tk.milkthedev.paradise.exploit.impl.other.MultiverseExploit;
import tk.milkthedev.paradise.exploit.impl.other.PEXExploit;
import tk.milkthedev.paradise.exploit.impl.other.SpamExploit;
import viamcp.ViaMCP;

public enum Paradise {
  INSTANCE;

  public String broadcastOfMilk = "&1Server hacked by &4&lhttps://www.youtube.com/@SpigotRCE &7https://discord.gg/spigotrce";
  public final CommandManager commandManager;
  private final ExploitManager exploitManager;
  private DiscordRichPresenceManager discordRichPresence;

  public final String VER = "PUBLIC v1.8/1.2 dev";

  public Session orginalSession;
  public String PreUUID;
  public boolean bungeeHack;
  public boolean premiumUUID;
  public boolean sessionPremium;


  public String ipBungeeHack = "1.1.1.1";
  public String fakeNick = "NIGGERS ON TOP, NIGGA";
  public String bungeeGuardField = "bungeeguard token here";
  public boolean autoIP = false;

  public final HashMap<Item, String> toolBinds = new HashMap<>();
  public final HashMap<Integer, String> keyToolBinds = new HashMap<>();

  public DynamicTexture wallpaper;


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
    try {
      discordRichPresence = new DiscordRichPresenceManager();
    } catch (Exception ignored) {}

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
        new DiscordSRVCommand(),
        new StopDiscordSRVCommand(),
        new TestCommand(),
        new MotionBlurCommand(),
        new VClipCommand(),
        new BanAllCommand(),
        new FetchAllCommand(),
        new FakeBroadcast(),
        new CopyBroadcastCommand(),
        new BungeeDumpCommand(),
        new SeenDumpCommand(),
        new GMCCommand(),
        new ForceOPCommand(),
        new TabCompleteCrash(),
        new ConnectCommand()
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
