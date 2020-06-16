package club.frozed.frozedhub;

import club.frozed.frozedhub.commands.Commands;
import club.frozed.frozedhub.listeners.HubListener;
import club.frozed.frozedhub.listeners.ItemListener;
import club.frozed.frozedhub.queue.QueueManager;
import club.frozed.frozedhub.scoreboard.ScoreboardAdapter;
import club.frozed.frozedhub.selector.Selector;
import club.frozed.frozedhub.utils.command.CommandFramework;
import club.frozed.frozedhub.utils.scoreboard.Assemble;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FrozedHub extends JavaPlugin implements PluginMessageListener {

    @Getter private static FrozedHub instance;
    @Getter private QueueManager queueManager;
    @Getter private Assemble assemble;
    @Getter private CommandFramework commandFramework;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        queueManager = new QueueManager();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::initPlayerCount, 0, 20L);
        Bukkit.getPluginManager().registerEvents(new Selector(), this);
        Bukkit.getPluginManager().registerEvents(new HubListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemListener(), this);
        this.commandFramework = new CommandFramework(this);
        commandFramework.registerCommands(new Commands());
        commandFramework.registerHelp();
        assemble = new Assemble(this, new ScoreboardAdapter());
    }

    @Override
    public void onDisable() {
        assemble.cleanup();
        instance = null;
    }

    private void initPlayerCount() {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("PlayerCount");
            out.writeUTF("ALL");

            Bukkit.getServer().sendPluginMessage(this, "BungeeCord", b.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int amount = 0;
    String server = "ALL";

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;

        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            String command = in.readUTF();
            if (command.equals("PlayerCount")) {
                server = in.readUTF();
                amount = in.readInt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
