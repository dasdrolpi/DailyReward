package de.drolpi.dailyreward.provider;

import de.drolpi.dailyreward.object.RewardObject;
import de.drolpi.dailyreward.object.RewardPlayer;
import de.drolpi.dailyreward.object.RewardType;
import de.drolpi.dailyreward.storage.RewardStorage;
import org.bukkit.entity.Player;

import java.util.Collection;

public class RewardProvider {

    private final RewardStorage rewardStorage;

    public RewardProvider() {
        this.rewardStorage = RewardStorage.load();
    }

    public void resetReward(Player player, RewardType rewardType) {
        RewardPlayer rewardPlayer = rewardStorage.getPlayer(player);

        RewardObject rewardObject = rewardPlayer.getRewards().get(rewardType);
        rewardObject.setTimeStamp(System.currentTimeMillis() + rewardType.getTime());
    }

    public Collection<RewardObject> getPlayerRewards(Player player) {
        return rewardStorage.getPlayer(player).getRewards().values();
    }

    public RewardStorage getRewardStorage() {
        return rewardStorage;
    }
}
