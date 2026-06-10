package dev.artsign.tridentEvents.manager;

import dev.artsign.tridentEvents.TridentEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class MessageManager {

    final File messageFile;
    YamlConfiguration messageConfig;

    public MessageManager(TridentEvents plugin) {
        this.messageFile = new File(plugin.getDataFolder(), "messages.yml");
        this.messageConfig = YamlConfiguration.loadConfiguration(messageFile);
    }

    public Component getMessage(String path) {
        String value = messageConfig.getString(path);

        return MiniMessage.miniMessage().deserialize(value);
    }

    public Component getMessage(String path, List<String> placeholders) {
        String value = messageConfig.getString(path);

        for (int i = 0; i < placeholders.size(); i++) {
            value = value.replace("{" + i + "}", placeholders.get(i));
        }

        return MiniMessage.miniMessage().deserialize(value);
    }

    public String getMessageRaw(String path) {
        String value = messageConfig.getString(path);
        return value;
    }

    public void reload() {
        this.messageConfig = YamlConfiguration.loadConfiguration(messageFile);
    }

}
