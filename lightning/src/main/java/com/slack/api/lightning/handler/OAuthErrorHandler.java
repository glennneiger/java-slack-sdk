package com.slack.api.lightning.handler;

import com.slack.api.lightning.request.builtin.OAuthCallbackRequest;
import com.slack.api.lightning.response.Response;

@FunctionalInterface
public interface OAuthErrorHandler {

    Response handle(OAuthCallbackRequest request, Response response);

}
