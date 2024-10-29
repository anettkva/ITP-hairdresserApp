package core;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;


public class PriceCalculatorTest {
    @Test
    public void calculateTotalPriceTest(){
        Treatment haircut = new Treatment("Haircut",500);
        Treatment coloring = new Treatment("Hair Coloring",700);
        Treatment styling = new Treatment("Styling", 400);

        List<Treatment> treatments = Arrays.asList(haircut,coloring,styling);

        PriceCalculator priceCalculator = new PriceCalculator();

        double totalPrice = priceCalculator.CalculateTotalPrice(treatments);

        assertEquals(1600, totalPrice, "totalprisen skal være 1000 kr.");
    }
    @Test
    public void calculateTotalPrice_EmptyList(){
        List<Treatment> emptyList = Arrays.asList();
        PriceCalculator priceCalculator = new PriceCalculator();
        double totalPrice = priceCalculator.CalculateTotalPrice(emptyList);
        assertEquals(0.0, totalPrice, "totalprisen skal være 0.0 kr");
    }
    
}




    

