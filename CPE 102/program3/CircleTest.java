
public class CircleTest {
   
   public static final double EPSILON = 0.00001;
   
   public static void main(String[] args) {
      
      testConstructor();
      testGetRadius();
      testSetRadius();
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
      
      Circle myCircle = new Circle(2.4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      
      // Test each property
      sUnit.assertEquals(2.4, myCircle.getRadius(), EPSILON);
      sUnit.assertEqualsObject(new java.awt.Point(5,2), myCircle.getPosition());
      sUnit.assertEqualsObject(new java.awt.Color(5,2,1), myCircle.getColor());
      sUnit.assertFalse(myCircle.getFilled());
   }
   public static void testGetRadius() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle1 = new Circle(99.99, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertEquals(99.99, myCircle1.getRadius(), EPSILON);
      
      Circle myCircle2 = new Circle(0.001, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertEquals(0.001, myCircle2.getRadius(), EPSILON);
   }
   public static void testSetRadius() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      myCircle.setRadius(0.0001);
      
      sUnit.assertEquals(0.0001, myCircle.getRadius(), EPSILON);
      
      myCircle.setRadius(99);
      sUnit.assertEquals(99, myCircle.getRadius(), EPSILON);
   }
   public static void testEquals() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle = new Circle(2.4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      SUnit testObject = new SUnit();
      sUnit.assertFalse(myCircle.equals(testObject));
      
      Circle anotherCircle = new Circle(2.4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertTrue(myCircle.equals(anotherCircle));
   }
   public static void testGetArea() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle1 = new Circle(2.4, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertEquals(18.095573684677, myCircle1.getArea(), EPSILON);
      
      Circle myCircle2 = new Circle(57.1111, new java.awt.Point(5,2), new java.awt.Color(5,2,1), false);
      sUnit.assertEquals(10246.862836446, myCircle2.getArea(), EPSILON);
   }
   public static void testGetColor() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle1 = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      sUnit.assertEqualsObject(new java.awt.Color(5,2,1), myCircle1.getColor());
      
      Circle myCircle2 = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(1,0,1), true);
      sUnit.assertEqualsObject(new java.awt.Color(1,0,1), myCircle2.getColor());
   }
   public static void testSetColor() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      myCircle.setColor(new java.awt.Color(1,2,3));
      
      sUnit.assertEqualsObject(new java.awt.Color(1,2,3), myCircle.getColor());
      
      myCircle.setColor(new java.awt.Color(0, 0, 0));
      sUnit.assertEqualsObject(new java.awt.Color(0,0,0), myCircle.getColor());
   }
   public static void testGetFilled() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle1 = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      sUnit.assertTrue(myCircle1.getFilled());
      
      Circle myCircle2 = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), false);
      sUnit.assertFalse(myCircle2.getFilled());
   }
   public static void testSetFilled() {
      SUnit sUnit = new SUnit();
      
      // Start with false
      Circle myCircle1 = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), false);
      
      // Set to true and test
      myCircle1.setFilled(true);
      sUnit.assertTrue(myCircle1.getFilled());
      
      // Start with true
      Circle myCircle2 = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      
      // Set to false and test
      myCircle2.setFilled(false);
      sUnit.assertFalse(myCircle2.getFilled());
   }
   public static void testSetPosition() {
      SUnit sUnit = new SUnit();
      
      Circle myCircle = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), true);
      
      myCircle.setPosition(new java.awt.Point(99, -11));
      java.awt.Point currentPosition = myCircle.getPosition();
      sUnit.assertEquals(99, currentPosition.x, EPSILON);
      sUnit.assertEquals(-11, currentPosition.y, EPSILON);
      
      myCircle.setPosition(new java.awt.Point(3, 1));
      currentPosition = myCircle.getPosition();
      sUnit.assertEquals(3, currentPosition.x, EPSILON);
      sUnit.assertEquals(1, currentPosition.y, EPSILON);
   }
   public static void testMove() {
      SUnit sUnit = new SUnit();      
      Circle myCircle = new Circle(2.4, new java.awt.Point(1,1), new java.awt.Color(5,2,1), false);

      myCircle.move(new java.awt.Point(2,2));
      java.awt.Point currentPosition = myCircle.getPosition();
      sUnit.assertEquals(3, currentPosition.x, EPSILON);
      sUnit.assertEquals(3, currentPosition.y, EPSILON);
      
      myCircle.move(new java.awt.Point(-1, 2));
      currentPosition = myCircle.getPosition();
      sUnit.assertEquals(2, currentPosition.x, EPSILON);
      sUnit.assertEquals(5, currentPosition.y, EPSILON);
   }
}