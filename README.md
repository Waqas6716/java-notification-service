# Notification Dispatcher

A Java 17 module that demonstrates reliable notification delivery policy: channel preference, exponential retry backoff, and a testable clock. Infrastructure adapters can implement the included `Sender` interface for email, SMS, or push providers.

```bash
javac -d out $(find src -name '*.java')
java -ea -cp out com.waqas.notifications.DeliveryPolicyTest
```
