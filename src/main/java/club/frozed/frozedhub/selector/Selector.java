package club.frozed.frozedhub.selector;

import club.frozed.frozedhub.FrozedHub;
import club.frozed.frozedhub.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Selector implements Listener {

    public void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Select a server to join");
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1)
                    .displayName(ChatColor.GREEN + " ")
                    .data((short) 15).build());
        }

        inv.setItem(12, new ItemBuilder(Material.CHEST, 1)
                .displayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "FrozedSG")
                .lore(getLore("frozedsg", p, "FrozedSG is a plugin", "based on Minemen's", "and Lunar's PotSG."))
                .build());

        inv.setItem(14, new ItemBuilder(Material.MUSHROOM_SOUP, 1)
                .displayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "FrozedTeams")
                .lore(getLore("frozedteams", p, "MineHQ MCTeams", "replica made by Elb1to."))
                .build());

        inv.setItem(26, new ItemBuilder(Material.STICK, 1)
                .displayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "ZenPractice")
                .lore(getLore("zenpractice", p, "Custom Zonix fork with", "all the known bugs", "fixed, new features", "and much more.", " ", "Contact Elb1to#8527", "through Discord", "to get whitelisted."))
                .build());

        p.openInventory(inv);
    }

    public List<String> getLore(String serverName, Player p, String... info) {
        List<String> lores = new ArrayList<>();

        lores.add(ChatColor.GREEN.toString());
        for (String i : info) {
            lores.add("§7§o" + i);
        }
        lores.add(ChatColor.GREEN.toString());
        lores.add("§7» §bClick to join §7«");

        return lores;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() != null && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().getDisplayName() != null)
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&7» &b&lSelector &7«"))) {
                    openInventory(e.getPlayer());
                }
        }
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
            Player p = (Player) e.getWhoClicked();
            String name = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            if (name.contains(ChatColor.RED.toString())) return;
            if (e.getClick().isRightClick()) {
                String server = "???";
                if (name.contains("frozedsg")) {
                    FrozedHub.getInstance().getQueueManager().getQueue("frozedsg").removeEntry(p);
                    server = "FrozedSG";
                } else if (name.contains("frozedteams")) {
                    FrozedHub.getInstance().getQueueManager().getQueue("frozedteams").removeEntry(p);
                    server = "FrozedTeams";
                } else if (name.contains("zenpractice")) {
                    FrozedHub.getInstance().getQueueManager().getQueue("zenpractice").removeEntry(p);
                    server = "ZenPractice";
                }
                if (!server.equals("???")) {
                    p.sendMessage("§cLeft the queue for " + server);
                    p.closeInventory();
                    openInventory(p);
                }
            } else {
                boolean joined = true;
                if (name.contains("frozedsg")) {
                    FrozedHub.getInstance().getQueueManager().getQueue("frozedsg").addEntry(p);
                } else if (name.contains("frozedteams")) {
                    FrozedHub.getInstance().getQueueManager().getQueue("frozedteams").addEntry(p);
                } else if (name.contains("zenpractice")) {
                    FrozedHub.getInstance().getQueueManager().getQueue("zenpractice").addEntry(p);
                } else {
                    joined = false;
                }

                if (joined) {
                    p.closeInventory();
                }
            }
        }
    }

}
