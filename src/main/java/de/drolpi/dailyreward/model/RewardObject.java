package de.drolpi.dailyreward.model;

import org.jetbrains.annotations.NotNull;

public class RewardObject {

    private final RewardType rewardType;
    private long timeStamp;

    public RewardObject(RewardType rewardType) {
        this.rewardType = rewardType;
    }

    public @NotNull RewardType rewardType() {
        return this.rewardType;
    }

    public long timeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
