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

        assertEquals(startTime, list.get(0).getStart()); 
        assertEquals(startTime2, list.get(1).getStart());

        List<TimeSlot> allSlots = this.bookingController.getAllTimeSlots();

        for (TimeSlot bookedSlot : list) {
            for (TimeSlot slot : allSlots) {
                if (bookedSlot.getStart().equals(slot.getStart())) {
                    assertTrue(slot.isBooked());
                }
            }
            
        }


        this.filehandling.reset();
    }

    @Test
    public void testShowAllTimeSlots() throws IOException, InterruptedException{
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
    // import static org.junit.jupiter.api.Assertions.*;

// import core.TimeSlot;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.ContextConfiguration;
// import org.testfx.framework.junit5.ApplicationTest;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.Arrays;
// import java.util.List;

// @SpringBootTest
// @ContextConfiguration(classes = {BookingController.class, FrontBookingService.class})
// public class BookingControllerTest extends ApplicationTest {

//     @Autowired
//     private BookingController bookingController;

//     @MockBean
//     private FrontBookingService frontBookingService;

   

//     @Test
//     public void testShowAllTimeSlots() throws Exception {
//         TimeSlot slot1 = new TimeSlot(LocalDateTime.now().plusDays(1).withHour(10).withMinute(0));
//         slot1.setBooked(true);
//         TimeSlot slot2 = new TimeSlot(LocalDateTime.now().plusDays(1).withHour(11).withMinute(0));
//         slot2.setBooked(false);
//         List<TimeSlot> bookedSlots = Arrays.asList(slot1, slot2);

//         Mockito.when(frontBookingService.getBookedSlots()).thenReturn(bookedSlots);

//         bookingController.showAllTimeSlots();

//         String expectedText = "Oversikt over timer: \n" +
//                 slot1.getStart().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")) + " - Booket\n" +
//                 slot2.getStart().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")) + " - Ledig\n";

//         assertEquals(expectedText, bookingController.getarea().getText());
//     }
// }
}
