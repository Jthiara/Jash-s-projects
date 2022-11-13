/** 
 * TCSS 305
 * 
 * Bill class represents bills in the vehicle rental program.
 * Class utilizes vehicle information and user input to calculate the bill.
 * 
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.vehicles.AbstractVehicle;


/**
 * represents Bill object in vehicle rental program.
 * 
 * @author jthiara
 * @version FALL 2021
 */

public class Bill {

    /**
     * field represents a Bill ID.
     */
    private final int myBillID;
    
    /**
     * Primary User field.
     */
    private final User myPrimaryUser;
    
    /**
     * primary vehicle field.
     */
    private final AbstractVehicle myVehicle;
    
    /**
     * number of days to rent field.
     */
    private final int myNumDays;
    
    /**
     * Bill amount field.
     */
    private BigDecimal myBillAmount;
    
    /**
     * constructs a Bill Object taking in the following parameters.
     * 
     * @param theBillID
     * @param theUser
     * @param theVehicle
     * @param theNumDays
     */
    public Bill(final int theBillID, final User theUser, final AbstractVehicle
                theVehicle, final int theNumDays) {
        myBillID = theBillID;
        myPrimaryUser = theUser;
        myVehicle = theVehicle;
        myNumDays = theNumDays;
        myBillAmount = new BigDecimal(0.0);
    }
    
    /**
     * Method calculates bill amount using user input and information on tax rates, discount rates, and insurance rates.
     */
    public void computeAndPrintAmount() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final BigDecimal percent = new BigDecimal("0.01"); // rate for insurance and vip discount. 
        final BigDecimal taxPercent = new BigDecimal("0.10");
        final String tempVar = "*****************"; // temporary variable to handle magic number error.
       
        System.out.println(tempVar);
        System.out.println("Rental Bill Summary");
        System.out.println(tempVar);
        System.out.println("User Name: " + myPrimaryUser.getMyName());
        
        System.out.println("----Vehicle Information----");
        System.out.println("VehicleName " + myVehicle.getMyName());
        System.out.println("VehicleID " + myVehicle.getMyVehicleID());
        System.out.println("VehicleType " + myVehicle.getMyVIN());
        System.out.println("VIN " + myVehicle.getMyVIN());
        
        System.out.println("----Cost Information----");
        System.out.println("RentalPerDay: ");
        System.out.println("Cost Per day " + nf.format(myVehicle.getMyRentalAmount()));
        System.out.println("No. of Rental days: " + myNumDays);
        
        myBillAmount = new BigDecimal(myNumDays).multiply(myVehicle.getMyRentalAmount()); // total amount.
        
        System.out.println("Total Amount: " + nf.format(myBillAmount));
        final BigDecimal insuranceAmount = myBillAmount.multiply(percent); // insurance amount
        System.out.println("Insurance: " + nf.format(insuranceAmount));
        BigDecimal discountAmount = new BigDecimal("0.00"); // discount amount
        System.out.print("VIP discount: ");
        if (myPrimaryUser.getMyVIPStatus()) { // discount only activated on VIP status otherwise is 0.
            discountAmount = myBillAmount.multiply(percent.multiply(new BigDecimal("-1")));
        }
        System.out.println(nf.format(discountAmount));
        final BigDecimal taxAmount = myBillAmount.multiply(taxPercent); // tax amount
        System.out.println("Tax: " + nf.format(taxAmount));
        myBillAmount = myBillAmount.add(insuranceAmount);
        myBillAmount = myBillAmount.add(discountAmount);
        myBillAmount = myBillAmount.add(taxAmount);
        System.out.println("Total Rent: " + nf.format(myBillAmount)); // final cost after calculations
        System.out.println("Rent Successfull");
        System.out.println(tempVar);
    }
}
