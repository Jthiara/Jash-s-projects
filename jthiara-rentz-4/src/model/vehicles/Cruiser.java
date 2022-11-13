/** 
 * TCSS 305
 * 
 * Cruiser class that represents cruiser bicycles in the vehicle rental program.
 * 
 */

package model.vehicles;

import java.math.BigDecimal;
/**
 * Class represents Cruiser Bicycles and is a child class of Bicycles.
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class Cruiser extends BiCycle {

    public Cruiser(final int theVehicleID, final String theVIN,
                    final String theName, final boolean theAvailability) {
        super(theVehicleID, theVIN, theName, theAvailability);
        myRentalAmount = calculateMyRentalAmount();
    }
   
    /**
     * Method calculates rental amounts for Cruiser Bicycles.
     * 
     * @return BigDecimal rental amount.
     */
    @Override
    public BigDecimal calculateMyRentalAmount() {
        return BICYCLE_FARE.multiply(new BigDecimal("1.02"));
    }
}
