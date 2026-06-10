package dev.artsign.tridentEvents.util;

public enum EventState {

    STARTING_SOON, // To alert players that an event is starting soon

    JOINING, // Players can now join the event
    PREPARING, //  Giving kits, resetting map, etc

    GRACE_PERIOD, // PVP Is disabled but players can move and interact
    MAIN_PHASE, // PVP Enabled, Players can move around and interact

    DEATHMATCH, // Border Shrinks Rapidly. All other features remain unchanged

    ENDING, // Post-processing
    ENDED, //  Event has concluded

    INACTIVE //  No active event

}
