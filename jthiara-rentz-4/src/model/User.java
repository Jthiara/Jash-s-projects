
package model;

import java.util.Objects;

/**
 * Represents a single user for registration or sign-in. User is an immutable object.
 * 
 * Constructors and methods of this class throw NullPointerException if required parameters are
 * null.
 * 
 * @author Jthiara
 * @version Fall 2021
 */

public final class User {
    
    /**
     * Name in database.
     */
    private final String myName;
    
    /**
     * Password in database.
     */
    private final String myPassword;
    
    /**
     * VIP Status in database.
     */
    private final boolean myVIPStatus;
    
    /**
     * Constructs a User object using the provided name and username.
     * 
     * @param theName The name to assign to this user.
     * @param thePassword The password to assign to this user. 
     */
    public User(final String theName, final String thePassword) { 
        // checks for null arguments
        Objects.requireNonNull(theName, thePassword);
        
        // checks for empty arguments 
        if (theName.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        //values will be assigned to the user only if they pass the previous test cases
        this.myName = theName;
        this.myPassword = thePassword;
        this.myVIPStatus = false; // default value for two parameter constructor
    }
    
    /**
     * Constructs a User object using the provided name, username, and vipstatus.
     * 
     * @param theName The name to assign to this user.
     * @param thePassword The password to assign to this user. 
     * @param theVIPStatus The vipstatus to assign to this user.
     */
    public User(final String theName, final String thePassword, final boolean theVIPStatus) {
         // checks for null arguments
        Objects.requireNonNull(theName, thePassword);
        
        // checks for empty arguments
        if (theName.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        //values will be assigned to the user only if they pass the previous test cases
        this.myName = theName;
        this.myPassword = thePassword;
        this.myVIPStatus = theVIPStatus;
    }
    
    /**
     * getter for myName.
     * 
     * @return myName
     */
    public String getMyName() {
        return myName;
    }
    
    /**
     * getter for myPassword.
     * 
     * @return myPassword
     */
    public String getMyPassword() {
        return myPassword;
    }
    
    /**
     * getter for myVIPStatus.
     * 
     * @return myVIPStatus
     */
    public boolean getMyVIPStatus() {
        return myVIPStatus;
    }
    
    @Override
    /**
     * String representation of the object
     * 
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final String tempVar = ", ";
        sb.append(getClass().getSimpleName());
        sb.append(" (");
        sb.append(getMyName());
        sb.append(tempVar);
        sb.append(getMyPassword());
        sb.append(tempVar);
        sb.append(getMyVIPStatus());
        sb.append(")");
        
        return sb.toString();
    }
    
    /**
     * Appropriate equals representation of the object.
     * 
     */
    @Override
    public boolean equals(final Object theOtherUser) {
        //boolean variable for single exit.
        boolean result = true;
        
        if (this == theOtherUser) { // location case
            result = true; 
        } else if (theOtherUser == null) { //null case
            result = false;
        } else if (this.getClass() != theOtherUser.getClass()) { // class case
            result = false;
        } else {
            final User other = (User) theOtherUser; // case to test fields of different point objects
            result = this.myName.equals(other.myName)
                            && (this.myPassword.equals(other.myPassword)) 
                            && this.myVIPStatus == other.myVIPStatus;
        }
        return result;  
    }
    
    public int hashCode() {
        // method will override hashcode() method for the fields uses in our previous methods.
        return Objects.hash(myName, myPassword, myVIPStatus);
    }
}