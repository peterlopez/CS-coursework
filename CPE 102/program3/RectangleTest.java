
public class RectangleTest {
   
   public static final double EPSILON = 0.00001;
   
   public static void main(String[] args) {
      
      testConstructor();
      testGetWidth();
      testSetWidth();
      testEquals();
      testGetArea();
      testGetColor();
      testSetColor();
      testGetFilled();
      testSetFilled();
      testSetPosition();
      testMove();
   }
   
   public static void testConstructor() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle = new Rectangle(2, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), true);
      
      // Test each property
      sUnit.assertEquals(2, myRectangle.getWidth(), EPSILON);
      sUnit.assertEquals(4, myRectangle.getHeight(), EPSILON);
      sUnit.assertEqualsObject(new java.awt.Point(5,2), myRectangle.getPosition());
      sUnit.assertEqualsObject(new java.awt.Color(5,2,1), myRectangle.getColor());
      sUnit.assertTrue(myRectangle.getFilled());
   }
   public static void testGetWidth() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle1 = new Rectangle(2, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), true);
      sUnit.assertEquals(2, myRectangle1.getWidth(), EPSILON);
      
      Rectangle myRectangle2 = new Rectangle(99, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), true);
      sUnit.assertEquals(99, myRectangle2.getWidth(), EPSILON);
   }
   public static void testSetWidth() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle = new Rectangle(88, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), true);
      myRectangle.setWidth(2);
      sUnit.assertEquals(2, myRectangle.getWidth(), EPSILON);
      
      myRectangle.setWidth(101);
      sUnit.assertEquals(101, myRectangle.getWidth(), EPSILON);
   }
   public static void testEquals() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle = new Rectangle(2, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      SUnit testObject = new SUnit();
      sUnit.assertFalse(myRectangle.equals(testObject));
      
      Rectangle anotherRectangle = new Rectangle(2, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertTrue(myRectangle.equals(anotherRectangle));
   }
   public static void testGetArea() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle1 = new Rectangle(2, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertEquals(8, myRectangle1.getArea(), EPSILON);
      
      Rectangle myRectangle2 = new Rectangle(97, 1, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertEquals(97, myRectangle2.getArea(), EPSILON);
   }
   public static void testGetColor() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle1 = new Rectangle(2, 4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      sUnit.assertEqualsObject(new java.awt.Color(5,2,1), myRectangle1.getColor());
      
      Rectangle myRectangle2 = new Rectangle(2, 4, new java.awt.Point(1,1), new java.awt.Color(98,21,44), true);
      sUnit.assertEqualsObject(new java.awt.Color(98,21,44), myRectangle2.getColor());
   }
   public static void testSetColor() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle = new Rectangle(88, 4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), true);
      myRectangle.setColor(new java.awt.Color(6,123,54));
      sUnit.assertEqualsObject(new java.awt.Color(6,123,54), myRectangle.getColor());
      
      myRectangle.setColor(new java.awt.Color(0,0,0));
      sUnit.assertEqualsObject(new java.awt.Color(0,0,0), myRectangle.getColor());
   }
   public static void testGetFilled() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle1 = new Rectangle(2, 4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      sUnit.assertTrue(myRectangle1.getFilled());
      
      Rectangle myRectangle2 = new Rectangle(2, 4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), false);
      sUnit.assertFalse(myRectangle2.getFilled());
   }
   public static void testSetFilled() {
      SUnit sUnit = new SUnit();
      
      // Start with true
      Rectangle myRectangle = new Rectangle(2, 4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      sUnit.assertTrue(myRectangle.getFilled());
      
      // Set to false
      myRectangle.setFilled(false);
      
      sUnit.assertFalse(myRectangle.getFilled());
   }
   public static void testSetPosition() {
      SUnit sUnit = new SUnit();
      
      Rectangle myRectangle = new Rectangle(2, 4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      
      myRectangle.setPosition(new java.awt.Point(99, -11));
      java.awt.Point currentPosition = myRectangle.getPosition();
      sUnit.assertEquals(99, currentPosition.x, EPSILON);
      sUnit.assertEquals(-11, currentPosition.y, EPSILON);
      
      myRectangle.setPosition(new java.awt.Point(3, 1));
      currentPosition = myRectangle.getPosition();
      sUnit.assertEquals(3, currentPosition.x, EPSILON);
      sUnit.assertEquals(1, currentPosition.y, EPSILON);
   }
   public static void testMove() {
      SUnit sUnit = new SUnit();      
      Rectangle myRectangle = new Rectangle(2, 4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), false);

      myRectangle.move(new java.awt.Point(-1, 0));
      java.awt.Point currentPosition = myRectangle.getPosition();
      sUnit.assertEquals(0, currentPosition.x, EPSILON);
      sUnit.assertEquals(1, currentPosition.y, EPSILON);
      
      myRectangle.move(new java.awt.Point(0, -22));
      currentPosition = myRectangle.getPosition();
      sUnit.assertEquals(0, currentPosition.x, EPSILON);
      sUnit.assertEquals(-21, currentPosition.y, EPSILON);
   }
}