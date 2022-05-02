package de.drolpi.dailyreward.storage;

import de.drolpi.dailyreward.model.RewardPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RewardStorage {

    private final Map<UUID, RewardPlayer> playerContainer;

    public RewardStorage() {
        this.playerContainer = new HashMap<>();
    }

    public RewardPlayer createPlayer(Player player) {
        var rewardPlayer = new RewardPlayer();
        this.playerContainer.put(player.getUniqueId(), rewardPlayer);
        return rewardPlayer;
    }

    public @NotNull RewardPlayer player(Player player) {
        if (!this.playerContainer.containsKey(player.getUniqueId())) {
            return createPlayer(player);
        }
        return this.playerContainer.get(player.getUniqueId());
    }

    public void deletePlayer(Player player) {
        this.playerContainer.remove(player.getUniqueId());
    }

}
