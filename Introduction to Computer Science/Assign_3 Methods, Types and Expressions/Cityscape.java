package Assign_3;


import Media.*;                  // for Turtle and TurtleDisplayer
import java.awt.*;               // for Color objects and methods
import static Media.Turtle.*;    // for Turtle speeds
import static java.lang.Math.*;  // for math constants and functions
import static java.awt.Color.*;  // for Color constants


/** This class ...
  *
  * @author Micah Rose-Mighty
  *
  * @version 1.0 (2018/10/09)                                                        */

public class Cityscape {
  
  private TurtleDisplayer display;
  private Turtle          yertle;    
// instance variables

    /** This constructor ...                                                     */
    
    public Cityscape( ) {
      display = new TurtleDisplayer(yertle,500,500);
      yertle = new Turtle(FAST);
      display.placeTurtle(yertle);
      int w;
      int z;
      int y; 
      w = 0;
      z = -225;
      y = (int)(3*random())+3;;
      if(y == 3){
        yertle.moveTo(w-=105,z);
        for(int i=1; i<=3; i++){
          drawBuilding();
          w+=70;
          yertle.moveTo(w,z);
          yertle.left(PI/2);
        }
          
        }
      else if(y == 4){
        yertle.moveTo(w-=140,z);
        for(int i=1; i<=4; i++){
          drawBuilding();
          w+=70;
          yertle.moveTo(w,z);
          yertle.left(PI/2);
        }
          
        }
      else if(y == 5){
        yertle.moveTo(w-=175,z);
        for(int i=1; i<=5; i++){
          drawBuilding();
          w+=70;
          yertle.moveTo(w,z);
          yertle.left(PI/2);
        }
          
        }
       else if(y == 6){
        yertle.moveTo(w-=210,z);
        for(int i=1; i<=6; i++){
          drawBuilding();
          w+=70;
          yertle.moveTo(w,z);
          yertle.left(PI/2);
        }
          
        }
      
  
      display.close();
        
        // statements including call of method
        
    }; // constructor

    
    
    /** This method ...                                                          */
    
    private void drawRectangle ( ) {
      for(int i=1;i<=4; i++){
        yertle.penDown();
        yertle.forward(10);
        yertle.right(PI/2);
        yertle.penUp();
    
      }   // local variables
    
        // statements
    
    }; // <methodName>
      
      
     private void drawWindow ( ) {
        drawRectangle();
        yertle.forward(10);
        drawRectangle();
        yertle.right(PI/2);
        yertle.forward(10);
        drawRectangle();
        yertle.left(PI/2);
        drawRectangle();
      
    
        // local variables
    
        // statements
    
    }; // <methodName>
      
      
     private void drawBuilding ( ) {
       int x = (int)(10*random())+5;
       yertle.penDown();
       yertle.forward(70);
       yertle.left(PI/2);
       yertle.forward(x*30);
       yertle.left(PI/2);
       yertle.forward(70);
       yertle.left(PI/2);
       yertle.forward(x*30);
    
     for (int i=1; i<=x; i++){
        yertle.backward(25);
        yertle.left(PI/2);
        yertle.penUp();
        yertle.forward(10);
        drawWindow();
        yertle.forward(20);
        yertle.left(PI/2);
        yertle.forward(10);
        yertle.right(PI/2);
        drawWindow();
        yertle.backward(50);
        yertle.right(PI/2);
        yertle.backward(15);
      }

      };
     //something heya
       
       
       
    

        // local variables
    
        // statements
    
    // <methodName>
      
    public static void main ( String[] args ) { Cityscape s = new Cityscape(); };}

    
    
      // <className>
