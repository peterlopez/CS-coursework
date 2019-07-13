/**
* Complex number
*
* @author Peter Lopez
* @version Program 1B
*/
public class ComplexNumber {
   
   private double real;
   private double imaginary;
   
   public ComplexNumber() {
      real = 0;
      imaginary = 0;
   }
   public ComplexNumber(double realVal, double imaginaryVal) {
      real = realVal;
      imaginary = imaginaryVal;
   }
   
   public ComplexNumber add(ComplexNumber in) {
      double realIn = in.getReal();
      double imaginaryIn = in.getImaginary();
      
      // Perform operations on summands
      double realResult = real + realIn;
      double imaginaryResult = imaginary + imaginaryIn;
      
      ComplexNumber resultObject = new ComplexNumber(
         realResult, imaginaryResult);
      
      return resultObject;
   }
   public ComplexNumber sub(ComplexNumber in) {
      double realIn = in.getReal();
      double imaginaryIn = in.getImaginary();
      
      // Perform operations on values
      double realResult = real - realIn;
      double imaginaryResult = imaginary - imaginaryIn;
      
      ComplexNumber result = new ComplexNumber(
         realResult, imaginaryResult);
      
      return result;
   }
   public ComplexNumber mul(ComplexNumber in) {
      double realIn = in.getReal();
      double imaginaryIn = in.getImaginary();
      
      // Perform operations on values
      double realResult = (real * realIn) - (imaginary * imaginaryIn);
      double imaginaryResult = (imaginary * realIn) + (real * imaginaryIn);
      
      ComplexNumber result = new ComplexNumber(
         realResult, imaginaryResult);
      
      return result;
   }
   public ComplexNumber div(ComplexNumber in) {
      double realIn = in.getReal();
      double imaginaryIn = in.getImaginary();
      
      // Perform operations on values
      double realResult = ((real * realIn) + (imaginary * imaginaryIn)) /
         ((realIn * realIn) + (imaginaryIn * imaginaryIn));
      double imaginaryResult = ((imaginary * realIn) - (real * imaginaryIn)) /
         ((realIn * realIn) + (imaginaryIn * imaginaryIn));
      
      ComplexNumber result = new ComplexNumber(
         realResult, imaginaryResult);
      
      return result;
   }
   
   public double getReal() {
      return real;
   }
   public double getImaginary() {
      return imaginary;
   }
   public java.lang.String toString() {
      if(imaginary < 0) {
         return "("+real+""+imaginary+"i)";
      }
      
      return "("+real+"+"+imaginary+"i)";
   }
   
   public boolean equals(ComplexNumber in) {
      if(in.getReal() == real && in.getImaginary() == imaginary) {
         return true;
      }
      return false;
   }
}