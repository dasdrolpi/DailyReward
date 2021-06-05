package de.drolpi.dailyreward.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author drolpi / Lars Nippert
 * @project DailyReward
 * @date Date: 05.06.2021
 * @time Time: 17:32
 */

@Getter
@Setter
@RequiredArgsConstructor
public class RewardObject {

    private final RewardType rewardType;
    private long timeStamp;
}
