package de.drolpi.dailyreward.listener;

import de.drolpi.dailyreward.inventory.RewardInventory;
import de.drolpi.dailyreward.object.RewardObject;
import de.drolpi.dailyreward.object.RewardType;
import de.drolpi.dailyreward.provider.RewardProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PlayerClickListener implements Listener {

    private final RewardInventory rewardInventory;
    private final RewardProvider rewardProvider;

    public PlayerClickListener(RewardInventory rewardInventory) {
        this.rewardInventory = rewardInventory;
        this.rewardProvider = rewardInventory.getRewardProvider();
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {

        if(!(event.getWhoClicked() instanceof Player)) return;

        if(event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() &&
                event.getCurrentItem().getItemMeta().hasDisplayName() && event.getInventory().getName().equals("§cRewards")) {

            final Player player = (Player) event.getWhoClicked();

            for (RewardObject result : rewardProvider.getPlayerRewards(player)) {
                RewardType rewardType = result.getRewardType();
                if(rewardType.getSlot() == event.getSlot()) {

                    if (isReady(result.getTimeStamp())) {
                        player.sendMessage(sendPrefix() + "§7Du hast §e" + rewardType.getCoins() + " §7Coins erhalten!");
                        rewardProvider.resetReward(player, rewardType);
                    } else {
                        player.sendMessage(sendPrefix() + "§7Du kannst diese Belohnung noch §cnicht §7Abholen!");
                    }

                    rewardInventory.buildInventory(rewardInventory.getInventory(player), player);
                    player.updateInventory();
                }
            }

            event.setCancelled(true);

        }
    }

    private boolean isReady(long millis) {
        return System.currentTimeMillis() >= millis;
    }

    private String sendPrefix() {
        return "§cReward §8» §7";
    }

}
