package de.drolpi.dailyreward.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RewardPlayer {

    private final Map<RewardType, RewardObject> rewards;

    public RewardPlayer() {
        this.rewards = new HashMap<>();

        for (var value : RewardType.values()) {
            var rewardObject = new RewardObject(value);
            rewardObject.setTimeStamp(0);
            this.rewards.put(value, rewardObject);
        }
    }

    public @NotNull Map<RewardType, RewardObject> rewards() {
        return this.rewards;
    }
}
