package Assign_1_A;


import Media.*;                  // for Turtle and TurtleDisplayer
import static Media.Turtle.*;    // for Turtle speeds
import static java.lang.Math.*;  // for Math constants and functions
import static java.awt.Color.*;  // for Color constants


/** This class is a program  that draws a set of 4 20x20 black squares at a distance of 20 units from each other using Turtle Graphics
  *
  * @author Micah Rose-Mighty
  * 
  * @version 1.0 (2018/09/15)                                                        */

public class Squares {
  
  private TurtleDisplayer  display;  // display to draw on
  private Turtle           yertle;   // turtle to do drawing
    
    
    
    /** This constructor draws a set of 4 20x20 black squares at a distance of 20 units from each other using Turtle Graphics                                                   */
    
    public Squares ( ) {
      
      display = new TurtleDisplayer();
      yertle = new Turtle();
      display.placeTurtle(yertle);
      yertle.setSpeed(Turtle.FAST);
      yertle.penUp();
      yertle.setPenWidth(10);
      yertle.left(PI);
      yertle.forward(80);
     
      
      for( int j=1 ; j<=4 ; j++ ){
        yertle.penDown();
        for( int i=1 ; i<=4 ; i++ ){
          yertle.forward(10);
          yertle.right(PI/2);
        }; 
        yertle.penUp();  
        yertle.backward(40);
          
      };
      yertle.backward(0);
      yertle.right(PI);
      display.close();
    
        
    }; // constructor
    

    public static void main ( String[] args ) { Squares s = new Squares(); };
    
    
} // Squares