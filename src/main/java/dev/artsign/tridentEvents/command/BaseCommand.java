package dev.artsign.tridentEvents.command;

import dev.artsign.tridentEvents.manager.EventManager;
import dev.artsign.tridentEvents.manager.MessageManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BaseCommand implements CommandExecutor {

    EventManager eventManager;
    MessageManager messageManager;

    MiniMessage MM = MiniMessage.miniMessage();

    public BaseCommand(EventManager eventManager, MessageManager messageManager) {
        this.eventManager = eventManager;
        this.messageManager = messageManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            return false;
        } // Only players can execute commands (FOR NOW)

        if (args.length == 0) {
            player.sendMessage(MM.deserialize("<#ff2056>✘ You must specify a subcommand"));
            return false;
        }

        if (args[0].equals("start")) {
            Title showTitle = Title.title(
                    messageManager.getMessage("event.start.title"),
                    messageManager.getMessage("event.start.subtitle")
            );

            for (Player target : Bukkit.getOnlinePlayers()) {
                target.showTitle(showTitle);
            }

            return false;
        }

        if (args[0].equals("debug")) {
            player.sendMessage("DEBUG INFORMATION");
            player.sendMessage("EVENT STATE: " + eventManager.getEventState());
            return false;
        }

        return false;
    }
}
