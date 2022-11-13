/** 
 * TCSS 305
 * 
 * Hybrid class that represents hybrid bicycles in the vehicle rental program.
 * 
 */

package model.vehicles;

import java.math.BigDecimal;

/**
 * Class represents Hybrid bikes and is a child class of Bicycle class.
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class Hybrid extends BiCycle {

    public Hybrid(final int theVehicleID, final String theVIN,
                    final String theName, final boolean theAvailability) {
        super(theVehicleID, theVIN, theName, theAvailability);
        myRentalAmount = calculateMyRentalAmount();
    }
        
    /**
     * Method calculates rental amounts for Hybrid Bicycles.
     * 
     * @return BigDecimal rental amount.
     */
    @Override
    public BigDecimal calculateMyRentalAmount() {
        return BICYCLE_FARE.multiply(new BigDecimal("1.04"));
    }
}
