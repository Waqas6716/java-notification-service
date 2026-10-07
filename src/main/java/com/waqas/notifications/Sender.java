package com.waqas.notifications;

public interface Sender {
    DeliveryResult send(DeliveryPolicy.Message message, DeliveryPolicy.Channel channel);
    record DeliveryResult(boolean accepted, String providerMessageId) { }
}
