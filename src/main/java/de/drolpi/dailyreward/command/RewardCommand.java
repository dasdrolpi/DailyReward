package de.drolpi.dailyreward.command;

import de.drolpi.dailyreward.DailyRewardPlugin;
import de.drolpi.dailyreward.inventory.RewardInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RewardCommand implements CommandExecutor {

    private final RewardInventory rewardInventory;

    public RewardCommand(DailyRewardPlugin plugin) {
        this.rewardInventory = plugin.rewardInventory();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player))
            return true;

        if (args.length != 0)
            return false;

        this.rewardInventory.buildInventory(this.rewardInventory.inventory(player), player);
        player.openInventory(this.rewardInventory.inventory(player));

        return true;
    }
}
