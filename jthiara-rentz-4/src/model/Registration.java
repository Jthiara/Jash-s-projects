
/*
 * This file is the registration class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import utility.FileLoader;

/**
 * Represents User Sign-in Object.
 * 
 * Methods of this class throw NullPointerException if required parameters are null.
 * 
 * @author Jthiara
 * @version Fall 2021
 */

public class Registration {
    
    /**
     * User Storage File.
     */
    public static final String USERFILE_NAME = "./resources/registeredusers.txt";

    /**
     * Static Scanner field. 
     */
    private static Scanner console = new Scanner(System.in);
    
    /**
     * The registered user list for signin.
     */
    private final Map<String, User> myUserList;

    /**
     * Constructs a signin/registration system.
     * 
     */
    public Registration() {
        myUserList = FileLoader.readItemsFromFile(USERFILE_NAME);
    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public Map<String, User> getMyUserList() {
        return myUserList;
    }

    /**
     * display sign-in or registration options.
     * 
     * @return boolean
     */
    public boolean printSignin() {
        //Temporary variables to fix repetitive strings error.
        final String tempVar = "User Name:";
        final String tempVar2 = "Password:";
        final String holder = "**********************";
        
        System.out.print("Enter 1 or 2 (1. New Registration 2.) Login):"); //Initial user prompt 
        final String choice = console.next();
        System.out.println("You entered option " + choice);
        System.out.println();
        System.out.println(holder);
        System.out.println(" Enter Details");
        System.out.println(holder);
        
        if ("1".equals(choice)) { // Registration case.
            final Map<String, User> users = getMyUserList();
            
            System.out.print(tempVar);
            String name = console.next();
            
            //loops until user has picked a username that is not already taken.
            while (users.containsKey(name)) {
                System.out.print("User already exists, enter different user name:");
                name = console.next();
            }
            System.out.print(tempVar2);
            final String password = console.next();
            System.out.print("isVIP(true/false):");
            final boolean isVIP = console.nextBoolean();
            final User newUser = new User(name, password, isVIP);
            register(newUser); // call to register method, which will add our new user to the list. 
            System.out.print("Registration Successful");
            
        } else if ("2".equals(choice)) { // Login case (existing account)
            //variable that serves as exit case for loop
            boolean result = false;
            
            while (!result) { //loops until username and password are both correct. 
                System.out.print(tempVar);
                final String name = console.next();
                System.out.print(tempVar2);
                final String password = console.next();
                result = login(name, password); // calls the login method, where both username and password will be checked.
                
                if (result) {
                    System.out.println("Login Successfull");
                    System.out.println();
                } else {
                    System.out.println(); // User has failed to login and will be prompted to enter information again.
                    System.out.println("Wrong Credentials");
                }
            }
        }
        return true;
    }

    /**
     * Verify Sign-in procedure.
     * 
     * @param theUsername username for sign-in
     * @param thePassword password for signin
     * @return sign-in success
     */
    public boolean login(final String theUsername, final String thePassword) {
        // checks for null values
        Objects.requireNonNull(theUsername, thePassword);
        
        //checks for empty values such as "". 
        if (theUsername.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        //iterates through the set of usernames in myUserList
        for (String username : myUserList.keySet()) {
            // compares username/password provided with the usernames/passwords in the database
            if (theUsername.equals(username) && thePassword.equals(myUserList.get(username).getMyPassword())) {
                return true; // correct username and password case.
            }
        }
        return false; 
    }

    /**
     * Adds a user to the registered user list.
     * 
     * @param theUser an order to add to this shopping cart
     * @return register success
     */
    public boolean register(final User theUser) {
        //check for null arguments
        Objects.requireNonNull(theUser.getMyName(), theUser.getMyPassword());
        
        //checks for empty arguments
        if (theUser.getMyName().isEmpty() || theUser.getMyPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        //adds username and user to our map and to the file. 
        myUserList.put(theUser.getMyName(), theUser);
        FileLoader.writeUserToFile(USERFILE_NAME, theUser);
        
        return true;
    }

    /**
     * Empties the user list.
     */
    public void clear() {
        myUserList.clear();
    }   

    @Override
    /**
     * String representation of the object
     * 
     */
    public String toString() {
        return "Registered UserList " + myUserList.toString();
    }

}
