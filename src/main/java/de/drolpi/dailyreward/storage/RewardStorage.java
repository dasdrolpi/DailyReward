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

    public @NotNull RewardPlayer createPlayer(@NotNull Player player) {
        var rewardPlayer = new RewardPlayer();
        this.playerContainer.put(player.getUniqueId(), rewardPlayer);
        return rewardPlayer;
    }

    public @NotNull RewardPlayer player(@NotNull Player player) {
        if (!this.playerContainer.containsKey(player.getUniqueId())) {
            return createPlayer(player);
        }
        return this.playerContainer.get(player.getUniqueId());
    }

    public void deletePlayer(@NotNull Player player) {
        this.playerContainer.remove(player.getUniqueId());
    }

}
