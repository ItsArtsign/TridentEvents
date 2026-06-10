package dev.artsign.tridentEvents.command;

import dev.artsign.tridentEvents.manager.EventManager;
import dev.artsign.tridentEvents.manager.KitManager;
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
    KitManager kitManager;

    MiniMessage MM = MiniMessage.miniMessage();

    public BaseCommand(EventManager eventManager, MessageManager messageManager, KitManager kitManager) {
        this.eventManager = eventManager;
        this.messageManager = messageManager;
        this.kitManager = kitManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            return false;
        } // Only players can execute commands (FOR NOW)

        if (args.length == 0) {
            player.sendMessage(messageManager.getMessage("debug.error-subcommand"));
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

        if (args[0].equals("reload")){
            messageManager.reload();
            player.sendMessage(messageManager.getMessage("debug.reloaded"));
        }

        if (args[0].equals("kit")){

            if (args.length == 1){
                player.sendMessage(MM.deserialize("<#ff2056>✘ You must specify a subcommand"));
                return false;
            }

            if (args[1].equals("get")){
                if (args.length != 3){
                    player.sendMessage(messageManager.getMessage("event.kit.invalid"));
                    return false;
                }
                String name = args[2];
                kitManager.giveKit(player, name, true);
                return false;
            }
            if (args[1].equals("save")){
                if (args.length != 3){
                    player.sendMessage(messageManager.getMessage("event.kit.invalid"));
                    return false;
                }
                String name = args[2];
                kitManager.saveKit(player, name);
                return false;
            }
            if (args[1].equals("delete")){ // TODO: Fix this
                if (args.length != 3){
                    player.sendMessage(messageManager.getMessage("event.kit.invalid"));
                    return false;
                }
                String name = args[2];
                kitManager.deleteKit(player, name);
                return false;
            }
            if (args[1].equals("list")){
                player.sendMessage("Kits: " + kitManager.getKits());
                return false; // Patched
            }



            player.sendMessage(messageManager.getMessage("debug.error-subcommand")); // Invalid subcommand
            return false; // Patched

        }

        player.sendMessage(messageManager.getMessage("debug.error-subcommand")); // Invalid subcommand

        return false;
    }
}
