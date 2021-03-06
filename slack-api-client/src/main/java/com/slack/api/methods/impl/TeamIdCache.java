package com.slack.api.methods.impl;

import com.slack.api.SlackConfig;
import com.slack.api.methods.Methods;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.auth.AuthTestRequest;
import com.slack.api.methods.response.auth.AuthTestResponse;
import com.slack.api.util.http.SlackHttpClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static com.slack.api.methods.RequestFormBuilder.toForm;

@Slf4j
public class TeamIdCache {

    private static final ConcurrentMap<String, String> TOKEN_TO_TEAM_ID = new ConcurrentHashMap<>();

    private final MethodsClientImpl methodsImpl;

    public TeamIdCache(SlackConfig config) {
        this(new MethodsClientImpl(buildHttpClient(config)));
    }

    public TeamIdCache(MethodsClientImpl methodsImpl) {
        this.methodsImpl = methodsImpl;
    }

    private static SlackHttpClient buildHttpClient(SlackConfig config) {
        SlackHttpClient httpClient = new SlackHttpClient();
        httpClient.setConfig(config);
        return httpClient;
    }

    public String lookupOrResolve(String token) {
        return lookupOrResolve(token, (newToken) -> {
            try {
                FormBody.Builder form = toForm(AuthTestRequest.builder().token(newToken).build());
                Response response = methodsImpl.runPostFormWithToken(form, Methods.AUTH_TEST, token);
                AuthTestResponse authTest = methodsImpl.parseJsonResponseAndRunListeners(null, null, response, AuthTestResponse.class);
                if (authTest.isOk()) {
                    return authTest.getTeamId();
                } else {
                    log.error("Got an unsuccessful response from auth.test API (error: {})", authTest.getError());
                }
            } catch (IOException | SlackApiException e) {
                log.error("Failed to call auth.test API (error: {})", e.getMessage(), e);
            }
            return null;
        });
    }

    private static String lookupOrResolve(String token, Function<String, String> compute) {
        if (token == null) {
            return null;
        } else {
            return TOKEN_TO_TEAM_ID.computeIfAbsent(token, compute);
        }
    }

}