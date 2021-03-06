package com.slack.api.app_backend.events.handler;

import com.slack.api.model.event.SubteamUpdatedEvent;
import com.slack.api.app_backend.events.EventHandler;
import com.slack.api.app_backend.events.payload.SubteamUpdatedPayload;

public abstract class SubteamUpdatedHandler extends EventHandler<SubteamUpdatedPayload> {

    @Override
    public String getEventType() {
        return SubteamUpdatedEvent.TYPE_NAME;
    }
}
