package test_with_remote_apis.methods;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.methods.response.reactions.ReactionsAddResponse;
import com.slack.api.methods.response.reactions.ReactionsGetResponse;
import com.slack.api.methods.response.reactions.ReactionsListResponse;
import com.slack.api.methods.response.reactions.ReactionsRemoveResponse;
import config.Constants;
import config.SlackTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class reactions_Test {

    static SlackTestConfig testConfig = SlackTestConfig.getInstance();
    static Slack slack = Slack.getInstance(testConfig.getConfig());

    @AfterClass
    public static void tearDown() throws InterruptedException {
        SlackTestConfig.awaitCompletion(testConfig);
    }

    String token = System.getenv(Constants.SLACK_SDK_TEST_USER_TOKEN);

    @Test
    public void test() throws IOException, SlackApiException {
        String channel = slack.methods().channelsList(r -> r.token(token).excludeArchived(true))
                .getChannels().get(0).getId();

        ChatPostMessageResponse postMessage = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(token)
                .channel(channel)
                .text("hello")
                .build());
        assertThat(postMessage.getError(), is(nullValue()));
        assertThat(postMessage.isOk(), is(true));

        String timestamp = postMessage.getTs();
        ReactionsAddResponse addResponse = slack.methods().reactionsAdd(r -> r
                .token(token)
                .name("smile")
                .channel(channel)
                .timestamp(timestamp));
        assertThat(addResponse.getError(), is(nullValue()));
        assertThat(addResponse.isOk(), is(true));

        ReactionsGetResponse getResponse = slack.methods().reactionsGet(r -> r
                .token(token)
                .channel(channel)
                .timestamp(timestamp));
        assertThat(getResponse.getError(), is(nullValue()));
        assertThat(getResponse.isOk(), is(true));

        ReactionsRemoveResponse removeResponse = slack.methods().reactionsRemove(r -> r
                .token(token)
                .name("smile")
                .channel(channel)
                .timestamp(timestamp));
        assertThat(removeResponse.getError(), is(nullValue()));
        assertThat(removeResponse.isOk(), is(true));
    }

    @Test
    public void test_async() throws ExecutionException, InterruptedException {
        String channel = slack.methodsAsync().conversationsList(r -> r.token(token).excludeArchived(true))
                .get().getChannels().get(0).getId();

        ChatPostMessageResponse postMessage = slack.methodsAsync().chatPostMessage(ChatPostMessageRequest.builder()
                .token(token)
                .channel(channel)
                .text("hello")
                .build())
                .get();
        assertThat(postMessage.getError(), is(nullValue()));
        assertThat(postMessage.isOk(), is(true));

        String timestamp = postMessage.getTs();
        ReactionsAddResponse addResponse = slack.methodsAsync().reactionsAdd(r -> r
                .token(token)
                .name("smile")
                .channel(channel)
                .timestamp(timestamp))
                .get();
        assertThat(addResponse.getError(), is(nullValue()));
        assertThat(addResponse.isOk(), is(true));

        ReactionsGetResponse getResponse = slack.methodsAsync().reactionsGet(r -> r
                .token(token)
                .channel(channel)
                .timestamp(timestamp))
                .get();
        assertThat(getResponse.getError(), is(nullValue()));
        assertThat(getResponse.isOk(), is(true));

        ReactionsRemoveResponse removeResponse = slack.methodsAsync().reactionsRemove(r -> r
                .token(token)
                .name("smile")
                .channel(channel)
                .timestamp(timestamp))
                .get();
        assertThat(removeResponse.getError(), is(nullValue()));
        assertThat(removeResponse.isOk(), is(true));
    }

    @Test
    public void list() throws IOException, SlackApiException {
        String user = slack.methods().usersList(r -> r.token(token))
                .getMembers().get(0).getId();

        ReactionsListResponse response = slack.methods().reactionsList(r -> r
                .token(token)
                .user(user));
        assertThat(response.getError(), is(nullValue()));
        assertThat(response.isOk(), is(true));
        assertThat(response.getItems(), is(notNullValue()));
    }

    @Test
    public void list_async() throws ExecutionException, InterruptedException {
        String user = slack.methodsAsync().usersList(r -> r.token(token))
                .get()
                .getMembers().get(0).getId();

        ReactionsListResponse response = slack.methodsAsync().reactionsList(r -> r
                .token(token)
                .user(user))
                .get();
        assertThat(response.getError(), is(nullValue()));
        assertThat(response.isOk(), is(true));
        assertThat(response.getItems(), is(notNullValue()));
    }

}
