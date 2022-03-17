package de.drolpi.dailyreward.listener;

import de.drolpi.dailyreward.DailyRewardPlugin;
import de.drolpi.dailyreward.inventory.RewardInventory;
import de.drolpi.dailyreward.object.RewardObject;
import de.drolpi.dailyreward.object.RewardType;
import de.drolpi.dailyreward.provider.RewardProvider;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerClickListener implements Listener {

    private final RewardInventory rewardInventory;
    private final RewardProvider rewardProvider;

    public PlayerClickListener(DailyRewardPlugin plugin) {
        this.rewardInventory = plugin.rewardInventory();
        this.rewardProvider = plugin.rewardProvider();
    }

    //TODO: Improve

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() &&
                event.getCurrentItem().getItemMeta().hasDisplayName() && event.getView().title().equals(Component.text("§cRewards"))) {

            for (RewardObject result : rewardProvider.playerRewards(player)) {
                RewardType rewardType = result.rewardType();
                if (rewardType.slot() == event.getSlot()) {

                    if (isReady(result.timeStamp())) {
                        player.sendMessage(sendPrefix() + "§7Du hast §e" + rewardType.coins() + " §7Coins erhalten!");
                        rewardProvider.resetReward(player, rewardType);
                    } else {
                        player.sendMessage(sendPrefix() + "§7Du kannst diese Belohnung noch §cnicht §7Abholen!");
                    }

                    rewardInventory.buildInventory(rewardInventory.inventory(player), player);
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
