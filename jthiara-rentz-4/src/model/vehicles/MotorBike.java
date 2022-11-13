/** 
 * TCSS 305
 * 
 * MotorBike class that represents MotorBike vehicles in the vehicle rental program.
 * 
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class represents MotorBikes and is a child class of AbstractVehicle.
 * 
 * @author jthiara
 * @version Fall 2021
 */
public class MotorBike extends AbstractVehicle {
    /**
     * 
     * Constant field for MotorBike fare.
     */
    public static final BigDecimal MOTORBIKE_FARE = BASE_FARE.multiply(new BigDecimal("2.00"));
    
    /**
     * 
     * Field for Touring status.
     */
    private final boolean myTouring;
    
    /**
     * Constructs a MotorBike using provided vehicle ID, VIN, name, availability, and rental amount.
     * 
     * @param theVehicleID
     * @param theVIN
     * @param theName
     * @param theAvailability
     * @param theTouring
     */
    public MotorBike(final int theVehicleID, final String theVIN, final String theName,
                   final boolean theAvailability, final boolean theTouring) {
        super(theVehicleID, theVIN, theName, theAvailability);
        myRentalAmount = calculateMyRentalAmount();
        myTouring = theTouring;
    }
    
    /**
     * gets touring status of motorbike object.
     * 
     * @return myTouring status of Motorbike.
     */
    public boolean getMyTouring() {
        return this.myTouring;
    }
    
    /**
     * Method calculates rental amount for Motorbikes.
     * 
     * @return BigDecimal rental amount for MotorBike.
     */
    @Override
    public BigDecimal calculateMyRentalAmount() {
        if (getMyTouring()) {
            return MOTORBIKE_FARE.add(new BigDecimal("5"));
        } else {
            return MOTORBIKE_FARE;
        }
    }
    
    /**
     * Method returns a string representation of MotorBike object.
     * 
     * @return String sb 
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" (ID:");
        sb.append(getMyVehicleID());
        sb.append(", Name:");
        sb.append(getMyName());
        sb.append(", VIN:");
        sb.append(getMyVIN());
        sb.append(", CanRent?:");
        sb.append(getMyAvailability());
        sb.append(", IsTouring?:");
        sb.append(getMyTouring());
        sb.append(")");
        return sb.toString();
        
    }
    
    /**
     * Method compares two objects and returns true if they are both MotorBikes with identical fields.
     * 
     * @return boolean result of comparison.
     */
    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) { // returns true if we are comparing same object (same address).
            return true;
        }
        if (theOther == null) { // null object case.
            return false;
        }
        
        if (getClass() != theOther.getClass()) { // returns false if object are not of the same class. 
            return false; 
        }
        
        final MotorBike otherMotorBike = (MotorBike) theOther;
        
        return getMyVehicleID() == otherMotorBike.getMyVehicleID() // returns true if fields are identical.
                        && getMyName().equals(otherMotorBike.getMyName())
                        && getMyVIN().equals(otherMotorBike.getMyVIN())
                        && getMyAvailability() == otherMotorBike.getMyAvailability()
                        && getMyTouring() == otherMotorBike.getMyTouring();
    }
    
    /**
     * Method creates a hashcode for MotorBike object.
     * 
     * @return int hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMyVehicleID(), getMyVIN(), getMyName(), getMyAvailability(), getMyTouring());
    }
}
