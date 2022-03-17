package de.drolpi.dailyreward;

import de.drolpi.dailyreward.commands.RewardCommand;
import de.drolpi.dailyreward.inventory.RewardInventory;
import de.drolpi.dailyreward.listener.PlayerClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DailyReward extends JavaPlugin {

    private RewardInventory rewardInventory;

    @Override
    public void onEnable() {
        this.rewardInventory = new RewardInventory();

        Bukkit.getPluginCommand("reward").setExecutor(new RewardCommand(rewardInventory));
        Bukkit.getPluginManager().registerEvents(new PlayerClickListener(rewardInventory), this);
    }

    @Override
    public void onDisable() {
        rewardInventory.getRewardProvider().getRewardStorage().save();
    }
}
