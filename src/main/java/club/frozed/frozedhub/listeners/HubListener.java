package club.frozed.frozedhub.listeners;

import club.frozed.frozedhub.FrozedHub;
import club.frozed.frozedhub.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class HubListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        p.setHealth(20D);
        p.setFoodLevel(20);
        Location loc = e.getPlayer().getWorld().getSpawnLocation();
        loc.setX(loc.getX() + 0.5);
        loc.setZ(loc.getZ() + 0.5);
        p.teleport(loc);
        p.getInventory().clear();

        p.sendMessage("§7§m-----------------------------------------------------");
        p.sendMessage("");
        p.sendMessage("§fWelcome to the §bFrozed Club §oDevelopment");
        p.sendMessage("");
        p.sendMessage("§f✦ §bDiscord: §ffrozed.club/discord");
        p.sendMessage("§f✦ §bTwitter: §ftwitter.com/FrozedClubGames");
        p.sendMessage("§f✦ §bMC-Market: §fhttps://www.mc-market.org/members/230758/");
        p.sendMessage("");
        p.sendMessage("§7§m-----------------------------------------------------");

        p.getInventory().setItem(1, new ItemBuilder(Material.WATCH, 1).displayName(ChatColor.translateAlternateColorCodes('&', "&7» &b&lSelector &7«")).build());
        p.getInventory().setItem(7, new ItemBuilder(Material.ENDER_PEARL, 1).displayName(ChatColor.translateAlternateColorCodes('&', "&7» &b&lEnderbutt &7«")).build());
        p.getInventory().setItem(4, new ItemBuilder(Material.CHEST, 1).displayName(ChatColor.translateAlternateColorCodes('&', "&7» &b&lCosmetics &7«")).build());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().getName().equals("Elb1to") || e.getPlayer().getName().equals("Netvision") || e.getPlayer().getName().equals("Reinstallation")) {
            e.setFormat(ChatColor.DARK_AQUA + "❖ " + ChatColor.AQUA.toString() + e.getPlayer().getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
        } else {
            e.setFormat(ChatColor.AQUA.toString() + e.getPlayer().getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
        }
    }

    @EventHandler
    public void onMoveItem(InventoryClickEvent e) {
        if (e.getClickedInventory() == null || e.getClickedInventory().getName() == null)
            return;
        if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE || e.getClickedInventory().getName().contains("server")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLoadEvent(PluginEnableEvent e) {
        Bukkit.getWorlds().get(0).setTime(6000);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLimitsBypassing(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location sL = e.getPlayer().getWorld().getSpawnLocation();
        Location pL = e.getPlayer().getLocation();

        if (pL.getY() <= 0) {
            p.teleport(sL);
        }
        if (pL.getY() >= 128) {
            p.teleport(sL);
        }

        if (pL.getX() >= 60) {
            p.teleport(sL);
        }
        if (pL.getZ() >= 10) {
            p.teleport(sL);
        }
        if (pL.getX() <= -60) {
            p.teleport(sL);
        }
        if (pL.getZ() <= -110) {
            p.teleport(sL);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

}
