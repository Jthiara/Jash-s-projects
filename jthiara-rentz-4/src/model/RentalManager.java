/** 
 * TCSS 305
 * 
 * Rental Manager class that implements an inventory of vehicles and presents available vehicles to client.
 * Class also allows them to choose a vehicle for rent and creates a bill. 
 * 
 */
package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import model.vehicles.AbstractVehicle;
import model.vehicles.Car;
import model.vehicles.Cruiser;
import model.vehicles.MotorBike;
import model.vehicles.Mountain;
import model.vehicles.Road;

/**
 * Class represents a Rental Manager for the vehicle rental program.
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class RentalManager {

    /**
     * Static scanner.
     */
    private static Scanner input = new Scanner(System.in);
    
    /**
     * Map field of Vehicles.
     */
    private final Map<Integer, AbstractVehicle> myVehicleList;
    
    /**
     * Map field of bills.
     */
    private final Map<Integer, Bill> myBills;
    
    /**
     * reference to registration object.
     */
    private final Registration myRegistration;
    
    
    /**
     * Constructor that instantiates a RentalManager object.
     * 
     * @param theRegistration
     */
    public RentalManager(final Registration theRegistration) {
        myRegistration = Objects.requireNonNull(theRegistration);
        myVehicleList = generateInventory();
        myBills = new HashMap<Integer, Bill>();
    }
    
    /**
     * Method generates an inventory of vehicles for the client.
     * 
     * @return Map of HashMap.
     */
    public Map<Integer, AbstractVehicle> generateInventory() {
        
        final Map<Integer, AbstractVehicle> otherMap = new HashMap<Integer, AbstractVehicle>(); // map collection to hold vehicles.
        int i = 1; 
        
        otherMap.put(i, new Car(i++, "V100", "Fiat", true, false, false, false));
        otherMap.put(i, new Car(i++, "V101", "Outback", true, true, true, false));
        otherMap.put(i, new Car(i++, "V102", "BMW", true, true, true, true));
        otherMap.put(i, new MotorBike(i++, "B100", "Bike1" , true, false)); 
        otherMap.put(i, new MotorBike(i++, "B101", "Bike2t" , true, true));
        otherMap.put(i, new Road(i++, "C100", "Roadies", true));
        otherMap.put(i, new Cruiser(i++, "C101", "Cruiser", true));
        otherMap.put(i, new Mountain(i++, "C102", "Mountain", true));
        
        return otherMap;
    }
    
    /**
     * Method returns a a vehicle list.
     * 
     * @return Map copy of vehicleList.
     */
    public Map<Integer, AbstractVehicle> getMyVehicleList() {
        return Map.copyOf(myVehicleList);
    }
    
    /**
     * Method returns the registration object.
     * 
     * @return myRegistration
     */
    public Registration getMyRegistration() { 
        return myRegistration;
    }
    
    /**
     * Method returns whether or not a vehicle exists in the inventory and is available for rent.
     * 
     * @param theVehicleID
     * @return rentable status.
     */
    public boolean isRentable(final int theVehicleID) {
        final Set<Integer> keys = myVehicleList.keySet();
        if (keys.contains(theVehicleID)) { //make sure vehicle ID is in our inventory
            if (myVehicleList.get(theVehicleID).getMyAvailability()) { //check if the vehicle can be rented
                return true;
            }
        }
        return false;
    }
    
    /**
     * method rents a vehicle.
     * 
     * @param theVehicleID
     * @param theUserName
     * @param theNumDays
     * @param theBillID
     * 
     * @return boolean
     */
    public boolean rent(final int theVehicleID, final String theUserName,
                        final int theNumDays, final int theBillID) {
        
        Objects.requireNonNull(theUserName); // check for null values in userName string.
        
        if (theVehicleID < 0 || theNumDays < 0 || theBillID < 0
                        || theUserName.isEmpty()) { //incorrect vehicleID, days, and billID values.
            throw new IllegalArgumentException();
        }
        
        //validity check on username and vehicleID/availability
        if (!myVehicleList.containsKey(theVehicleID) || !isRentable(theVehicleID)) {
            System.out.println("Vehicle not rentable");
        }
        if (!myRegistration.getMyUserList().containsKey(theUserName)) {
            System.out.println("User does not exists, enter different user name:");
        }
        
        // case where vehicle exists and is available for rent. Calls computeAndPrintAmoutn method.
        if (isRentable(theVehicleID) && myRegistration.getMyUserList().containsKey(theUserName)) {
            myVehicleList.get(theVehicleID).setMyAvailability(false); // switches availability to false
            final Bill bill = new Bill(theBillID, myRegistration.getMyUserList().get(theUserName),
                                        myVehicleList.get(theVehicleID), theNumDays); // create a new bill object
            bill.computeAndPrintAmount();
            myBills.put(theBillID, bill); // add bill to our list of bills.        
            return true;
        }
        return false;
    }
    
    /**
     * method represents dropping a car off after rental.
     * 
     * @param theVehicleID
     * @return boolean
     */
    public boolean drop(final int theVehicleID) {
        if (myVehicleList.containsKey(theVehicleID)) { // check if car ID is valid.
            if (!myVehicleList.get(theVehicleID).getMyAvailability()) { // makes sure the availability is false.
                myVehicleList.get(theVehicleID).setMyAvailability(true); // returns vehicle availability back to true. 
                return true;
            }
        }
        return false;    
    }
    
    /**
     * Method prints list of options for agent to choose from 1) rent 2) drop off 3) exit. 
     */
    public void printOptions() { 
        final String tempVar = "*****************"; // handles repeated string error.
        boolean contResponse = true; // variable represents exit case if user wishes to discontinue.
        
        while (contResponse) { // loops until agent wishes not to continue.
            System.out.println("Enter 1 or 2 or 3 (1. Rent 2. Drop-off 3. Exit):");
            final int choice = input.nextInt();
            System.out.println("You entered option " + choice);  
            System.out.println();
            System.out.println(tempVar);
            
            if (choice == 1) { //rent case
                final Set<Integer> keys = myVehicleList.keySet();
                final Iterator<Integer> itr = keys.iterator();
                System.out.println("List of available vehicles:");
                    
                while (itr.hasNext()) { //prints list of vehicles.
                    final Integer key = itr.next();
                    if (myVehicleList.get(key).getMyAvailability()) {
                        System.out.println(myVehicleList.get(key));
                    }
                }
                boolean rent = false;
                while (!rent) {  //loops until correct information is entered.
                    System.out.println(tempVar);
                    System.out.println("Enter Rental Details");
                    System.out.println(tempVar);
                    System.out.print("Enter Vehicle ID:");
                    final int vehicleID = input.nextInt();
                    System.out.print("Enter User Name:");
                    final String userName = input.next();
                    System.out.print("Enter NumDays to rent:");
                    final int numDays = input.nextInt();
                    
                    rent = rent(vehicleID, userName, numDays, myBills.size()); //calls rent method
                }
            } else if (choice == 2) { // drop off case
                boolean drop = false;  //variable represents exit case of loop.
                System.out.println(tempVar);
                System.out.println("Enter Drop-off Details");
                System.out.println(tempVar);
                    
                while (!drop) { // loops until correct information is entered. 
                    System.out.print("Enter Drop-off Vehicle ID:");
                    final int vehicleID = input.nextInt();
                    drop = drop(vehicleID);
                    if (!drop) { // drop method fail case.
                        System.out.println("Vehicle is not rented already");
                    }
                }
                System.out.println("Drop-off Successfull"); // loop exit means drop was successfull.
            } else {
                break; // if agent enters option 3 program will terminate. 
            } 
            System.out.println(tempVar);
            System.out.print("Do you want to continue?");
            contResponse = input.nextBoolean(); 
        } 
        input.close();
    }
        

    
    /**
     * clears lists.
     * 
     */
    public void clearLists() {
        myVehicleList.clear();
        myBills.clear();
    }
}
