package dev.artsign.tridentEvents.event;

import dev.artsign.tridentEvents.manager.MessageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class DeathListener implements Listener {

    final MessageManager messageManager;

    public DeathListener(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent e) {
        e.deathMessage(messageManager.getMessage("event.death", List.of(e.getPlayer().getName())));
    }
}
