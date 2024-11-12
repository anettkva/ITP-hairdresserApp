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
    requires com.fasterxml.jackson.core;
  
    
    opens ui to javafx.graphics, javafx.fxml, spring.beans, spring.context, spring.core;

}
