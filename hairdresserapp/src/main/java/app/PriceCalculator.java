
package app;
//klasse som har en kalkulator som tar inn en liste med treatments, og regner ut totalpris, og returnerer dette.
import java.util.List;

public class PriceCalculator {
    public double CalculateTotalPrice(List<Treatment> treatments){ //tar inn en liste med treatments-objekter

        double totalPrice = 0.0;

        for (Treatment treatment : treatments){
            totalPrice += treatment.getPrice();
        }

        return totalPrice;



    }
    
}


