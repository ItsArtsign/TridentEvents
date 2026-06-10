package dev.artsign.tridentEvents.placeholder;

import dev.artsign.tridentEvents.manager.EventManager;
import dev.artsign.tridentEvents.manager.MessageManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EventExpansion extends PlaceholderExpansion {

    EventManager eventManager;
    MessageManager messageManager;

    public EventExpansion(EventManager eventManager, MessageManager messageManager){
        this.eventManager = eventManager;
        this.messageManager = messageManager;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "event";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TridentEvents";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {

        if (params.equals("state")){

            String stateMessage = messageManager.getMessageRaw("placeholders.states." + eventManager.getEventState());

            System.out.println(stateMessage);

            return stateMessage;
        }

        return "UNKNOWN PLACEHOLDER: " + params;
    }

}
