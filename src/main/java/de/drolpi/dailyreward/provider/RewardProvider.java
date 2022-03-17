package de.drolpi.dailyreward.provider;

import de.drolpi.dailyreward.object.RewardObject;
import de.drolpi.dailyreward.object.RewardPlayer;
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
        var rewardObject = rewardPlayer.getRewards().get(rewardType);

        rewardObject.setTimeStamp(System.currentTimeMillis() + rewardType.getTime());
    }

    public Collection<RewardObject> playerRewards(Player player) {
        return storage.getPlayer(player).getRewards().values();
    }

    public RewardStorage storage() {
        return storage;
    }
}
