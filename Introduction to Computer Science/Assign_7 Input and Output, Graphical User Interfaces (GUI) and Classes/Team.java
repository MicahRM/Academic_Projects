package Assign_7;

import BasicIO.*;                
import static BasicIO.Formats.*; 
import static java.lang.Math.*; 

/** This class determines standings of the different hockey teams based upon their statistics during the season.
  * @author Micah Rose-Mighty
  * @version 1.0 2018/11/30                                                       */


public class Team {
  private String team;
  private int    goalsFor;
  private int    goalsAgainst;
  private int    points;


  /** This constructor ranks teams based upon point differential. The points are based upon the team's statistics throughout the season.                                                */ 
  
  public Team ( ASCIIDataFile from ) {
    team = from.readString();
    if ( ! from.isEOF() ) {
      goalsFor = from.readInt();
      goalsAgainst = from.readInt();
      points = from.readInt();
    } 

  }; 
  public String getTeam () {
    return team;
  } 
  public int getGoalsFor () {
    return goalsFor;
  } 
  public int getGoalsAgainst () {
    return goalsAgainst;
  }
  public int getPoints () {
    return points; 
  } 
  
  public void play ( int gF, int gA ) {
    if ( gF>gA ) {
      points = points + 2;
    }
    else if  ( gF==gA ){
      points = points + 1; 
    } 
    else {
      points = points + 0;    
    }
    goalsFor = goalsFor + gF;
    goalsAgainst = goalsAgainst + gA;
  } 
  public void write ( ASCIIOutputFile to ) {
    to.writeString(team);
    to.writeInt(goalsFor);
    to.writeInt(goalsAgainst);
    to.writeInt(points);
    to.newLine();
  }; 
  public void writer ( ReportPrinter to ) {
    to.writeInt("goalsFor", goalsFor);
    to.writeInt("goalsAgainst", goalsAgainst);to.writeInt("points", points);
  }; 
  

} // Team