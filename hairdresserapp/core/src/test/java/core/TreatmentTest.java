package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class TreatmentTest {
    @Test
    void constructorValidInputsTest(){
        Treatment treatment = new Treatment("Long haircut", 500);
        assertEquals("Long haircut", treatment.getName());
        assertEquals(500, treatment.getPrice());
        assertEquals(60, treatment.getduration());

    }

    @Test
    void constructorInvalidPrice(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Treatment("Long haircut", -500);
        });
    }

    @Test
    void ValidsetPriceTest(){
        Treatment treatment = new Treatment("Long haircut", 500);
        treatment.setPrice(400);
        assertEquals(400, treatment.getPrice());

    }


    @Test
    void setNameTest(){
        Treatment treatment = new Treatment("Long haircut", 500);
        treatment.setName("Exstra Long haircut");
        assertEquals("Exstra Long haircut", treatment.getName());
    }

    
}
