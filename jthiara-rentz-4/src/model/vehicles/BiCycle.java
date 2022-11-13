/** 
 * TCSS 305
 * 
 * Bicycle class to represent Bicycle vehicles in the vehicle Registration method.
 * Class contains constant field BICYCLE_FARE, and adopts fields from AbstractVehicle class.
 * 
 */

package model.vehicles;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * represents Bicycle object in vehical rental application.
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class BiCycle extends AbstractVehicle {
    /**
     * 
     * Constant field for Bicycle fare.
     */
    public static final BigDecimal BICYCLE_FARE = BASE_FARE;
    
    /**
     * Constructs a bicycle using provided vehicle ID, VIN, name, availability.
     * 
     * @param theVehicleID
     * @param theVIN
     * @param theName
     * @param theAvailability
     */
    public BiCycle(final int theVehicleID, final String theVIN, final String theName,
                   final boolean theAvailability) {
        super(theVehicleID, theVIN, theName, theAvailability);
        myRentalAmount = calculateMyRentalAmount();
    }
    
    /**
     * Calculates rental amount for BiCycle class.
     */
    @Override
    public BigDecimal calculateMyRentalAmount() {
        return BICYCLE_FARE;
    }
    
    /**
     * Creates string representation of the object.
     * 
     * @return sb
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSuperclass().getSimpleName());
        sb.append(" (ID:");
        sb.append(getMyVehicleID());
        sb.append(", Name:");
        sb.append(getMyName());
        sb.append(", VIN:");
        sb.append(getMyVIN());
        sb.append(", CanRent?:");
        sb.append(getMyAvailability());
        sb.append(", CycleType:");
        sb.append(getClass().getSimpleName());
        sb.append(")");
        
        return sb.toString();
    }
    
    /**
     * Returns true if the parameter is of the same class type and has identical name, vehicleID, NAME, VIN, and availability.
     * 
     * @param theOther
     * @return boolean result of equals method.
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
        
        final BiCycle otherBiCycle = (BiCycle) theOther;
        
        return getMyVehicleID() == otherBiCycle.getMyVehicleID()  //checks that all fields are identical
                        && getMyName().equals(otherBiCycle.getMyName())
                        && getMyVIN().equals(otherBiCycle.getMyVIN())
                        && getMyAvailability() == otherBiCycle.getMyAvailability();
    }
    
    /**
     * Creates hash code for bicycle objects.
     * 
     * @return int hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMyVehicleID(), getMyName(), getMyVIN(), getMyAvailability());
    }
}
