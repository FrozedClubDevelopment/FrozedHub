package club.frozed.frozedhub.queue;

import club.frozed.frozedhub.FrozedHub;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Data;
import me.activated.core.api.player.PlayerData;
import me.activated.core.api.rank.RankData;
import me.activated.core.plugin.AquaCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

@Data
public class Queue {

    private String server;
    private List<OfflinePlayer> list;
    private Map<OfflinePlayer, BukkitTask> taskMap;
    private boolean paused;
    private int limit;

    public Queue(String server) {
        this.server = server;
        this.list = new ArrayList<>();
        this.taskMap = new HashMap<>();
        this.paused = false;
        this.limit = 400;
    }

    public void addEntry(OfflinePlayer p) {
        if (FrozedHub.getInstance().getQueueManager().getQueue(p) != null) {
            FrozedHub.getInstance().getQueueManager().getQueue(p).removeEntry(p);
        }
        list.add(p);
    }

    public List<OfflinePlayer> getPlayers() {
        return this.list;
    }

    public void removeEntry(OfflinePlayer p) {
        if (list.contains(p))
            list.remove(p);
    }

    public int getSize() {
        return list.size();
    }

    public OfflinePlayer getPlayerAt(int i) {
        return list.get(i);
    }

    public void sendFirst() {
        if (!list.isEmpty()) {
            Player p = list.get(0).getPlayer();

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(this.server);
            for (Queue q : FrozedHub.getInstance().getQueueManager().getQueues()) {
                if (q.getPlayers().contains(p)) {
                    q.removeEntry(p);
                }
            }
            p.sendPluginMessage(FrozedHub.getInstance(), "BungeeCord", out.toByteArray());
        }
    }

}
