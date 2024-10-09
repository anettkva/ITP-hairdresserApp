package core;

import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TreatmentTest {
    @Test
    void constructorValidInputsTest(){
        Treatment treatment = new Treatment("Long haircut", 500, 90);
        assertEquals("Long haircut", treatment.getName());
        assertEquals(500, treatment.getPrice());
        assertEquals(90, treatment.getduration());

    }

    @Test
    void constructorInvalidPrice(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Treatment("Long haircut", -500, 90);
        });
    }

    @Test
    void constructorInvalidDurationTest(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Treatment("Long haircut", 500, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Treatment("Long haircut", 500, 500);
        });

    }

    @Test
    void ValidsetPriceTest(){
        Treatment treatment = new Treatment("Long haircut", 500, 90);
        treatment.setPrice(400);
        assertEquals(400, treatment.getPrice());

    }


    @Test
    void ValidsetDurationTest(){
        Treatment treatment = new Treatment("Long haircut", 500, 90);
        treatment.setduration(60);
        assertEquals(60, treatment.getduration());

    }

    @Test
    void InvalidSetDurationTest(){
        Treatment treatment = new Treatment("Long haircut", 500, 90);
        assertThrows(IllegalArgumentException.class, () -> {
            treatment.setduration(-10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            treatment.setduration(500);
        });
    }

    @Test
    void constructorDurationBoundryTest(){
        Treatment treatment1 = new Treatment("Long haircut", 500,0);
        assertEquals(0, treatment1.getduration());

        Treatment treatment2 = new Treatment("Long haircut", 500, 420);
        assertEquals(420, treatment2.getduration());
    }

    @Test
    void setDurationBoundryTest(){
        Treatment treatment = new Treatment("Long haircut", 500, 90);
        treatment.setduration(0);
        assertEquals(0, treatment.getduration());

        treatment.setduration(420);
        assertEquals(420, treatment.getduration());
    }

    @Test
    void setNameTest(){
        Treatment treatment = new Treatment("Long haircut", 500, 90);
        treatment.setName("Exstra Long haircut");
        assertEquals("Exstra Long haircut", treatment.getName());
    }

    
}
