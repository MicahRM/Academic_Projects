package TestParetoSet;

/** This class is test harness for the collection of Observation Data and results in displaying the Pareto Set
  * Micah Rose-Mighty
  * 6498935
  * 2020-03-08  
  */

import BasicIO.*;
import java.io.*;
import Pareto.*;

public class TestParetoSet {
  
  private ASCIIDataFile dataFile;
  private ASCIIDisplayer display;
  TestParetoSet () {
    
    dataFile = new ASCIIDataFile();
    display = new ASCIIDisplayer();
    int numberObservation = dataFile.readInt();
    int numberReading = dataFile.readInt();
    Observation obs;
    ParetoSet set = new LnkParetoSet(2);
    
    try {
      set = new LnkParetoSet(2);
    }
    catch ( DimensionalityException de ) {
      System.out.println("ERROR-Dimensionality Exception: parameter is not 2");
    }
    for ( int i=1; i<=numberObservation; i++) {
      obs = new Observation(dataFile, numberReading);
      try {
        set.add(obs);
      }
      catch ( DimensionalityException de ) {
        System.out.println("ERROR-Dimensionality Exception");
      }
    }  
    
    try {
      set.getBestForIndex (3);
    }
    catch ( DimensionalityException de ) {
      System.out.println("ERROR: Pararmeter for getBestForIndex is not 0 or 1");
    }
    try {
      set.getBestForIndex (0);
    }
    catch ( UnderflowException ufe) {
      System.out.println("ERROR-Underflow Exception");
    }
    display.writeLine("Number of observations within list : " + set.getSize());
    display.writeLine("Label of observations within list: " + set.toString());
    display.writeLine("The Best value for it's index is: " + set.getBestForIndex(0).getLabel() + " , " + set.getBestForIndex(0).getReading(0) + " , " + set.getBestForIndex(0).getReading(1));
  } // constructor
  
  public static void main ( String args[] ) { TestParetoSet p = new TestParetoSet(); }
  
} // TestParetoSet