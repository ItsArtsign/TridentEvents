package dev.artsign.tridentEvents.manager;

import dev.artsign.tridentEvents.util.EventState;

public class EventManager {

    EventState eventState = EventState.INACTIVE; // Default Event State

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState setState) {
        eventState = setState;
    }


}
