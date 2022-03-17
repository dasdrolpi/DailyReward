package de.drolpi.dailyreward.inventory;

import de.drolpi.dailyreward.object.RewardObject;
import de.drolpi.dailyreward.object.RewardType;
import de.drolpi.dailyreward.provider.RewardProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

public class RewardInventory {

    private final Map<Player, Inventory> inventoryMap;
    private final RewardProvider rewardProvider;

    public RewardInventory() {
        this.inventoryMap = new WeakHashMap<>();
        this.rewardProvider = new RewardProvider();
    }

    public void buildInventory(Inventory inventory, Player player) {
        if(inventory == null) {
            inventory = Bukkit.createInventory(null, 5 * 9, "§cRewards");
        }

        inventory.setItem(4, buildItem(Material.GOLD_INGOT, (short) 0, "§cTägliche Belohnungen", ""));

        Collection<RewardObject> resultList = rewardProvider.getPlayerRewards(player);

        for (RewardObject result : resultList) {
            RewardType rewardType = result.getRewardType();
            inventory.setItem(rewardType.getSlot(), buildItem(Material.GOLD_INGOT, (short) 0, "§7● Coin Reward",
                    "§8» §7Diese Belohnung beinhaltet §e" + rewardType.getCoins() + " §7Coins",
                    (isAvailable(result.getTimeStamp()) ? "§8» §aVerfügbar" : "§8» §7Verfügbar am §c" + formatDate(result.getTimeStamp()))));
        }

        int[] slots = {0, 8, 18, 26, 27, 35, 36, 44};
        ItemStack glass = buildItem(Material.STAINED_GLASS_PANE, (short) 15, "§8 ");

        for (int i = 8; i < 18; i++) {
            inventory.setItem(i, glass);
        }
        for (int slot : slots) {
            inventory.setItem(slot, glass);
        }

        inventoryMap.put(player, inventory);
    }

    public Inventory getInventory(Player player) {
        return inventoryMap.get(player);
    }

    public static String formatDate(long dateMillis) {
        Date date = new Date(dateMillis);
        SimpleDateFormat timeZoneDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return timeZoneDate.format(date);
    }

    private boolean isAvailable(long millis) {
        return System.currentTimeMillis() >= millis;
    }

    private ItemStack buildItem(Material material, int subId, String display, String... loreList) {
        ItemStack itemStack = new ItemStack(material, 1, (short) subId);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(display);
        itemMeta.setLore(Arrays.stream(loreList).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public RewardProvider getRewardProvider() {
        return rewardProvider;
    }
}
