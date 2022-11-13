/** 
 * TCSS 305
 * 
 * Abstract vehicle class for vehicle rental application.
 * 
 */

package model.vehicles;

import java.math.BigDecimal;

/**
 * This class creates an Abstract Vehicle class that will be an outline for specific vehicles to extend and build on.
 * 
 * 
 * @author jthiara
 * @version Fall 2021
 */
public abstract class AbstractVehicle {
    
    /**
     * Constant field representing fare for vehicles.
     */
    public static final BigDecimal BASE_FARE = new BigDecimal(10);
    
    /**
     * Rental amount field.
     * 
     */
    protected BigDecimal myRentalAmount; 
    
    /**
     * Vehicle ID field.
     * 
     */
    private final int myVehicleID;
    
    /**
     * VIN field. 
     * 
     */
    private final String myVIN;
    
    /**
     * Name field.
     * 
     */
    private final String myName;
    
    /**
     * Availability field.
     * 
     */
    private boolean myAvailability; 
   
    
    /**
     *  Constructor.
     *  @param theVehicleID 
     *  @param theVIN
     *  @param theName
     *  @param theAvailability
     */
    public AbstractVehicle(final int theVehicleID, final String theVIN, final String theName,
                           final boolean theAvailability) {
        myVehicleID = theVehicleID;
        myVIN = theVIN;
        myName = theName;
        myAvailability = theAvailability;
        myRentalAmount = calculateMyRentalAmount();
    }
    
    /**
     * Gets Vehicle Id for a specific vehicle.
     * 
     * @return myVehicle ID of the vehicle.
     */
    public int getMyVehicleID() {
        return myVehicleID;
    }
    
    /**
     * Gets Vehicle VIN for a specific vehicle.
     * 
     * @return myVIN of the vehicle.
     */
    public String getMyVIN() {
        return myVIN;
    }
    
    /**
     * Gets Name of Vehicle.
     * 
     * @return myName of vehicle.
     */
    public String getMyName() {
        return myName;
    }
    
    /**
     * gets availability status of vehicle.
     * 
     * @return myAvailability of vehicle.
     */
    public boolean getMyAvailability() {
        return myAvailability;
    }
    
    /**
     * method changes availability status for a vehicle.
     * 
     * @param theAvailibility
     */
    public void setMyAvailability(final boolean theAvailibility) {
        myAvailability = theAvailibility;
    }
    
    /**
     * gets rental amount of vehicle.
     * 
     * @return myRentalAmount of vehicle.
     */
    public BigDecimal getMyRentalAmount() {
        return myRentalAmount;
    }
    
    /**
     * calculates rental amounts for vehicle.
     * 
     * @return BigDecimal 
     */
    public abstract BigDecimal calculateMyRentalAmount();
}
 