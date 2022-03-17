package de.drolpi.dailyreward.commands;

import de.drolpi.dailyreward.inventory.RewardInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RewardCommand implements CommandExecutor {

    private final RewardInventory rewardInventory;

    public RewardCommand(RewardInventory rewardInventory) {
        this.rewardInventory = rewardInventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        final Player player = (Player) sender;
        if (args.length == 0) {

            rewardInventory.buildInventory(rewardInventory.getInventory(player), player);
            player.openInventory(rewardInventory.getInventory(player));

        }
        return false;
    }
}
