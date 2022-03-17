package de.drolpi.dailyreward;

import de.drolpi.dailyreward.commands.RewardCommand;
import de.drolpi.dailyreward.inventory.RewardInventory;
import de.drolpi.dailyreward.listener.PlayerClickListener;
import de.drolpi.dailyreward.provider.RewardProvider;
import de.drolpi.dailyreward.storage.RewardStorage;
import de.drolpi.dailyreward.storage.RewardStorageLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class DailyRewardPlugin extends JavaPlugin {

    private RewardProvider rewardProvider;
    private RewardInventory rewardInventory;
    private RewardStorageLoader rewardStorageLoader;
    private RewardStorage rewardStorage;

    @Override
    public void onEnable() {
        this.rewardStorageLoader = new RewardStorageLoader();
        this.rewardStorage = rewardStorageLoader.loadOrCreateFile();

        this.rewardProvider = new RewardProvider(rewardStorage);
        this.rewardInventory = new RewardInventory(this);
        var server = getServer();

        server.getPluginCommand("reward").setExecutor(new RewardCommand(this));
        server.getPluginManager().registerEvents(new PlayerClickListener(this), this);
    }

    @Override
    public void onDisable() {
        this.rewardStorageLoader.saveFile(this.rewardStorage);
    }

    public RewardProvider rewardProvider() {
        return this.rewardProvider;
    }

    public RewardInventory rewardInventory() {
        return this.rewardInventory;
    }
}
