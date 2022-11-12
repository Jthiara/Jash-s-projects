/** TCSS 305
 * 
 * Class demonstrates testing using a point class. Class covers every method in the Point.java class and creates extra methods when needed. 
 */
package exercisesTests;

import static org.junit.Assert.*;

import exercises.Point;
import java.util.ArrayList;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jthiara
 * @version FALL 2021
 *
 */
public class PointTest {
    
    /**
     * Tolerance used to compare double values.
     */
    private static final double TOLERANCE = 0.000001;
    
    /**
     * Private point object.
     */
    private Point myPoint1;
    
    /**
     * Private point object.
     */
    private Point myPoint2;
    
    /**
     * This method will initialize Points to observe for our test cases.
     */
    @Before
    public void setUp() {
        myPoint1 = new Point();
        myPoint2 = new Point(4, 2);
    }
    
    /**
     * Test method for {@link exercises.Point#Point()}.
     */
    @Test
    public void testPoint() { // test for parameterless constructor
        assertNotNull("Point object is null", myPoint1);
        assertEquals("default values are incorrect", 0, myPoint1.getX(), TOLERANCE);
        assertEquals("default values are incorrect", 0, myPoint1.getY(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#Point(double, double)}.
     */
    @Test
    public void testPointDoubleDouble() { // tests whether the 2 param constructor is working correctly
        assertEquals("faulty parameter constructor", 4, myPoint2.getX(), TOLERANCE);
        assertEquals("faulty parameter constructor", 2, myPoint2.getY(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#Point()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalXArgument() { //tests that exception is thrown at appropriate time when x is negative.
        myPoint2 = new Point(-6, 1);
    }
    
    /**
     * Test method for {@link exercises.Point#Point()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalYArgument() { //tests that exception is thrown at appropriate time when y is negative.
        myPoint2 = new Point(1, -6);
    }
    
    /**
     * Test method for {@link exercises.Point#Point(exercises.Point)}.
     */
    @Test
    public void testPointPoint() { // test case for point constructor that takes a point object as a parameter.
        final Point p = new Point(myPoint2); // object to experiment our test case.
        assertNotNull("copy constructor fail/ null value", p);
        assertEquals("copy constructor fail/ incorrect x", p.getX(), myPoint2.getX(), TOLERANCE);
        assertEquals("copy constructor fail/ incorrect Y", p.getY(), myPoint2.getY(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#Point(exercises.Point)}.
     */
    @Test(expected = NullPointerException.class)
    public void testPointPointNull() { // tests that null exception is thrown when we create a null point object.
        myPoint2 = new Point(null);
    }
    
    /**
     * Test method for {@link exercises.Point#getX()}.
     */
    @Test
    public void testGetX() { //test case for get x.
        assertNotNull("null point object", myPoint1);
        assertNotNull("null point object", myPoint2);
        assertEquals("incorrect default value", 0, myPoint1.getX(), TOLERANCE);
        assertEquals("incorrect default value", 4, myPoint2.getX(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#getY()}.
     */
    @Test
    public void testGetY() { //test case for get y.
        assertNotNull("null point object", myPoint1);
        assertNotNull("null point object", myPoint2);
        assertEquals("incorrect default value", 0, myPoint1.getY(), TOLERANCE);
        assertEquals("incorrect default value", 2, myPoint2.getY(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#setLocation(double, double)}.
     */
    @Test
    public void testSetLocation() { //test case for setLocation method
        myPoint1.setLocation(6, 20);
        
        assertEquals("set location has set up incorrect value for x", 6, myPoint1.getX(), TOLERANCE);
        assertEquals("set location has set up incorrect value for y", 20, myPoint1.getY(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#calculateDistance(exercises.Point)}.
     */
    @Test
    public void testCalculateDistance() { //test case for calculate distance.
        assertEquals("test calculate method faulty", 10, myPoint1.calculateDistance(new Point(6, 8)), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#calculateDistance(exercises.Point)}.
     */
    @Test
    public void testCalcuateDistanceOrigin() { //test case for calculate distance method when point is at origin.
        assertEquals("Calculate distance method failed for origin calculation", 0, myPoint1.calculateDistance(new Point(0, 0)), TOLERANCE);
    }
    
    /**
     * Test method for calculateDistance when given a null parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testCalculateDistanceNull() { // Test case ensures that null exception is thrown for calculate distance method.
        myPoint1.calculateDistance(null);
    }
    
    /**
     * Test method for {@link exercises.Point#translate(double, double)}.
     */
    @Test
    public void testTranslate() { // test case to ensure translate method is working properly.
        myPoint1.translate(1, 1);
        assertEquals("failed to translate points x value", 1, myPoint1.getX(), TOLERANCE);
        assertEquals("failed to translate points Y value", 1, myPoint1.getY(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#getRho()}.
     */
    @Test
    public void testGetRho() { // test case to ensure getRho method is calculating properly
        assertEquals("getRHO method has incorrect functionality.", 3.605551275, new Point(2, 3).getRho(), TOLERANCE); // using new point for easy rho
    }
    
    /**
     * Test method for {@link exercises.Point#getThetaInRadians()}.
     */
    @Test
    public void testGetThetaInRadians() { //test case to ensure getThetaInRadians() is functioning properly.
        assertEquals("get theta in radians method fail", 
                     Math.atan2(myPoint2.getY(), myPoint2.getX()), myPoint2.getThetaInRadians(), TOLERANCE);
    }
    
    /**
     * Test method for {@link exercises.Point#getThetaInDegrees()}.
     */
    @Test
    public void testGetThetaInDegrees() { // test case to ensure getThetaInDegrees() is functioning properly.
        assertEquals("get theta in degrees fail",
                     Math.atan2(myPoint2.getY(), myPoint2.getX()) * Point.HALF_CIRCLE / Math.PI, myPoint2.getThetaInDegrees(), TOLERANCE);
    }

    /**
     * Test method for {@link exercises.Point#toString()}.
     */
    @Test
    public void testToString() { // test case to ensure toString() is function properly and creating string representations in desired form.
        assertEquals("toString() method incorrect", "Point (0.00, 0.00)", myPoint1.toString());
        assertEquals("toString() method incorrect", "Point (4.00, 2.00)", myPoint2.toString());
    }
    
    /**
     * Test method for {@link exercises.Point#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectPositive() { // test case for positive equalsObject() outcomes
        final Point p2 = new Point(myPoint2);
        assertEquals("equals method failed/ positive", p2, myPoint2); // test case for same values of x and y fields
        assertEquals("equals method failed/ positive/same object", myPoint2, myPoint2); // test case for when the objects are identical
    }
    
    /**
     * Test method for {@link exercises.Point#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObjectNegative() { // test case for negative equalsObject() outcomes.
        assertNotEquals("equals method failed/negative/null", myPoint2, null); //null outcome
        assertNotEquals("equals method failed/negative/different fields", myPoint2, myPoint1); // different fields outcome
        assertNotEquals("equals method failed/negative/", myPoint2, new ArrayList<String>()); //compared to completely different object
    }

    /**
     * Test method for {@link exercises.Point#hashCode()}.
     */
    @Test
    public void testHashCode() { // test case for hash code() method.
        assertEquals("hash code method fail", Objects.hash(myPoint1.getX(), myPoint1.getY()), myPoint1.hashCode()); //same objects should have same hash code
        
        final Point p3 = new Point(myPoint1); //point with same fields should have same hash code.
        assertEquals("hash code method fail/same state case", p3.hashCode(), myPoint1.hashCode());
        
    }

}
