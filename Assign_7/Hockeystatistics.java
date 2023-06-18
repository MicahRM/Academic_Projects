 package Assign_7; 
 
 import BasicIO.*; 
 import static BasicIO.Formats.*;  
 import static java.lang.Math.*; 
 
 /** This class creates a program to report and organize various hockey team statistics throughout the season.  
   * @author Micah Rose-Mighty
   * @version 1.0 2018/11/30                                                       */
 public class Hockeystatistics {
   
   private ASCIIDataFile gameData;
   private ASCIIOutputFile newgameData; 
   private ReportPrinter report;
   private BasicForm display; 
// instance variables
   
   /** This constructor organizes different hockey team statistics and places them into a report based upon user input.                                                  */
   
   public Hockeystatistics() {
     Team aTeam;   // Team
     int button;    // Buttons on interface
     int goalsFor;  // Goals for 
     int goalsAgainst; // Goals Conceded 
     int points;       // Points a team has earned 
     int gF; // goalsFor
     int gA;  // Goalsgainst
     
     display = new BasicForm("Enter","Bye");
     report = new ReportPrinter();
     gameData = new ASCIIDataFile();
     newgameData = new ASCIIOutputFile();
     buildForm(); 
     setUpReport();
     
     for (;;) {
       aTeam = new Team(gameData);
       report.writeString("aTeam",aTeam.getTeam());
       display.writeString("aTeam", aTeam.getTeam());
       if (gameData.isEOF()) break; 
       points = aTeam.getPoints();
       button = display.accept();
       switch (button) {
         case 0: { 
           gF = display.readInt("goalsFor");
           gA = display.readInt("goalsAgainst");
           aTeam.play(gF,gA);
           display.writeString("goalsFor", "");
           display.writeString("goalsAgainst", "");
           break;
         }
         case 1: {
           break;
         }
       }
       aTeam.writer(report);
       aTeam.write(newgameData);
     }
     
     report.close();
     display.close();
     gameData.close();
     newgameData.close();

   };
   private void setUpReport() { 
     report.setTitle("NHL Hockey Statistics");
     report.addField("aTeam", "Team", 10);
     report.addField("goalsFor", "For", 4);
     report.addField("goalsAgainst", "Against", 8); 
     report.addField("points", "Points", 8);
   } 
   private void buildForm() {
     display.setTitle("Niagara Hockey League (NHL) Stats");
     display.addTextField("aTeam", "Team", 10, 35, 5);
     display.setEditable("aTeam", false);
     display.addTextField("goalsFor", "For", 4, 20, 40);
     display.addTextField("goalsAgainst", "Against", 4, 85, 40);
   } 

   public static void main(String[] args) { Hockeystatistics c = new Hockeystatistics();
   };
 } // Hockeystatistics