package club.frozed.frozedhub.scoreboard;

import club.frozed.frozedhub.FrozedHub;
import club.frozed.frozedhub.utils.scoreboard.AssembleAdapter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardAdapter implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return FrozedHub.getInstance().getConfig().getString("scoreboard.title");
    }

    @Override
    public List<String> getLines(Player player) {
        return new ArrayList<>(FrozedHub.getInstance().getConfig().getStringList("scoreboard.normal"));
    }

}
