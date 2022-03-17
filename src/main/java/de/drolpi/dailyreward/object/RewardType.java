package de.drolpi.dailyreward.object;

import java.util.concurrent.TimeUnit;

public enum RewardType {

    FIRST(29, 500, TimeUnit.MILLISECONDS.convert(4, TimeUnit.HOURS)),
    SECOND(31, 1000, TimeUnit.MILLISECONDS.convert(8, TimeUnit.HOURS)),
    THIRD(33, 10000, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

    private final int slot;
    private final int coins;
    private final long time;

    RewardType(int slot, int coins, long time) {
        this.slot = slot;
        this.coins = coins;
        this.time = time;
    }

    public int slot() {
        return this.slot;
    }

    public int coins() {
        return this.coins;
    }

    public long time() {
        return this.time;
    }
}
