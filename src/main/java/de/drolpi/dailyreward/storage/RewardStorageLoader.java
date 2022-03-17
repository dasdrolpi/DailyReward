package de.drolpi.dailyreward.storage;

import de.drolpi.dailyreward.configuration.AbstractConfigurationLoader;

import java.nio.file.Paths;

public class RewardStorageLoader extends AbstractConfigurationLoader<RewardStorage> {

    public RewardStorageLoader() {
        super(Paths.get("plugins", "DailyReward", "storage.json"), RewardStorage.class);
    }

    @Override
    public RewardStorage createObject() {
        return new RewardStorage();
    }
}
