package s.m.learn;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class AppRunner implements QuarkusApplication {
    public static void main(String... args) {
        Quarkus.run(AppRunner.class, args);
    }

    @Override
    public int run(String... args) {
        Quarkus.waitForExit();
        return 0;
    }

    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting on hardware with {} processors",
                Runtime.getRuntime().availableProcessors());
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("The application is stopping...");
    }
}

