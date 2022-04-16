package de.drolpi.dailyreward.provider;

import de.drolpi.dailyreward.model.RewardObject;
import de.drolpi.dailyreward.model.RewardType;
import de.drolpi.dailyreward.storage.RewardStorage;
import org.bukkit.entity.Player;

import java.util.Collection;

public record RewardProvider(RewardStorage storage) {

    public void resetReward(Player player, RewardType rewardType) {
        var rewardPlayer = this.storage.player(player);
        var rewardObject = rewardPlayer.rewards().get(rewardType);

        rewardObject.setTimeStamp(System.currentTimeMillis() + rewardType.time());
    }

    public Collection<RewardObject> playerRewards(Player player) {
        return this.storage.player(player).rewards().values();
    }
}
