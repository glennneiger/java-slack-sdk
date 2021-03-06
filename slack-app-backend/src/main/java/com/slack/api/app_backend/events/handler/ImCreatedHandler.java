package com.slack.api.app_backend.events.handler;

import com.slack.api.model.event.ImCreatedEvent;
import com.slack.api.app_backend.events.EventHandler;
import com.slack.api.app_backend.events.payload.ImCreatedPayload;

public abstract class ImCreatedHandler extends EventHandler<ImCreatedPayload> {

    @Override
    public String getEventType() {
        return ImCreatedEvent.TYPE_NAME;
    }
}
