package de.drolpi.dailyreward.storage;

import de.drolpi.dailyreward.model.RewardObject;
import de.drolpi.dailyreward.model.RewardPlayer;
import de.drolpi.dailyreward.model.RewardType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
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

    public void resetReward(@NotNull Player player, @NotNull RewardType rewardType) {
        var rewardPlayer = this.player(player);
        var rewardObject = rewardPlayer.rewards().get(rewardType);

        rewardObject.setTimeStamp(System.currentTimeMillis() + rewardType.time());
    }

    public @NotNull Collection<RewardObject> playerRewards(@NotNull Player player) {
        return this.player(player).rewards().values();
    }

    public void deletePlayer(@NotNull Player player) {
        this.playerContainer.remove(player.getUniqueId());
    }

}
