package Assign_2; 


import Media.*;                  // for Turtle and TurtleDisplayer
import java.awt.*;               // for Color objects, constructor and methods
import static Media.Turtle.*;    // for Turtle speeds
import static java.lang.Math.*;  // for Math constants and functions
import static java.awt.Color.*;  // for Color constants

/** This class creates a cover with rows of  diamond-like patches composed of isoceles triangles.
  * *
  * @author Micah Rose-Mighty
  *
  * @version 1.0 2018/10/05                                                        */
public class Cover {
  
  private TurtleDisplayer display;
  private Turtle          yertle;    
// instance variables
  
  
  //This constructor runs the methods needed for the program to produce the cover.
  
  public Cover ( ) {
    display = new TurtleDisplayer();
    yertle = new Turtle(FAST);
    display.placeTurtle(yertle);
    yertle.moveTo(-120,120);
    drawTriangle();
    drawPatch();
    drawRow();
    drawCover();
    yertle.moveTo(0,0);
    display.close();
  }; // constructor
  
  //method draws an isoceles triangle.
  
  private void drawTriangle ( ) {
    yertle.penDown();
    yertle.forward(30);
    yertle.left(3*PI/4);
    yertle.forward(Math.sqrt(1800));
    yertle.left(3*PI/4);
    yertle.forward(30);
    yertle.penUp();
  
  };
//method draws a diamond-like patch composed of 4 isoceles triangles
  private void drawPatch() {
   yertle.penDown();
   for (int i = 1;  i<=4;  i++ ) {
     yertle.right(PI);
     drawTriangle();
   
   }
   yertle.penUp();
  }

//method draws a row consisting of 5 diamond-like patches.
  private void drawRow()   {
    yertle.left(PI/2);
    for (int k = 1; k<= 4 ; k++) {
      yertle.forward(60);
      drawPatch();
    }
  }
  //method draws 5 rows that cover the entire display.
  private void drawCover() {
    
    int x;
    int y;
    
    x = -120;
    y=  60;

    for ( int l = 1 ; l <= 4 ; l++) {
      yertle.moveTo(x,y);
      drawPatch();
      drawTriangle();
      drawRow();
      //y= y - 60;
      y -=60;
    }
  }
  
  public static void main ( String[] args ) { Cover s = new Cover(); }; 
}  // <className>