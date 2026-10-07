package com.waqas.notifications;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public final class DeliveryPolicy {
    public enum Channel { EMAIL, SMS, PUSH }
    public record Message(String recipient, String subject, String body, List<Channel> preferredChannels) {
        public Message {
            if (recipient == null || recipient.isBlank() || subject == null || body == null) throw new IllegalArgumentException("message fields are required");
            preferredChannels = List.copyOf(preferredChannels);
            if (preferredChannels.isEmpty()) throw new IllegalArgumentException("at least one channel is required");
        }
    }
    public record Attempt(int number, Instant notBefore, Channel channel) { }

    public Attempt nextAttempt(Message message, int previousFailures, Instant now) {
        Objects.requireNonNull(now, "now is required");
        if (previousFailures < 0 || previousFailures >= 5) throw new IllegalArgumentException("retry budget exhausted");
        long delaySeconds = previousFailures == 0 ? 0 : Duration.ofSeconds(5).multipliedBy(1L << (previousFailures - 1)).toSeconds();
        var channel = message.preferredChannels().get(previousFailures % message.preferredChannels().size());
        return new Attempt(previousFailures + 1, now.plusSeconds(delaySeconds), channel);
    }
}
