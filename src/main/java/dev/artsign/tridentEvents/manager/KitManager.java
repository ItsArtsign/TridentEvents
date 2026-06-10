package dev.artsign.tridentEvents.manager;

import dev.artsign.tridentEvents.TridentEvents;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KitManager {

    final TridentEvents plugin;
    final MessageManager messageManager;

    public KitManager(TridentEvents plugin, MessageManager messageManager) {
        this.plugin = plugin;
        this.messageManager = messageManager;
    }

    public void saveKit(Player player, String name) {

        File file = new File(plugin.getDataFolder(), "kits/" + name + ".yml");

        YamlConfiguration config = new YamlConfiguration();

        config.set("contents", player.getInventory().getContents());
        config.set("armor", player.getInventory().getArmorContents());
        config.set("offhand", player.getInventory().getItemInOffHand());

        try {
            config.save(file);
            player.sendMessage(messageManager.getMessage("event.kit.saved", List.of(name)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void giveKit(Player player, String name, Boolean overwrite) {

        File file = new File(plugin.getDataFolder(), "kits/" + name + ".yml");

        if (!file.exists()) {
            player.sendMessage(messageManager.getMessage("event.kit.invalid", List.of(name)));
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (overwrite) {
            player.getInventory().clear();
        }

        List<?> contentsList = config.getList("contents");
        if (contentsList != null) {
            player.getInventory().setContents(
                    contentsList.toArray(new ItemStack[0])
            );
        }

        List<?> armorList = config.getList("armor");
        if (armorList != null) {
            player.getInventory().setArmorContents(
                    armorList.toArray(new ItemStack[0])
            );
        }

        ItemStack offhand = config.getItemStack("offhand");
        player.getInventory().setItemInOffHand(offhand);

        player.sendMessage(messageManager.getMessage("event.kit.given", List.of(name)));

    }

    public void deleteKit(Player player, String name) {
        // Check for file, delete file, send message
        File file = new File(plugin.getDataFolder(), "kits/" + name + ".yml");

        if (!file.exists()) {
            return;
        }

        boolean succeeded = file.delete();

        if (succeeded) {
            player.sendMessage(messageManager.getMessage("event.kit.deleted", List.of(name)));
        } else {
            player.sendMessage(messageManager.getMessage("event.kit.invalid", List.of(name)));
        }

    }

    public List<String> getKits() {
        File kitsFolder = new File(plugin.getDataFolder(), "kits");

        if (!kitsFolder.exists() || !kitsFolder.isDirectory()) {
            return Collections.emptyList();
        }

        File[] files = kitsFolder.listFiles();

        if (files == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .map(File::getName)
                .toList();
    }

}
