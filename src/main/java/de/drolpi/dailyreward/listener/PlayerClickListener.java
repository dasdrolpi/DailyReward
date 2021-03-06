package de.drolpi.dailyreward.listener;

import de.drolpi.dailyreward.DailyRewardPlugin;
import de.drolpi.dailyreward.inventory.RewardInventory;
import de.drolpi.dailyreward.storage.RewardStorage;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerClickListener implements Listener {

    private final RewardInventory rewardInventory;
    private final RewardStorage rewardStorage;

    public PlayerClickListener(DailyRewardPlugin plugin) {
        this.rewardInventory = plugin.rewardInventory();
        this.rewardStorage = plugin.rewardStorage();
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        var item = event.getCurrentItem();

        if (item == null || !item.hasItemMeta())
            return;

        var itemMeta = item.getItemMeta();

        if (itemMeta == null || !itemMeta.hasDisplayName())
            return;

        var title = event.getView().title();

        if (!title.equals(Component.text("§cRewards")))
            return;

        for (var result : this.rewardStorage.playerRewards(player)) {
            var rewardType = result.rewardType();
            if (rewardType.slot() == event.getSlot()) {

                if (isReady(result.timeStamp())) {
                    player.sendMessage(prefix() + "§7Du hast §e" + rewardType.coins() + " §7Coins erhalten!");
                    this.rewardStorage.resetReward(player, rewardType);
                } else {
                    player.sendMessage(prefix() + "§7Du kannst diese Belohnung noch §cnicht §7Abholen!");
                }

                this.rewardInventory.buildInventory(this.rewardInventory.inventory(player), player);
                player.updateInventory();
            }
        }

        event.setCancelled(true);
    }

    private boolean isReady(long millis) {
        return System.currentTimeMillis() >= millis;
    }

    private String prefix() {
        return "§cReward §8» §7";
    }

}
