package club.frozed.frozedhub.listeners;

import club.frozed.frozedhub.FrozedHub;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemListener implements Listener {

    public void setupEnderpearlRunnable(Item item) {
        (new BukkitRunnable() {
            public void run() {
                if (item.isDead()) {
                    this.cancel();
                }
                if (item.getVelocity().getX() == 0.0D || item.getVelocity().getY() == 0.0D || item.getVelocity().getZ() == 0.0D) {
                    Player player = (Player) item.getPassenger();
                    item.remove();
                    if (player != null) {
                        player.teleport(player.getLocation().add(0.0D, 0.5D, 0.0D));
                    }
                    this.cancel();
                }
                if (item.getPassenger() == null) {
                    item.remove();
                    this.cancel();
                }
            }
        }).runTaskTimer(FrozedHub.getInstance(), 2L, 1L);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack itemStack = player.getItemInHand();
            if (itemStack.getType() == Material.ENDER_PEARL) {
                event.setCancelled(true);
                event.setUseItemInHand(Event.Result.DENY);
                event.setUseInteractedBlock(Event.Result.DENY);
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    Item item = player.getWorld().dropItem(player.getLocation().add(0.0D, 0.5D, 0.0D), new ItemStack(Material.ENDER_PEARL, 1));
                    item.setPickupDelay(10000);
                    item.setVelocity(player.getLocation().getDirection().normalize().multiply(2F));
                    item.setPassenger(player);
                    player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    this.setupEnderpearlRunnable(item);
                    player.updateInventory();
                }
            }
        }
    }
}
