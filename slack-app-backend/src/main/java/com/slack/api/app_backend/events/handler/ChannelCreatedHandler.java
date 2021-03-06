package com.slack.api.app_backend.events.handler;

import com.slack.api.model.event.ChannelCreatedEvent;
import com.slack.api.app_backend.events.EventHandler;
import com.slack.api.app_backend.events.payload.ChannelCreatedPayload;

public abstract class ChannelCreatedHandler extends EventHandler<ChannelCreatedPayload> {

    @Override
    public String getEventType() {
        return ChannelCreatedEvent.TYPE_NAME;
    }
}
