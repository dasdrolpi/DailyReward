package de.drolpi.dailyreward.storage;

import de.drolpi.dailyreward.controller.FileController;
import de.drolpi.dailyreward.object.RewardPlayer;
import org.bukkit.entity.Player;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RewardStorage {

    private final static Path PATH = Paths.get("plugins", "dailyreward", "rewardsstorage.json");
    private final Map<UUID, RewardPlayer> playerContainer;

    public RewardStorage() {
        this.playerContainer = new HashMap<>();
    }

    public static RewardStorage load() {
        FileController fileController = new FileController();

        if(Files.exists(PATH)) {
            return fileController.loadFromJson(PATH, RewardStorage.class);
        }else {
            RewardStorage instance = new RewardStorage();
            instance.save();
            return instance;
        }
    }

    public void createPlayer(Player player) {
        playerContainer.put(player.getUniqueId(), new RewardPlayer(player.getUniqueId()));
    }

    public RewardPlayer getPlayer(Player player) {
        if(!playerContainer.containsKey(player.getUniqueId())) {
            createPlayer(player);
        }
        return playerContainer.get(player.getUniqueId());
    }

    public void save() {
        FileController fileController = new FileController();

        if(!Files.exists(PATH)) {
            fileController.createDirectory(PATH.getParent().toFile());
        }

        fileController.saveToJson(this, PATH);
    }
}
