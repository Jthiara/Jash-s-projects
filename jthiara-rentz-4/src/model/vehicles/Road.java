/** 
 * TCSS 305
 * 
 * Road class that represents Road bicycles in the vehicle rental program.
 * 
 */

package model.vehicles;

/**
 * Class represents a mountain bike and is a child of the BiCycle class.
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class Road extends BiCycle {

    public Road(final int theVehicleID, final String theVIN,
                final String theName, final boolean theAvailability) {
        super(theVehicleID, theVIN, theName, theAvailability);
        myRentalAmount = calculateMyRentalAmount();
    }
}
