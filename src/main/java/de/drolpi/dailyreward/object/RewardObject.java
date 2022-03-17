package de.drolpi.dailyreward.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RewardObject {

    private final RewardType rewardType;
    private long timeStamp;
}
