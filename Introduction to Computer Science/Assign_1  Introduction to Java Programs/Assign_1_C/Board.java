package Assign_1_C;


import Media.*;                  // for Turtle and TurtleDisplayer
import static Media.Turtle.*;    // for Turtle speeds
import static java.lang.Math.*;  // for Math constants and functions
import static java.awt.Color.*;  // for Color constants


/** This class is a program  that draws a two rows of 4 20x20 black squares at a distance of 20 units from each other using Turtle Graphics
  *
  * @author Micah Rose-Mighty
  * 
  * @version 1.0 (2018/09/15)                                                        */

public class Board {
  
  private TurtleDisplayer  display;  // display to draw on
  private Turtle           yertle;   // turtle to do drawing
  private Turtle           mertle;   // turtle to do drawing
    
    
    // This constructor draws a  creates an eight by eight board covered with alternating black and white squares
   
   public Board ( ) {
      
      display = new TurtleDisplayer();
      yertle = new Turtle();
      mertle = new Turtle();
      display.placeTurtle(yertle);
      display.placeTurtle(mertle);
      yertle.setSpeed(Turtle.FAST);
      mertle.setSpeed(Turtle.FAST);
      yertle.moveTo(20,60);
      mertle.moveTo(40,40);
      yertle.penUp();
      yertle.setPenWidth(10);
      yertle.left(PI);
      yertle.forward(80);
      mertle.penUp();
      mertle.setPenWidth(10);
      mertle.left(PI);
      mertle.forward(80);
     
     
      
      
      
      for( int k=1 ; k<=4 ; k++ ){
        yertle.penUp();
        mertle.penUp();
        for( int j=1 ; j<=4 ; j++ ){
          yertle.penDown();
          mertle.penDown();
          for( int i=1 ; i<=4 ; i++ ){
            yertle.forward(10);
            yertle.right(PI/2);
            mertle.forward(10);
            mertle.right(PI/2);
        }; 
          yertle.penUp();  
          yertle.backward(40);
          mertle.penUp();  
          mertle.backward(40);
          
      };
        yertle.forward(160);
        yertle.left(PI/2);
        yertle.forward(40);
        yertle.right(PI/2);
        mertle.forward(160);
        mertle.left(PI/2);
        mertle.forward(40);
        mertle.right(PI/2);
        
    
        
    }; // constructor
    display.close();
    

    } public static void main ( String[] args ) { Board s = new Board(); };
    
    
} // Board