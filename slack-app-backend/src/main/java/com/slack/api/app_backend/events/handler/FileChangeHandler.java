package com.slack.api.app_backend.events.handler;

import com.slack.api.model.event.FileChangeEvent;
import com.slack.api.app_backend.events.EventHandler;
import com.slack.api.app_backend.events.payload.FileChangePayload;

public abstract class FileChangeHandler extends EventHandler<FileChangePayload> {

    @Override
    public String getEventType() {
        return FileChangeEvent.TYPE_NAME;
    }
}
