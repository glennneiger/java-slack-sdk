package samples;

import com.slack.api.lightning.App;
import com.slack.api.lightning.AppConfig;
import com.slack.api.lightning.middleware.builtin.LegacyRequestVerification;
import com.slack.api.lightning.middleware.builtin.SingleTeamAuthorization;
import lombok.extern.slf4j.Slf4j;
import samples.util.ResourceLoader;
import samples.util.TestSlackAppServer;

import java.util.Arrays;

@Slf4j
public class OutgoingWebhooksSample {

    public static void main(String[] args) throws Exception {
        AppConfig config = ResourceLoader.loadAppConfig();
        // https://github.com/seratch/jslack/blob/master/jslack-lightning/src/test/resources/
        String verificationToken = ResourceLoader.load("verificationToken.txt");
        App app = new App(config, Arrays.asList(
                // x-slack-signature unsupported
                new LegacyRequestVerification(verificationToken),
                new SingleTeamAuthorization(config, null)
        ));

        app.webhook("something", (req, ctx) -> {
            log.info("outgoing webhook - {}", req);
            return ctx.ack(r -> r.text("Hello " + req.getPayload().getUserName()));
        });
        TestSlackAppServer server = new TestSlackAppServer(app);
        server.start();
    }

}
