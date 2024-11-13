package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Entry point for the Backend Spring Boot application.
 * 
 * This class initializes and runs the Spring Boot application. It is configured to exclude
 * the auto-configuration for data sources and to scan specific base packages for Spring
 * components.
 * 
 * <p>
 * Annotations:
 * <ul>
 *   <li>{@link SpringBootApplication}: Indicates a configuration class that declares one or more {@link org.springframework.context.annotation.Bean} methods and also triggers auto-configuration and component scanning.</li>
 *   <li>{@link ComponentScan}: Configures component scanning directives for use with {@link org.springframework.context.annotation.Configuration} classes.</li>
 * </ul>
 * </p>
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"backend", "ui"})
public class BackendApplication {
    
    /**
     * Main method that launches the Spring Boot application.
     * 
     * This method delegates to Spring Boot's {@link SpringApplication#run(Class, String...)} method,
     * starting the application context.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
