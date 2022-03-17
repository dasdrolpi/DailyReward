package de.drolpi.dailyreward.object;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class RewardPlayer {

    private final UUID uuid;
    private final Map<RewardType, RewardObject> rewards;

    public RewardPlayer(UUID uuid) {
        this.uuid = uuid;
        this.rewards = new HashMap<>();

        for (RewardType value : RewardType.values()) {
            RewardObject rewardObject = new RewardObject(value);
            rewardObject.setTimeStamp(0);
            this.rewards.put(value, rewardObject);
        }
    }
}