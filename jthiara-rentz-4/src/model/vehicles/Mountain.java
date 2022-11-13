/** 
 * TCSS 305
 * 
 * Mountain Class that represents Mountain bikes in vehical rental program..
 * 
 */

package model.vehicles;

import java.math.BigDecimal;

/**
 * Class represents a mountain bike and is a child of the BiCycle class.
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class Mountain extends BiCycle {

    public Mountain(final int theVehicleID, final String theVIN,
                final String theName, final boolean theAvailability) {
        super(theVehicleID, theVIN, theName, theAvailability);
        myRentalAmount = calculateMyRentalAmount();
    }
    
    /**
     * Calculates rental amount for mountain bike class. 
     * 
     * @return BigDecimal.
     */
    @Override
    public BigDecimal calculateMyRentalAmount() {
        return BICYCLE_FARE.multiply(new BigDecimal("1.01"));
    }
}
