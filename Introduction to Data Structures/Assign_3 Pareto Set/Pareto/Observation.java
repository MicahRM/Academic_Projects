package Pareto;


import BasicIO.*;


/** This class represents a single observation consisting of an identifying label
  * and a set of readings for that observation.
  * 
  * @version 1.0 (Feb 2018)
  * 
  * @author D. Hughes (after E. Foxwell)                                         */

public class Observation {
    
    
    private char      label;      // identifying label
    private double[]  readings;   // the readings
    
    
    /** This constructor creates a new observation consisting of the specified
      * number of readings, reading data from an ASCIIDataFile.
      * 
      * @param  from         the file to read from
      * @param  numReadings  number of readings for the observation              */
    
    public Observation ( ASCIIDataFile from, int numReadings ) {
        
        label = from.readChar();
        if ( ! from.isEOF() ) {
            readings = new double[numReadings];
            for ( int i=0 ; i<readings.length ; i++ ) {
                readings[i] = from.readDouble();
            };
        };
        
    };  // constructor
    
    
    /** This constructor creates a new observation consisting of the specified
      * label and readings.
      * 
      * @param  l  the label
      * @param  r  the readings                                                  */
    
    public Observation ( char l, double[] r ) {
        
        label = l;
        readings = new double[r.length];
        for ( int i=0 ; i<readings.length ; i++ ) {
            readings[i] = r[i];
        };
        
    };  // constructor
    
    
    /** This method returns the label on the observation.
      * 
      * @return  char  the label on the observation                              */
    
    public char getLabel ( ) {
        
        return label;
        
    };  // getLabel
    
    
    /** This method returns the number of readings in the observation.
      * 
      * @return  int  the number of readings nthe observation                    */
    
    public int getNumReadings ( ) {
        
        return readings.length;
        
    };  // getNumReadings
    
    
    /** This method returns the value of the ith reading of the observation.
      * 
      * @param  i  the reading number
      * 
      * @return  double  the ith reading
      * 
      * @throws  IndexOutOfBoundsException  if the observation doesn't have an ith
      *                                     reading                              */
    
    public double getReading ( int i ) {
        
        return readings[i];
        
    };  // getReading
    
    
}