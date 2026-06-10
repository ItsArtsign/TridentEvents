package dev.artsign.tridentEvents;

import dev.artsign.tridentEvents.command.BaseCommand;
import dev.artsign.tridentEvents.manager.EventManager;
import dev.artsign.tridentEvents.manager.KitManager;
import dev.artsign.tridentEvents.manager.MessageManager;
import dev.artsign.tridentEvents.placeholder.EventExpansion;
import dev.artsign.tridentEvents.util.TabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TridentEvents extends JavaPlugin {

    /*

    @-- Planning

    - Spawning
        We will need to have options in the config for things like set locations, random spawning,
        grid-spawning, circle spawning, etc. Also options to freeze players and to allow/dissallow inventory
        events or blocks for things like inventory management.

    - Kits
        Predefined kits and with NBT saving and support for custom items etc. (I don't know how to do this yet)

    - Loot crates
        Spawn lootcrates around the world (Probably just will detect if som1 tries to open a chest, and we can use
        presistent data container since it's a block entity to see if it's already opened)

    - Map resetting
        Will most likely just require FAWE and save the world as a schematic, or save each chunk or something. Alternitave 2 is we track
        every modified block and manually reset it. We wouldn't want to do this in memory for performance reasons though, so this would
        require a database potentially.

    - Chatting
        You should be able to talk in a regular event chat, and also a /shout command that is distinguishable from
        the other chat messages (Maybe newlines before and after) with a permission and a cooldown (Maybe permission-based cooldowns?)

    - Teams
        Final feature to plan before I start coding is a teams feature, with options for predefined, red vs blue, random, etc.
        Teams will show prefixed in chat (maybe using %event_team% or something like that) and also joinable with commands maybe?

     */


    @Override
    public void onEnable() {

        if (!(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))){
            getLogger().warning("PlaceholderAPI is required for this plugin to run. TridentEvents will shut down now.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        saveResource("messages.yml", true); // SET TO FALSE FOR PRODUCTION

        EventManager eventManager = new EventManager();
        MessageManager messageManager = new MessageManager(this);
        KitManager kitManager = new KitManager(this, messageManager);

        new EventExpansion(eventManager, messageManager).register();

        getCommand("event").setExecutor(new BaseCommand(eventManager, messageManager, kitManager));
        getCommand("event").setTabCompleter(new TabCompleter(kitManager));
    }


    // onDisable has been removed for now.

}
