/**
 * The Backend module.
 *
 * <p>
 * This module handles the backend services of the application, including business logic,
 * data management, and interactions with external systems. It leverages Spring Boot for
 * building RESTful APIs, handling dependency injection, and managing application contexts.
 * The backend module depends on the core module for foundational classes and utilizes various
 * Spring modules for comprehensive backend functionality.
 * </p>
 *
 * <p>
 * <strong>Requires:</strong>
 * <ul>
 *   <li>{@code transitive core}: Inherits dependencies from the core module, making them available to modules that require backend.</li>
 *   <li>{@code com.fasterxml.jackson.databind}: For JSON processing.</li>
 *   <li>{@code spring.web}: Provides Spring Web functionalities for building web applications and RESTful services.</li>
 *   <li>{@code spring.beans}: For Spring Bean management.</li>
 *   <li>{@code spring.boot}: Core Spring Boot functionalities.</li>
 *   <li>{@code spring.context}: For Spring ApplicationContext management.</li>
 *   <li>{@code spring.boot.autoconfigure}: Enables Spring Boot auto-configuration.</li>
 *   <li>{@code spring.data.jpa}: For Spring Data JPA functionalities.</li>
 *   <li>{@code transitive spring.core}: Inherits core Spring functionalities.</li>
 *   <li>{@code javafx.fxml}: For JavaFX FXML support.</li>
 *   <li>{@code javafx.controls}: Provides JavaFX controls.</li>
 *   <li>{@code javafx.graphics}: Provides JavaFX graphics capabilities.</li>
 *   <li>{@code spring.jdbc}: For Spring JDBC support.</li>
 *   <li>{@code org.slf4j}: For logging purposes.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Exports:</strong>
 * <ul>
 *   <li>{@code backend}: Exports backend services and controllers.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Opens:</strong>
 * <ul>
 *   <li>{@code backend} to {@code spring.core}, {@code spring.context}, {@code spring.boot}, {@code spring.beans}:
 *       Allows Spring to perform reflection on the {@code backend} package for dependency injection and other Spring-related functionalities.</li>
 * </ul>
 * </p>
 */
module backend {
    requires transitive core;
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires transitive spring.core;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires spring.jdbc;
    requires org.slf4j;

    exports backend;
    opens backend to spring.core, spring.context, spring.boot, spring.beans;
}
