/** TCSS 305
 * 
 * Test class for Registration class in the Vehicle Rental System.
 * 
 */

package tests;

import static org.junit.Assert.*;

import java.util.Map;
import model.Registration;
import model.User;
import org.junit.Before;
import org.junit.Test;
import utility.FileLoader;

/**
 * Test Class for Registration class. 
 * 
 * @author jthiara
 * @version FALL 2021
 */
public class RegistrationTest {
    
    /**
     * Private registration object.
     */
    private Registration myRegistration = new Registration();
    
    /**
     * A method to initialize text fixture before each test.
     */
    @Before
    public void setUp() {
        myRegistration = new Registration();
    }
    
    /**
     * Test method for {@link model.Registration#Registration()}.
     */
    @Test
    public void testRegistration() { // tests that the constructor is working correctly and initializing myUserList map with current users. 
        final Map<String, User> userList2 = FileLoader.readItemsFromFile("./resources/registeredusers.txt");
        assertEquals("Registration constructor fail", myRegistration.getMyUserList(), userList2);
    }

    /**
     * Test method for {@link model.Registration#getMyUserList()}.
     */
    @Test
    public void testGetMyUserList() { // tests that the getMyUserList method is working correctly and returns a correct myUserList map.
        final Map<String, User> userList2 = FileLoader.readItemsFromFile("./resources/registeredusers.txt");
        assertEquals("getMyUserList fail", userList2, myRegistration.getMyUserList());
    }
    
    /**
     * Test method for {@link model.Registration#login(String, String)}.
     */
    @Test
    public void testLogin() {
        final User tempUser = new User("kobe", "0001", true); // create temporary user that we can use to test login
        myRegistration.register(tempUser);
        assertTrue("login fail", myRegistration.login("kobe", "0001")); // login should return true if the method is functioning correctly
        assertFalse("login fail", myRegistration.login("wrong", "info"));
        assertFalse("login fail", myRegistration.login("wrong", "0001"));
        assertFalse("login fail", myRegistration.login("kobe", "info"));
    }
    
    /**
     * Test method for {@link model.Registration#login(String, String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginStringStringIllegalNameArgument() { // exception should be thrown when passed an empty name.
        myRegistration.login("", "0900");
    }
    
    /**
     * Test method for {@link model.Registration#login(String, String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginStringStringIllegalPasswordArgument() { // exception should be thrown when passed an empty password.
        myRegistration.login("joe", "");
    }
    
    
    /**
     * Test method for {@link model.Registration#login(String, String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testLoginStringStringNameNull() { // tests that null exception is thrown when we pass a null value as a name.
        myRegistration.login(null, "0970");
    }
    
    @Test(expected = NullPointerException.class)
    public void testLoginStringStringPasswordNull() { // tests that null exception is thrown when we pass a null value as a password.
        myRegistration.login("joe", null);
    }
    
    /**
     * Test method for {@link model.Registration#register(model.User)}.
     */
    @Test
    public void testRegister() { 
        final User tempUser = new User("naruto", "0011", true); // temporary user to test our register method
        myRegistration.register(tempUser);
        assertEquals("equals method fail", true, myRegistration.login("naruto", "0011")); // new user should be found in the userList map.
    }
    
    /**
     * Test method for {@link model.Registration#Register(model.User)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserIllegalNameArgument() { //tests that exception is thrown at appropriate time when name is empty string.
        myRegistration.register(new User("", "7605"));
    }
    
    /**
     * Test method for {@link model.Registration#Register(model.User)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserIllegalPasswordArgument() { //tests that exception is thrown at appropriate time when password is empty string.
        myRegistration.register(new User("joe", ""));
    }
    
    /**
     * Test method for {@link model.Registration#register(model.User)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterUserNameNull() { // tests that null exception is thrown when passed a null value as a name.
        myRegistration.register(new User(null, "0970"));
    }
    
    /**
     * Test method for {@link model.Registration#register(model.User)}.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterUserPasswordNull() { // tests that null exception is thrown when passed a null value as a password.
        myRegistration.register(new User("luis", null));
    }
    
    /**
     * Test method for {@link model.Registration#clear()}.
     */
    @Test
    public void testClear() { // userList map should be empty when this method is called.
        myRegistration.clear();
        assertTrue(myRegistration.getMyUserList().isEmpty());
    }
    
    /**
     * Test method for {@link model.Registration#toString()}.
     */
    @Test
    public void testToString() { // tests that a string representation of the userList is returned in the correct form.
        final String tempVar = "Registered UserList " + myRegistration.getMyUserList().toString();
        assertEquals("toString method fail", tempVar, myRegistration.toString());
        
    }
}
