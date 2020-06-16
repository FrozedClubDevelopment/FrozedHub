package club.frozed.frozedhub.queue;

import club.frozed.frozedhub.FrozedHub;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class QueueManager implements Listener {

    @Getter public List<Queue> queues = new ArrayList<>();

    @Getter public List<Queue> pausedQueues = new ArrayList<>();

    public QueueManager() {
        Bukkit.getPluginManager().registerEvents(this, FrozedHub.getInstance());
        FrozedHub.getInstance().getConfig().getStringList("queues").forEach(queue -> queues.add(new Queue(queue)));

        new BukkitRunnable() {
            public void run() {
                for (Queue q : queues) {
                    if (!q.isPaused() && !q.getPlayers().isEmpty()) {
                        if (q.getSize() == 0) continue;
                        if (!q.getPlayerAt(0).isOnline() && !q.getList().isEmpty()) {
                            Player p = (Player) q.getPlayerAt(0);
                            q.getPlayers().remove(p);
                            if (q.getTaskMap().containsKey(p)) {
                                q.getTaskMap().get(p).cancel();
                                q.getTaskMap().remove(p);
                            }
                        } else if (q.getPlayerAt(0).isOnline()) {
                            q.sendFirst();
                        }
                    }
                }
            }
        }.runTaskTimer(FrozedHub.getInstance(), 120, 5);
    }

    public Queue getQueue(OfflinePlayer p) {
        for (Queue q : queues) {
            if (q.getPlayers().contains(p)) {
                return q;
            }
        }
        return null;
    }


    public Queue getQueue(String s) {
        for (Queue q : queues) {
            if (q.getServer().equalsIgnoreCase(s)) {
                return q;
            }
        }
        return null;
    }

    public String getQueueName(OfflinePlayer p) {
        return getQueue(p).getServer();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        for (Queue q : queues) {
            if (q.getPlayers().contains(p)) {
                q.removeEntry(p);
            }
        }
    }

}
