package club.frozed.frozedhub.commands;

import club.frozed.frozedhub.utils.command.Command;
import club.frozed.frozedhub.utils.command.CommandArgs;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Commands {

    @Command(name = "spawn", inGameOnly = true)
    public void spawnCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        Location loc = player.getWorld().getSpawnLocation();
        loc.setX(loc.getX() + 0.5);
        loc.setZ(loc.getZ() + 0.5);
        player.teleport(loc);
    }

}
