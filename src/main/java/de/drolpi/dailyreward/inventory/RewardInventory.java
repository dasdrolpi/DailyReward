package de.drolpi.dailyreward.inventory;

import de.drolpi.dailyreward.DailyRewardPlugin;
import de.drolpi.dailyreward.storage.RewardStorage;
import de.drolpi.dailyreward.util.DateUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

public class RewardInventory {

    private final RewardStorage rewardStorage;
    private final Map<Player, Inventory> cache;

    public RewardInventory(DailyRewardPlugin plugin) {
        this.rewardStorage = plugin.rewardStorage();
        this.cache = new WeakHashMap<>();
    }

    public void buildInventory(Inventory inventory, Player player) {
        if (inventory == null) {
            inventory = Bukkit.createInventory(null, 5 * 9, Component.text("§cRewards"));
        }

        inventory.setItem(4, buildItem(Material.GOLD_INGOT, "§cTägliche Belohnungen", ""));

        var rewards = this.rewardStorage.playerRewards(player);

        for (var result : rewards) {
            var rewardType = result.rewardType();
            inventory.setItem(rewardType.slot(), buildItem(
                Material.GOLD_INGOT,
                "§7● Coin Reward",
                "§8» §7Diese Belohnung beinhaltet §e" + rewardType.coins() + " §7Coins",
                (isAvailable(result.timeStamp()) ? "§8» §aVerfügbar" : "§8» §7Verfügbar am §c" + DateUtil.formatDate(result.timeStamp()))
            ));
        }

        int[] slots = {0, 8, 18, 26, 27, 35, 36, 44};
        var glass = buildItem(Material.GRAY_STAINED_GLASS_PANE, "§8 ");

        for (var i = 8; i < 18; i++) {
            inventory.setItem(i, glass);
        }
        for (var slot : slots) {
            inventory.setItem(slot, glass);
        }

        this.cache.put(player, inventory);
    }

    public Inventory inventory(Player player) {
        return this.cache.get(player);
    }

    private boolean isAvailable(long millis) {
        return System.currentTimeMillis() >= millis;
    }

    private ItemStack buildItem(Material material, String display, String... loreList) {
        var itemStack = new ItemStack(material, 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(display));
        itemMeta.lore(Arrays.stream(loreList).map(Component::text).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
