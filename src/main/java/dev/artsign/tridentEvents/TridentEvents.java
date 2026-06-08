package dev.artsign.tridentEvents;

import org.bukkit.plugin.java.JavaPlugin;

public final class TridentEvents extends JavaPlugin {

    /*

    @-- Planning

    - Spawning
        We will need to have options in the config for things like set locations, random spawning,
        grid-spawning, circle spawning, etc. Also options to freeze players and to allow/dissallow inventory
        events or blocks for things like inventory management.

    - Kits
        Predefined kits and with NBT saving and support for custom items etc (I don't know how to do this yet)

    - Loot crates
        Spawn lootcrates around the world (Probably just will detect if som1 tries to open a chest, and we can use
        presistent data container since it's a block entity to see if it's already opened)

    - Map resetting
        Idk

    - Chatting
        You should be able to talk in a regular event chat, and also a /shout command that is distinguishable from
        the other chat messages (Maybe newlines before and after) with a permission and a cooldown (Maybe permission-based cooldowns?)

    - Teams
        Final feature to plan before I start coding is a teams feature, with options for predefined, red vs blue, random, etc.
        Teams will show prefixed in chat (maybe using %event_team% or something like that) and also joinable with commands maybe?

     */



    @Override
    public void onEnable() {
        // Enable things shall go here.
    }


    // onDisable has been removed for cleanlines

}
