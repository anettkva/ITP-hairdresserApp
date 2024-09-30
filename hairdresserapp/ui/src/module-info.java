module ui {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires gr2415.core;

    opens ui to javafx.graphics, javafx.fxml;

}
