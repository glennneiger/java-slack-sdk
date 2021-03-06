package com.slack.api.methods;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://api.slack.com/docs/rate-limits"
 */
public enum MethodsRateLimitTier {

    /**
     * 1+ per minute
     * <p>
     * Access tier 1 methods infrequently. A small amount of burst behavior is tolerated.
     */
    Tier1,

    /**
     * 20+ per minute
     * <p>
     * Most methods allow at least 20 requests per minute, while allowing for occasional bursts of more requests.
     */
    Tier2,

    /**
     * 50+ per minute
     * <p>
     * Tier 3 methods allow a larger number of requests and are typically attached to methods with paginating collections of conversations or users. Sporadic bursts are welcome.
     */
    Tier3,

    /**
     * 100+ per minute
     * <p>
     * Enjoy a large request quota for Tier 4 methods, including generous burst behavior.
     */
    Tier4,

    /**
     * This method allows hundreds of requests per minute. Use it as often as is reasonably required.
     */
    SpecialTier_auth_test,

    /**
     * chat.postMessage has special rate limiting conditions.
     * It will generally allow an app to post 1 message per second to a specific channel.
     * There are limits governing your app's relationship with the entire workspace above that,
     * limiting posting to several hundred messages per minute. Generous burst behavior is also granted.
     */
    SpecialTier_chat_postMessage,

    /**
     * This method allows hundreds of requests per minute. Use it as often as is reasonably required.
     */
    SpecialTier_chat_getPermalink;

    // --------------------------------------------------------------------------------------------

    private static final Map<MethodsRateLimitTier, Integer> allowedRequestsPerMinute = new HashMap<>();

    static {
        allowedRequestsPerMinute.put(MethodsRateLimitTier.Tier1, 1);
        allowedRequestsPerMinute.put(MethodsRateLimitTier.Tier2, 20);
        allowedRequestsPerMinute.put(MethodsRateLimitTier.Tier3, 50);
        allowedRequestsPerMinute.put(MethodsRateLimitTier.Tier4, 100);
        allowedRequestsPerMinute.put(MethodsRateLimitTier.SpecialTier_auth_test, 600);
        allowedRequestsPerMinute.put(MethodsRateLimitTier.SpecialTier_chat_getPermalink, 600);
        allowedRequestsPerMinute.put(MethodsRateLimitTier.SpecialTier_chat_postMessage, 60); // per channel
    }

    public static Integer getAllowedRequestsPerMinute(MethodsRateLimitTier tier) {
        return allowedRequestsPerMinute.get(tier);
    }

}
