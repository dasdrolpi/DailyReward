package de.drolpi.dailyreward.provider;

import de.drolpi.dailyreward.object.RewardObject;
import de.drolpi.dailyreward.object.RewardType;
import de.drolpi.dailyreward.storage.RewardStorage;
import org.bukkit.entity.Player;

import java.util.Collection;

public class RewardProvider {

    private final RewardStorage storage;

    public RewardProvider() {
        this.storage = RewardStorage.load();
    }

    public void resetReward(Player player, RewardType rewardType) {
        var rewardPlayer = storage.getPlayer(player);
        var rewardObject = rewardPlayer.rewards().get(rewardType);

        rewardObject.setTimeStamp(System.currentTimeMillis() + rewardType.time());
    }

    public Collection<RewardObject> playerRewards(Player player) {
        return storage.getPlayer(player).rewards().values();
    }

    public RewardStorage storage() {
        return storage;
    }
}
