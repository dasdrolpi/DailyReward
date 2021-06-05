package de.drolpi.dailyreward.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author drolpi / Lars Nippert
 * @project DailyReward
 * @date Date: 05.06.2021
 * @time Time: 17:33
 */

@AllArgsConstructor
@Getter
public enum RewardType {

    FIRST(29, 500, TimeUnit.MILLISECONDS.convert(4, TimeUnit.HOURS)),
    SECOND(31, 1000, TimeUnit.MILLISECONDS.convert(8, TimeUnit.HOURS)),
    THIRD(33, 10000, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

    private final int slot;
    private final int coins;
    private final long time;

}
