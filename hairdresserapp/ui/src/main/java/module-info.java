module ui {
    requires core;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires backend;
    
    opens ui to javafx.graphics, javafx.fxml;

}
