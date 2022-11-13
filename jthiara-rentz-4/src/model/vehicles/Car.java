/** 
 * TCSS 305
 * 
 * Car class represents car vehicles in the vehicle rental program.
 * 
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * class represents Car vehicles and is a child class of AbstractVehicle.
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class Car extends AbstractVehicle {
    /**
     * 
     * Constant field for Car fare.
     */
    public static final BigDecimal CAR_FARE = BASE_FARE.multiply(new BigDecimal("3.00"));
    
    /**
     * 
     * Field for Luxury status.
     */
    private final boolean myLuxury;
    
    /**
     * 
     * Field for Navigation status.
     */
    private final boolean myNavigation;
    
    /**
     * 
     * Field for DrivingAssistance status.
     */
    private final boolean myDrivingAssistance;
    
    /**
     * Constructs a Car using provided vehicle ID, VIN, name, availability, luxury, navigation, drivingassistance.
     * 
     * @param theVehicleID
     * @param theVIN
     * @param theName
     * @param theAvailability
     * @param theLuxury
     * @param theNavigation
     * @param theDrivingAssistance
     */
    public Car(final int theVehicleID, final String theVIN, final String theName,
                   final boolean theAvailability, final boolean theLuxury,
                   final boolean theNavigation, final boolean theDrivingAssistance) {
        super(theVehicleID, theVIN, theName, theAvailability);
        myLuxury = theLuxury;
        myNavigation = theNavigation;
        myDrivingAssistance = theDrivingAssistance;
        myRentalAmount = calculateMyRentalAmount();
    }
    
    /**
     * method returns whether the car is luxury or not.
     * 
     * @return myLuxury status of car.
     */
    public boolean getMyLuxury() {
        return this.myLuxury;
    }
    
    /**
     * method returns whether the car has navigation or not.
     * 
     * @return myNavigation status of car.
     */
    public boolean getMyNavigation() {
        return this.myNavigation;
    }
    /**
     * method returns whether the car has driving assistance or not.
     * 
     * @return myDrivingAssistance status of car.
     */
    public boolean getMyDrivingAssistance() {
        return this.myDrivingAssistance;
    }
    
    /**
     * Method calculates rental amount for car objects.
     * 
     * @return BigDecimal rental amount of car object.
     */
    @Override 
    public BigDecimal calculateMyRentalAmount() { 
        BigDecimal otherDecimal = CAR_FARE;
        
        if (getMyLuxury()) { // luxury car case
            otherDecimal = otherDecimal.add(new BigDecimal("10.00"));
        } 
        
        if (getMyNavigation()) { // car has navigation case
            otherDecimal = otherDecimal.add(new BigDecimal("1.00"));
        } 
        
        if (getMyDrivingAssistance()) { // car has driving assistance case
            otherDecimal = otherDecimal.add(new BigDecimal("2.00"));
        } 
        
        return otherDecimal;
    }
    
    /**
     * method creates a string representation of car object.
     * 
     * @return String.
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
        sb.append(", IsLuxury?:");
        sb.append(getMyLuxury());
        sb.append(", HasNavigation?:");
        sb.append(getMyNavigation());
        sb.append(", HasAssistance?:");
        sb.append(getMyDrivingAssistance());
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * Method compares two objects and returns true if they are both car objects with identical fields.
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
        
        final Car otherCar = (Car) theOther;
        
        return getMyVehicleID() == otherCar.getMyVehicleID() // returns true if objects have identical fields.
                        && getMyName().equals(otherCar.getMyName())
                        && getMyVIN().equals(otherCar.getMyVIN())
                        && getMyAvailability() == otherCar.getMyAvailability()
                        && getMyLuxury() == otherCar.getMyLuxury()
                        && getMyNavigation() == otherCar.getMyLuxury()
                        && getMyDrivingAssistance() == otherCar.getMyDrivingAssistance(); 
    }
    
    /**
     * method creates a hashcode for car object.
     * 
     * @return int hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMyName(), getMyVIN(),
                          getMyAvailability(), getMyLuxury(),
                          getMyNavigation(), getMyDrivingAssistance());
    }
}
