package app;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;



public class PriceCalculator {


public class PriceCalculatorTest {
    @Test
    public void calculateTotalPrice(){
        Treatment haircut = new Treatment("Haircut",500, 60);
        Treatment coloring = new Treatment("Hair Coloring",700,90);
        Treatment styling = new Treatment("Styling", 400,90);

        List<Treatment> treatments = Arrays.asList(haircut,coloring,styling);

        PriceCalculator priceCalculator = new PriceCalculator();

        double totalPrice = priceCalculator.calculateTotalPrice(treatments);

        assertEquals(1600, totalPrice, "total prie should be 1000");
    }
    
}



    

