package de.drolpi.dailyreward.inventory;

import de.drolpi.dailyreward.DailyRewardPlugin;
import de.drolpi.dailyreward.object.RewardObject;
import de.drolpi.dailyreward.object.RewardType;
import de.drolpi.dailyreward.provider.RewardProvider;
import de.drolpi.dailyreward.util.DateUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

public class RewardInventory {

    private final RewardProvider rewardProvider;
    private final Map<Player, Inventory> cache;

    public RewardInventory(DailyRewardPlugin plugin) {
        this.rewardProvider = plugin.rewardProvider();
        this.cache = new WeakHashMap<>();
    }

    public void buildInventory(Inventory inventory, Player player) {
        if (inventory == null) {
            inventory = Bukkit.createInventory(null, 5 * 9, Component.text("§cRewards"));
        }

        inventory.setItem(4, buildItem(Material.GOLD_INGOT, "§cTägliche Belohnungen", ""));

        Collection<RewardObject> resultList = rewardProvider.playerRewards(player);

        for (RewardObject result : resultList) {
            RewardType rewardType = result.getRewardType();
            inventory.setItem(rewardType.getSlot(), buildItem(Material.GOLD_INGOT, "§7● Coin Reward",
                    "§8» §7Diese Belohnung beinhaltet §e" + rewardType.getCoins() + " §7Coins",
                    (isAvailable(result.getTimeStamp()) ? "§8» §aVerfügbar" : "§8» §7Verfügbar am §c" + DateUtil.formatDate(result.getTimeStamp()))));
        }

        int[] slots = {0, 8, 18, 26, 27, 35, 36, 44};
        ItemStack glass = buildItem(Material.GRAY_STAINED_GLASS_PANE, "§8 ");

        for (int i = 8; i < 18; i++) {
            inventory.setItem(i, glass);
        }
        for (int slot : slots) {
            inventory.setItem(slot, glass);
        }

        cache.put(player, inventory);
    }

    public Inventory inventory(Player player) {
        return cache.get(player);
    }

    private boolean isAvailable(long millis) {
        return System.currentTimeMillis() >= millis;
    }

    private ItemStack buildItem(Material material, String display, String... loreList) {
        ItemStack itemStack = new ItemStack(material, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(display));
        itemMeta.lore(Arrays.stream(loreList).map(Component::text).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
