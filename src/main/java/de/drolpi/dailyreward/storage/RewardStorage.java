package de.drolpi.dailyreward.storage;

import de.drolpi.dailyreward.model.RewardPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RewardStorage {

    private final Map<UUID, RewardPlayer> playerContainer;

    public RewardStorage() {
        this.playerContainer = new HashMap<>();
    }

    public void createPlayer(Player player) {
        this.playerContainer.put(player.getUniqueId(), new RewardPlayer(player.getUniqueId()));
    }

    public RewardPlayer player(Player player) {
        if (!this.playerContainer.containsKey(player.getUniqueId())) {
            createPlayer(player);
        }
        return this.playerContainer.get(player.getUniqueId());
    }

    public void deletePlayer(Player player) {
        this.playerContainer.remove(player.getUniqueId());
    }

}
