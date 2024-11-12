/**
 * The UI module.
 *
 * <p>
 * This module provides the user interface components of the application, utilizing JavaFX for graphical interfaces.
 * It depends on the core module for business logic and the backend module for data management.
 * </p>
 *
 * <p>
 * <strong>Requires:</strong>
 * <ul>
 *   <li>{@code core}: Provides core business logic and data structures.</li>
 *   <li>{@code javafx.controls}: Provides JavaFX controls for building the UI.</li>
 *   <li>{@code javafx.fxml}: Enables FXML support for defining UI layouts.</li>
 *   <li>{@code javafx.graphics}: Provides JavaFX graphics capabilities.</li>
 *   <li>{@code java.net.http}: For HTTP communication.</li>
 *   <li>{@code com.fasterxml.jackson.databind}: For JSON processing.</li>
 *   <li>{@code com.fasterxml.jackson.datatype.jsr310}: For JSON support for Java 8 date and time types.</li>
 *   <li>{@code backend}: Provides backend services and data management.</li>
 *   <li>{@code spring.beans}: For Spring Bean management.</li>
 *   <li>{@code spring.context}: For Spring ApplicationContext.</li>
 *   <li>{@code spring.core}: For core Spring functionalities.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Opens:</strong>
 * <ul>
 *   <li>{@code ui} to {@code javafx.graphics}, {@code javafx.fxml}, {@code spring.beans}, {@code spring.context}, {@code spring.core}:
 *       Allows these frameworks to perform reflection on the {@code ui} package.</li>
 * </ul>
 * </p>
 */
module ui {
    requires core;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires backend;
    requires spring.beans;
    requires spring.context;
    requires spring.core;

    opens ui to javafx.graphics, javafx.fxml, spring.beans, spring.context, spring.core;
}

