package com.slack.api.app_backend.events.handler;

import com.slack.api.model.event.ChannelHistoryChangedEvent;
import com.slack.api.app_backend.events.EventHandler;
import com.slack.api.app_backend.events.payload.ChannelHistoryChangedPayload;

public abstract class ChannelHistoryChangedHandler extends EventHandler<ChannelHistoryChangedPayload> {

    @Override
    public String getEventType() {
        return ChannelHistoryChangedEvent.TYPE_NAME;
    }
}
