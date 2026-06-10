package dev.artsign.tridentEvents.util;

import dev.artsign.tridentEvents.manager.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    KitManager kitManager;

    public TabCompleter(KitManager kitManager){
        this.kitManager = kitManager;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length == 1) {
            return completeRoot(args[0]);
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("kit")) {
            return completeKit(args[1]);
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("kit")){
            return completeListKits(args[2]);
        }


        return Collections.emptyList();
    }

    private List<String> filter(String input, List<String> options) {
        return options.stream()
                .filter(s -> s.toLowerCase().startsWith(input.toLowerCase()))
                .sorted()
                .toList();
    }

    private static final List<String> ROOT_COMMANDS = List.of(
            "start",
            "reload",
            "debug",
            "kit"
    );

    private static final List<String> KIT_COMMANDS = List.of(
            "save",
            "get",
            "delete",
            "list"
    );

    private List<String> completeKit(String input){
        return filter(input, KIT_COMMANDS);
    }

    private List<String> completeRoot(String input) {
        return filter(input, ROOT_COMMANDS);
    }

    private List<String> completeListKits(String input){
        return filter(input, kitManager.getKits());
    }


}
