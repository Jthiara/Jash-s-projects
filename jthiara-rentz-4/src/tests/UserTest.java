/** TCSS 305
 * 
 * Test class for User class in the Vehicle Rental System..
 * 
 */

package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Objects;
import model.User;
import org.junit.Before;
import org.junit.Test;


/**
 * Test Class for User class..
 *  
 * @author jthiara
 * @version Fall 2021
 */

public class UserTest {
    
    /**
     * Private User object.
     */
    private User myUser;
    
    /**
     * Private User object.
     */
    private User myUser2;
    
    /**
     * A method to initialize text fixtures before each test.
     */
    @Before
    public void setUp() {
        myUser = new User("jash", "6704");
        myUser2 = new User("josh", "4067", true);
    }

    /**
     * Test method for {@link model.User#User(String, String)}.
     */
    @Test
    public void testUserStringString() {
        assertNotNull("UserStringString constructor fail/null", myUser);
        assertEquals("UserStringString constructor fail/incorrect name", "jash", myUser.getMyName());
        assertEquals("UserStringString constructor fail/incorrect password", "6704", myUser.getMyPassword());
        assertEquals("UserStringString constructor fail/incorrect vip status", false, myUser.getMyVIPStatus());
    }
    
    /**
     * Test method for {@link model.User#User(String, String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserStringStringIllegalNameArgument() { //tests that exception is thrown at appropriate time when name is empty string.
        myUser = new User("", "6704");
    }
    
    /**
     * Test method for {@link model.User#User(String, String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserStringStringIllegalPasswordArgument() { //tests that exception is thrown at appropriate time when password is empty string.
        myUser = new User("jash", "");
    }
    
    /**
     * Test method for {@link model.User#User(String, String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testUserStringStringNameNull() { // tests that null exception is thrown when we pass a null value as a name.
        myUser = new User(null, "6704");
    }
    
    /**
     * Test method for {@link model.User#User(String, String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testUserStringStringPasswordNull() { // tests that null exception is thrown when we pass a null value as a password.
        myUser = new User("6704", null);
    }
    
    /**
     * Test method for {@link model.User#User(String, String, boolean)}.
     */
    @Test
    public void testUserStringStringBoolean() {
        assertNotNull("UserStringStringBoolean fail/null", myUser2);
        assertEquals("UserStringStringBolean fail/name", "josh", myUser2.getMyName());
        assertEquals("UserStringStringBolean fail/password", "4067", myUser2.getMyPassword());
        assertEquals("UserStringStringBolean fail/vipstatus", true, myUser2.getMyVIPStatus());
    }
    
    /**
     * Test method for {@link model.User#User(String, String, boolean)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserStringStringBooleanIllegalNameArgument() {
        myUser2 = new User("", "4067", true); // exception should be thrown for empty name string.
    }
    
    /**
     * Test method for {@link model.User#User(String, String, boolean)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserStringStringBooleanIllegalPasswordArgument() { 
        myUser2 = new User("josh", "", false); // exception should be thrown for empty password string.
    }

    /**
     * Test method for {@link exercises.User#User(String, String, boolean)}.
     */
    @Test(expected = NullPointerException.class)
    public void testUserStringStringBooleanNameNull() { 
        myUser2 = new User(null, "4067"); // null exception should  be thrown when passed a null name value.
    }
    
    /**
     * Test method for {@link exercises.User#User(String, String, boolean)}.
     */
    @Test(expected = NullPointerException.class)
    public void testUserStringStringBooleanPasswordNull() { 
        myUser2 = new User("Josh", null); // null exception should  be thrown when passed a null password value.
    }
    
    /**
     * Test method for {@link model.User#getMyName()}.
     */
    @Test
    public void testGetMyName() { //test case for get name.
        assertEquals("josh", myUser2.getMyName());
    }
    
    /**
     * Test method for {@link model.User#getMyPassword()}.
     */
    @Test
    public void testGetMyPassword() { //test case for get password.
        assertEquals("4067", myUser2.getMyPassword());
    }
    
    /**
     * Test method for {@link model.User#getMyVIPStatus()}.
     */
    @Test
    public void testGetMyVIPStatus() { //test case for get name.
        assertEquals(true, myUser2.getMyVIPStatus());
    }
    
    @Test
    public void testToString() { // tests that the user.toString method is working correctly. 
        assertEquals("ToString() method fail", "User (jash, 6704, false)", myUser.toString());
    }
    
    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectPositive() { // test case for positive equalsObject() outcomes
        final User user2 = new User("josh", "4067", true); // create temporary user class that is identical to myUser2
        assertEquals("equals method failed/ positive", user2, myUser2); // test case for same values of name, password, vip status.
        assertEquals("equals method failed/ positive/same object", myUser2, myUser2); // test case for when the objects are identical.
    }
    
    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectNegative() { // test case for negative equalsObject() outcomes.
        assertNotEquals("equals method failed/negative/null", myUser2, null); // case: null outcome
        assertNotEquals("equals method failed/negative/different fields", myUser2, myUser); // case: different name/password comparison
        assertNotEquals("equals method failed/negative/different object", myUser2, new ArrayList<String>()); 
        // case: compared to completely different object
    }
    
    /**
     * Test method for {@link model.User#hashCode()}.
     */
    @Test
    public void testHashCode() { // tests that user.hashCode() method is working correctly.
        assertEquals("hash code method fail", Objects.hash(myUser.getMyName(), myUser.getMyPassword(), myUser.getMyVIPStatus()),
                                                           myUser.hashCode()); // case demonstrated with same object
        
        final User user3 = new User("josh", "4067", true); // User objects with same username/password should have same hash code.
        assertEquals("hash code method fail/same state case", user3.hashCode(), myUser2.hashCode());
    }

}
