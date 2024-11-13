/**
 * The Core module.
 *
 * <p>
 * This module contains the core business logic and data structures of the application.
 * It provides essential classes such as {@link Treatment}, {@link TimeSlot}, and utilities
 * like {@link PriceCalculator}. The core module also handles JSON serialization and
 * deserialization through its JSON-related packages.
 * </p>
 *
 * <p>
 * <strong>Exports:</strong>
 * <ul>
 *   <li>{@code core}: Exports the main core functionalities and classes.</li>
 *   <li>{@code json}: Exports JSON handling utilities.</li>
 *   <li>{@code json.internal}: Exports internal JSON utilities, such as custom serializers and deserializers.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Requires:</strong>
 * <ul>
 *   <li>{@code com.fasterxml.jackson.databind}: For JSON processing.</li>
 *   <li>{@code com.fasterxml.jackson.datatype.jsr310}: For JSON support for Java 8 date and time types.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Opens:</strong>
 * <ul>
 *   <li>{@code core} to {@code com.fasterxml.jackson.databind}:
 *       Allows Jackson to perform reflection on the {@code core} package for serialization and deserialization.</li>
 * </ul>
 * </p>
 */
module core {
    exports core;
    exports json;
    exports json.internal;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens core to com.fasterxml.jackson.databind;
}

