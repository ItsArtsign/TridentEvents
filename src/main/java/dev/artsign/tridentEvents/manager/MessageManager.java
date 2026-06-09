package dev.artsign.tridentEvents.manager;

import dev.artsign.tridentEvents.TridentEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageManager {

    TridentEvents plugin;
    File messageFile;
    YamlConfiguration messageConfig;

    public MessageManager(TridentEvents plugin) {
        this.plugin = plugin;
        this.messageFile = new File(plugin.getDataFolder(), "messages.yml");
        this.messageConfig = YamlConfiguration.loadConfiguration(messageFile);
    }

    public Component getMessage(String path) {
        String value = messageConfig.getString(path);

        return MiniMessage.miniMessage().deserialize(value);
    }

}
