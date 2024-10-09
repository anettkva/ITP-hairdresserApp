package core;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import core.TimeSlot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TreatmentTest {
    @Test
    void constructorValidInputsTest(){
        Treatment treatment = new Treatment("Long hair cut", 500, 90);
        assertEquals("Long hair cut", treatment.getName());
        assertEquals(500, treatment.getPrice());
        assertEquals(90, treatment.getduration());

    }

    @Test
    void constructorInvalidPrice(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Treatment("Long hair cut", -500, 90);
        });
    }

    @Test
    void constructorInvalidDurationTest(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Treatment("Long hair cut", 500, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Treatment("Long hair cut", 500, 500);
        });

    }

    
}
