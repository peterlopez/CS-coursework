
public class TriangleTest {
   
   public static final double EPSILON = 0.00001;
   
   public static void main(String[] args) {
      
      testConstructor();
      testEquals();
      
      testGetVertexA();
      testGetVertexB();
      testGetVertexC();
      testSetVertexA();
      testSetVertexB();
      testSetVertexC();
      
      testGetArea();
      testGetColor();
      testSetColor();
      testGetFilled();
      testSetFilled();
      testMove();
   }
   
   public static void testConstructor() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      // Test each property
      sUnit.assertEqualsObject(new java.awt.Point(2,1), myTriangle.getVertexA());
      sUnit.assertEqualsObject(new java.awt.Point(3,2), myTriangle.getVertexB());
      sUnit.assertEqualsObject(new java.awt.Point(3,2), myTriangle.getVertexB());
      sUnit.assertEqualsObject(new java.awt.Color(5,2,1), myTriangle.getColor());
      sUnit.assertTrue(myTriangle.getFilled());
   }
   public static void testEquals() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);

      SUnit testObject = new SUnit();
      sUnit.assertFalse(myTriangle.equals(testObject));

      Triangle anotherTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      sUnit.assertEqualsObject(anotherTriangle, myTriangle);
   }
   public static void testGetVertexA() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEqualsObject(a, myTriangle.getVertexA());
      
      a = new java.awt.Point(99,13);
      b = new java.awt.Point(3,2);
      c = new java.awt.Point(4,3);
      myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEqualsObject(a, myTriangle.getVertexA());
   }
   public static void testGetVertexB() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEqualsObject(b, myTriangle.getVertexB());
      
      a = new java.awt.Point(99,13);
      b = new java.awt.Point(3,2);
      c = new java.awt.Point(4,3);
      myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEqualsObject(b, myTriangle.getVertexB());
   }
   public static void testGetVertexC() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEqualsObject(c, myTriangle.getVertexC());
      
      a = new java.awt.Point(99,13);
      b = new java.awt.Point(3,2);
      c = new java.awt.Point(4,3);
      myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEqualsObject(c, myTriangle.getVertexC());
   }
   public static void testSetVertexA() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      myTriangle.setVertexA(new java.awt.Point(3,4));
      sUnit.assertEqualsObject(new java.awt.Point(3,4), myTriangle.getVertexA());
      
      myTriangle.setVertexA(new java.awt.Point(0,99));
      sUnit.assertEqualsObject(new java.awt.Point(0,99), myTriangle.getVertexA());
   }
   public static void testSetVertexB() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      myTriangle.setVertexB(new java.awt.Point(21,45));
      sUnit.assertEqualsObject(new java.awt.Point(21,45), myTriangle.getVertexB());
      
      myTriangle.setVertexB(new java.awt.Point(2,-1));
      sUnit.assertEqualsObject(new java.awt.Point(2,-1), myTriangle.getVertexB());
   }
   public static void testSetVertexC() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      myTriangle.setVertexB(new java.awt.Point(1,54));
      sUnit.assertEqualsObject(new java.awt.Point(1,54), myTriangle.getVertexB());
      
      myTriangle.setVertexB(new java.awt.Point(22,-2));
      sUnit.assertEqualsObject(new java.awt.Point(22,-2), myTriangle.getVertexB());
   }
   public static void testGetArea() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle1 = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEquals(0.0, myTriangle1.getArea(), EPSILON);
      
      a = new java.awt.Point(-1,2);
      b = new java.awt.Point(5,2);
      c = new java.awt.Point(3,4);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      sUnit.assertEquals(0.0, myTriangle1.getArea(), EPSILON);
   }
   public static void testGetColor() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      
      Triangle myTriangle1 = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      sUnit.assertEqualsObject(new java.awt.Color(5,2,1), myTriangle1.getColor());
      
      Triangle myTriangle2 = new Triangle(a, b, c, new java.awt.Color(1,0,1), true);
      sUnit.assertEqualsObject(new java.awt.Color(1,0,1), myTriangle2.getColor());
   }
   public static void testSetColor() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      myTriangle.setColor(new java.awt.Color(3,1,2));
      sUnit.assertEqualsObject(new java.awt.Color(3,1,2), myTriangle.getColor());
      
      myTriangle.setColor(new java.awt.Color(99,1,0));
      sUnit.assertEqualsObject(new java.awt.Color(99,1,0), myTriangle.getColor());
   }
   public static void testGetFilled() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      
      Triangle myTriangle1 = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      sUnit.assertTrue(myTriangle1.getFilled());
      
      Triangle myTriangle2 = new Triangle(a, b, c, new java.awt.Color(1,0,1), false);
      sUnit.assertFalse(myTriangle2.getFilled());
   }
   public static void testSetFilled() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      myTriangle.setFilled(false);
      sUnit.assertFalse(myTriangle.getFilled());
      
      myTriangle.setFilled(true);
      sUnit.assertTrue(myTriangle.getFilled());
   }
   public static void testMove() {
      SUnit sUnit = new SUnit();
      
      java.awt.Point a = new java.awt.Point(2,1);
      java.awt.Point b = new java.awt.Point(3,2);
      java.awt.Point c = new java.awt.Point(4,3);
      Triangle myTriangle = new Triangle(a, b, c, new java.awt.Color(5,2,1), true);
      
      myTriangle.move(new java.awt.Point(2,2));
      
      sUnit.assertEqualsObject(new java.awt.Point(4,3), myTriangle.getVertexA());
      sUnit.assertEqualsObject(new java.awt.Point(5,4), myTriangle.getVertexB());
      sUnit.assertEqualsObject(new java.awt.Point(6,5), myTriangle.getVertexC());
      
      myTriangle.move(new java.awt.Point(-2,6));
      
      sUnit.assertEqualsObject(new java.awt.Point(2,9), myTriangle.getVertexA());
      sUnit.assertEqualsObject(new java.awt.Point(3,10), myTriangle.getVertexB());
      sUnit.assertEqualsObject(new java.awt.Point(4,11), myTriangle.getVertexC());
   }
}