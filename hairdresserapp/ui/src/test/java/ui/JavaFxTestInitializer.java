package ui;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

public class JavaFxTestInitializer {
    private static boolean initialized = false;

    public static void initializeJavaFX() {
        if (!initialized) {
            final CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(() -> {
                // No need to do anything here
                latch.countDown();
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException("JavaFX initialization interrupted", e);
            }
            initialized = true;
        }
    }
}
