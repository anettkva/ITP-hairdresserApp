package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

public class WelcomeControllerTest extends ApplicationTest {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml")); // Sørg for at denne filen eksisterer
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    void testHandleButtonOpensHairdresserApp() throws Exception {
        // Simulerer et klikk på knappen som håndteres av handleButton()
        clickOn("#hairdresserButton");

        // Verifiserer at en ny stage er åpnet med riktig tittel eller innhold
        FxAssert.verifyThat("#shortHairCut", NodeMatchers.isVisible());
    }

    
}
