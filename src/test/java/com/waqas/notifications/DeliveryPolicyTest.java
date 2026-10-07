package com.waqas.notifications;

import java.time.Instant;
import java.util.List;

public final class DeliveryPolicyTest {
    public static void main(String[] args) {
        var policy = new DeliveryPolicy();
        var message = new DeliveryPolicy.Message("user@example.com", "Welcome", "Hello", List.of(DeliveryPolicy.Channel.EMAIL, DeliveryPolicy.Channel.SMS));
        var now = Instant.parse("2026-01-01T00:00:00Z");
        assert policy.nextAttempt(message, 0, now).channel() == DeliveryPolicy.Channel.EMAIL;
        assert policy.nextAttempt(message, 2, now).notBefore().equals(now.plusSeconds(10));
    }
}
