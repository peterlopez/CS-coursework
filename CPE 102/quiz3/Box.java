import java.util.*;
import java.io.*;

public class Box {
   private double l, w, h, weight;
   
   public Box(double l, double w, double h, double weight) {
      this.l = l;
      this.w = w;
      this.h = h;
      this.weight = weight;
   }
   
   public double getLength() {
      return l;
   }
   public double getWidth() {
      return w;
   }
   public double getHeight() {
      return h;
   }
   public double getWeight() {
      return weight;
   }
   
   public double getVolume() {
      return l*w*h;
   }
   public double getMaxDimension() {
      double result = 0;
      
      if(l > result) {
         result = l;
      }
      if(w > result) {
         result = w;
      }
      if(h > result) {
         result = h;
      }
      return result;
   }
   
   public boolean equals(Box other) {
      if(other == null) {
         return false;
      }
      if(other.getClass() != this.getClass()) {
         return false;
      }
      if(other.l != l || other.h != h || other.w != w || other.weight != weight) {
         return false;
      }
      return true;
   }
   
   public static void write(String fileName, ArrayList<Box> boxes) throws IOException {
      FileOutputStream f = null;
      DataOutputStream writer = null;
      
      // Open file and create writer
      try {
         f = new FileOutputStream(new File(fileName));
         writer = new DataOutputStream(f);
      }
      catch(IOException e) {
         throw new IOException();
      }
      
      // Write length, width, height, and weight to file
      for(int i=0; i < boxes.size(); i++) {
         try {
            writer.writeDouble(boxes.get(i).l);
            writer.writeDouble(boxes.get(i).w);
            writer.writeDouble(boxes.get(i).h);
            writer.writeDouble(boxes.get(i).weight);
         }
         catch(IOException e) {
            throw new IOException();
         }
      }
   }
   
   public static ArrayList<Box> read(String fileName) throws FileNotFoundException, IOException, EOFException {
      ArrayList<Box> result = new ArrayList<Box>(1000000);
      FileInputStream f = null;
      DataInputStream reader = null;
      
      // Open file and create reader
      try {
         f = new FileInputStream(new File(fileName));
         reader = new DataInputStream(f);
      }
      catch(FileNotFoundException e) {
         throw new FileNotFoundException();
      }
      catch(IOException e) {
         throw new IOException();
      }
      
      // Set flag for end of file
      boolean finished = false;
      double l, w, h, weight;
      
      while(! finished) {
         try {
            l = reader.readDouble();
            w = reader.readDouble();
            h = reader.readDouble();
            weight = reader.readDouble();
            
            Box b = new Box(l, w, h, weight);
            result.add(b);
         }
         catch(EOFException e) {
            finished = true;
         }
         catch(IOException e) {
            throw new IOException();
         }
      }
      return result;
   }
}