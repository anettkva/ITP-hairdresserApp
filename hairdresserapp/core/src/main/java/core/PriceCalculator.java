package core;

import java.util.List;

/**
 * Provides functionality to calculate the total price of selected treatments.
 * 
 * This class contains a method to compute the aggregate cost of a list of {@link Treatment} objects.
 */
public class PriceCalculator {
    
    /**
     * Calculates the total price of the given list of treatments.
     * 
     * This method iterates through the list of {@link Treatment} objects, sums up their prices,
     * and returns the total amount. It ensures that all treatments are accounted for in the total.
     * 
     * @param treatments the {@link List} of {@link Treatment} objects whose prices are to be calculated
     * @return the total price as a {@code double}
     * @throws IllegalArgumentException if the treatments list is {@code null}
     */
    public double CalculateTotalPrice(List<Treatment> treatments) throws IllegalArgumentException {
        if (treatments == null) {
            throw new IllegalArgumentException("Treatments list cannot be null");
        }

        double totalPrice = 0.0;

        for (Treatment treatment : treatments) {
            totalPrice += treatment.getPrice();
        }

        return totalPrice;
    }
    
}



