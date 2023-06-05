package s.m.learn.consumer;

import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ApplicationScoped
public class DataConsumer {
    private final AtomicInteger consumerOneCount = new AtomicInteger();
    private final AtomicInteger consumerTwoCount = new AtomicInteger();
    private final Random random = new Random();

    @ConsumeEvent("data-input-channel")
    public String consumeOne(final String data) {
        log.info("consumerOne received data : {}", data);
        if(true) throw new RuntimeException(String.format("error consuming : %s", data));
        log.info("consumerOne consumed count : {}", consumerOneCount.incrementAndGet());
        return "data " + data;
    }

    @ConsumeEvent("data-input-channel")
    public String consumeTwo(final String data) {
        log.info("consumeTwo received data : {}", data);
        log.info("consumerOne consumed count : {}", consumerTwoCount.incrementAndGet());
        return "data " + data;
    }

    private boolean simulateError(){
        return true;
    }
}
