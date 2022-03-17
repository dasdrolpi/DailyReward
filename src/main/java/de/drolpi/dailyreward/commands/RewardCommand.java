package de.drolpi.dailyreward.commands;

import de.drolpi.dailyreward.DailyRewardPlugin;
import de.drolpi.dailyreward.inventory.RewardInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RewardCommand implements CommandExecutor {

    private final RewardInventory rewardInventory;

    public RewardCommand(DailyRewardPlugin plugin) {
        this.rewardInventory = plugin.rewardInventory();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) {

            rewardInventory.buildInventory(rewardInventory.inventory(player), player);
            player.openInventory(rewardInventory.inventory(player));
        }
        return false;
    }
}
