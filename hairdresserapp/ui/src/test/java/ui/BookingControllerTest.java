package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.TimeSlot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import json.JsonFilehandling;

public class BookingControllerTest extends ApplicationTest{

    /*private BookingController bookingController;
    private TextArea area;
    private JsonFilehandling filehandling;
    private TextField bookingTextField;
    

    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Booking.fxml"));
        final Parent root = loader.load();
        this.bookingController = loader.getController();
        this.area = bookingController.getarea();
        stage.setScene(new Scene(root));
        stage.show();
        this.bookingTextField = bookingController.getfield();
        this.filehandling = new JsonFilehandling();
    } 

    @Test
    public void testLoadJsonFile() throws IOException{
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
        String[] date = String.valueOf(startTime).split("-");
        String[] time = String.valueOf(startTime).split(":");
        this.bookingTextField.setText(date[0] + "-" + date[1] + "-" + date[2].split("T")[0] + " " + time[0].split("T")[1] + ":" + time[1]);
        
        this.bookingController.bookTimeSlot();
        
        LocalDateTime startTime2 = LocalDateTime.now().plusDays(2).withHour(10).withMinute(0).withSecond(0).withNano(0);
        String[] date2 = String.valueOf(startTime2).split("-");
        String[] time2 = String.valueOf(startTime2).split(":");
        this.bookingTextField.setText(date2[0] + "-" + date2[1] + "-" + date2[2].split("T")[0] + " " + time2[0].split("T")[1] + ":" + time2[1]);
        
        this.bookingController.bookTimeSlot();

        List<TimeSlot> list = this.filehandling.readFromFile();

        assertEquals(startTime, list.get(0).getStartTime()); 
        assertEquals(startTime2, list.get(1).getStartTime());

        List<TimeSlot> allSlots = this.bookingController.getAllTimeSlots();

        for (TimeSlot bookedSlot : list) {
            for (TimeSlot slot : allSlots) {
                if (bookedSlot.getStartTime().equals(slot.getStartTime())) {
                    assertTrue(slot.isBooked());
                }
            }
            
        }


        this.filehandling.reset();
    }

    @Test
    public void testShowAllTimeSlots() throws IOException{
        this.bookingController.showAllTimeSlots();

        assertFalse(this.area.getText() == "");

    }

    @Test
    public void testIllegalArgumentException() {
        this.bookingTextField.setText("2024-10-13 10:00");
        this.bookingController.bookTimeSlot();

        assertEquals("Ugyldig starttid: Velg en tilgjengelig tid fra listen", this.area.getText());
    }
*/
    
}
