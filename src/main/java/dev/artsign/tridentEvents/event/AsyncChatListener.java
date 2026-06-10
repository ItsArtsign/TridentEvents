package dev.artsign.tridentEvents.event;

import dev.artsign.tridentEvents.manager.MessageManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class AsyncChatListener implements Listener {

    MessageManager messageManager;

    public AsyncChatListener(MessageManager messageManager){
        this.messageManager = messageManager;
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent e) {

        e.setCancelled(true);

        Player player = e.getPlayer();

        String message = PlainTextComponentSerializer.plainText().serialize(e.message());

        for (Player target : Bukkit.getOnlinePlayers()){
            target.sendMessage(messageManager.getMessage("event.chat.format", List.of(player.getName(), message)));
        }



    }


}
