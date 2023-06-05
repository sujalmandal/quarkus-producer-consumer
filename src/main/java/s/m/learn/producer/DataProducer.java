package s.m.learn.producer;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ApplicationScoped
public class DataProducer {
    @Inject
    EventBus eventBus;

    private final AtomicInteger producerCount = new AtomicInteger();

    @Scheduled(every="5s")
    void produce() {
        log.info("sent : {} messages!", producerCount.incrementAndGet());
        eventBus.request("data-input-channel", UUID.randomUUID().toString())
                .subscribe().with(reply->{
                    log.info("received ack : {}", reply.body());
                }, error->{
                    log.error("error occurred : {}", error.getMessage(), error);
                });
    }

}
