
/*
 * Main class for the Vehicle Rental System TCSS 305
 * 
 * TCSS 305 - Rentz
 */

package model;

/**
 * RentalMain provides the main method for a simple VehicleRental application.
 * 
 * @author Jthiara
 * @version Fall 2021
 */
public final class RentalMain {

    /**
     * A private constructor, to prevent external instantiation.
     */
    private RentalMain() {

    }

    /**
     * Main method for Rentz.
     * 
     * @param theArgs argument for main method.
     */
    public static void main(final String[] theArgs) {
        final Registration reg = new Registration();
        final boolean otherResult = reg.printSignin();
        
        if (otherResult) { // if login is successful then the vehicles available for rent will be shown.
            final RentalManager regManager = new RentalManager(reg);
            regManager.printOptions(); // agent will be presented options from the vehicle rental program.
            regManager.clearLists();
        }
    } 

}
