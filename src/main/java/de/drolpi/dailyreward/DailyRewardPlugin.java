package de.drolpi.dailyreward;

import de.drolpi.dailyreward.commands.RewardCommand;
import de.drolpi.dailyreward.inventory.RewardInventory;
import de.drolpi.dailyreward.listener.PlayerClickListener;
import de.drolpi.dailyreward.provider.RewardProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class DailyRewardPlugin extends JavaPlugin {

    private RewardProvider rewardProvider;
    private RewardInventory rewardInventory;

    @Override
    public void onEnable() {
        this.rewardProvider = new RewardProvider();
        this.rewardInventory = new RewardInventory(this);
        var server = getServer();

        server.getPluginCommand("reward").setExecutor(new RewardCommand(this));
        server.getPluginManager().registerEvents(new PlayerClickListener(this), this);
    }

    @Override
    public void onDisable() {
        rewardProvider.storage().save();
    }

    public RewardProvider rewardProvider() {
        return this.rewardProvider;
    }

    public RewardInventory rewardInventory() {
        return this.rewardInventory;
    }
}
