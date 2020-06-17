package club.frozed.frozedhub.listeners;

import club.frozed.frozedhub.FrozedHub;
import club.frozed.frozedhub.utils.ItemBuilder;
import club.frozed.frozedhub.utils.Messages;
import club.frozed.frozedhub.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class ParticlesListener implements Listener {

    public void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, Messages.CC("&8Particle Effects"));

        ItemBuilder redstoneParticles = new ItemBuilder(Material.REDSTONE).displayName(ChatColor.GREEN + "Redstone Particles");
        redstoneParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(10, redstoneParticles.build());

        ItemBuilder musicParticles = new ItemBuilder(Material.NOTE_BLOCK).displayName(ChatColor.GREEN + "Musical Particles");
        musicParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(11, musicParticles.build());

        ItemBuilder criticalsParticles = new ItemBuilder(Material.PUMPKIN_SEEDS).displayName(ChatColor.GREEN + "Criticals Particles");
        criticalsParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(12, criticalsParticles.build());

        ItemBuilder loveParticles = new ItemBuilder(Material.RED_ROSE).displayName(ChatColor.GREEN + "Love Particles");
        loveParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(13, loveParticles.build());

        ItemBuilder sparkParticles = new ItemBuilder(Material.NETHER_STAR).displayName(ChatColor.GREEN + "Spark Particles");
        sparkParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(14, sparkParticles.build());

        ItemBuilder happyParticles = new ItemBuilder(Material.EMERALD).displayName(ChatColor.GREEN + "Happy Particles");
        happyParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(15, happyParticles.build());

        ItemBuilder angryParticles = new ItemBuilder(Material.COAL).displayName(ChatColor.GREEN + "Angry Particles");
        angryParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(16, angryParticles.build());

        ItemBuilder cloudParticles = new ItemBuilder(Material.INK_SACK).data((short) 15).displayName(ChatColor.GREEN + "Cloud Particles");
        cloudParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(20, cloudParticles.build());

        ItemBuilder flameParticles = new ItemBuilder(Material.BLAZE_POWDER).displayName(ChatColor.GREEN + "Flame Particles");
        flameParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(21, flameParticles.build());

        ItemBuilder lavaParticles = new ItemBuilder(Material.LAVA_BUCKET).displayName(ChatColor.GREEN + "Lava Particles");
        lavaParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(23, lavaParticles.build());

        ItemBuilder waterParticles = new ItemBuilder(Material.WATER_BUCKET).displayName(ChatColor.GREEN + "Water Particles");
        waterParticles.lore(
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------",
                ChatColor.YELLOW + "Left click to select this.",
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-----------------------");
        inv.setItem(24, waterParticles.build());

        p.openInventory(inv);
    }


    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() != null && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().getDisplayName() != null)
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&7» &b&lCosmetics &7«"))) {
                    openInventory(e.getPlayer());
                }
        }
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
            Player p = (Player) e.getWhoClicked();
            if (!e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Particle Effects")) {
                return;
            }
            String name = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            String particles = "";
            if (e.getCurrentItem().equals(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).displayName(" ").data((short) 15).build())) {
                return;
            }
            if (e.getClick().isLeftClick()) {
                if (name.toLowerCase().contains("redstone")) {
                    particles = "Redstone";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.REDSTONE.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("music")) {
                    particles = "Music";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.NOTE.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("criticals")) {
                    particles = "Criticals";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.CRIT_MAGIC.display(10, 15, 0, 0, 0, player.getLocation().add(0, 1, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("love")) {
                    particles = "Love";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.HEART.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("spark")) {
                    particles = "Spark";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.FIREWORKS_SPARK.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("happy")) {
                    particles = "Happy Villager";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.VILLAGER_HAPPY.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("angry")) {
                    particles = "Angry Villager";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.VILLAGER_ANGRY.display(10, 15, 0, 0, 0, player.getLocation().add(0, 2, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("cloud")) {
                    particles = "Cloud";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.CLOUD.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("flame")) {
                    particles = "Flame";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.FLAME.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0.5, 0), 10);
                            ParticleEffect.FLAME.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("lava")) {
                    particles = "Lava Pop";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.LAVA.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);

                } else if (name.toLowerCase().contains("water")) {
                    particles = "Water";
                    Bukkit.getScheduler().cancelAllTasks();
                    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FrozedHub.getInstance(), () -> {
                        Player player = (Player) e.getWhoClicked();
                        for (Player ignored : Bukkit.getServer().getOnlinePlayers()) {
                            ParticleEffect.DRIP_WATER.display(10, 15, 0, 0, 0, player.getLocation().add(0, 0, 0), 10);
                        }
                    }, 0, 0L);
                }
                p.sendMessage(Messages.CC("&aYou've selected the " + particles + " particle effects!"));
            }
            p.closeInventory();
        }
    }

}