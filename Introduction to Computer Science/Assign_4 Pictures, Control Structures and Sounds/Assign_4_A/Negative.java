package Assign_4_A;


import Media.*;                  // for Picture and PictureDisplayer
import java.awt.*;               // for Color objects and methods
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants


/** This class ...
  *
  * @author Micah
  * @version 1.0 (2018/11//02)                                                        */

public class Negative
 {
  PictureDisplayer display;
  Picture pic;
  
  
  
    
    
    // instance variables
    
    
    /** This constructor causes the r,g and b values of each pixel to change into its negative equivalent value                                                   */
    
    public Negative( ) {
      display = new PictureDisplayer();
      pic = new Picture();
      display.placePicture(pic);
      
      display.waitForUser();
      makeNegative(pic);
      display.close();

        // local variables
        
        // statements including call to method
        
    }; // constructor
    
    
    // method that makes the r,g and b value of each pixel into its negaive equivalant
    
    private void makeNegative(Picture aPic){
      Pixel pixel;
      int r;
      int g;
      int b;
      
      while(aPic.hasNext()){
        pixel = aPic.next();
        g = (int)(pixel.getGreen()*-1);
        pixel.setGreen(g);
        b = (int)(pixel.getBlue()*-1);
        pixel.setBlue(b);
        r = (int)(pixel.getRed()*-1);
        pixel.setRed(r);
        
      }
    }
    
   
    
    
    

    
    
    public static void main ( String[] args ) { Negative s = new Negative(); };
    
    
} // Negative

