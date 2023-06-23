package Puzzle;

 
import BasicIO.*;
import static BasicIO.Formats.*;
import static java.lang.Math.*;

/**Micah Rose-Mighty
  *Student Number: 6498935
  *This class solves a wordsearch with the words ans puzzle provided by a file.*/


public class Puzzle {
    
    
    private ASCIIDataFile   puzzleFile;
    private ASCIIDisplayer  display;
    private char[][]        puzzle;
    int height = 0;  //height of the char array (wordsearch puzzle)
    int width = 0;   //width of the char array (wordsearch puzzle)
    String word;
    
    
    public Puzzle ( ) {
        
        puzzleFile = new ASCIIDataFile();
        display = new ASCIIDisplayer(35,70);
        height = puzzleFile.readInt();
        width =  puzzleFile.readInt();
        loadPuzzle();
        displayPuzzle();
        for( ; ; ){
          word = puzzleFile.readString(); //Reads the words to be searched from the given file.
          if(puzzleFile.isEOF()) break;
          searchWord(puzzle,word);  
        }
        puzzleFile.close();
        display.close();
        
    }; //This is the main constructor where the puzzle is loaded, displayed and then solved.
     
     
     private void loadPuzzle ( ) {  //loads the wordsearch puzzle into a 2D array from a file.
      
       puzzle = new char[height][width];
       for ( int i=0 ; i<puzzle.length ; i++ ) {
         for (int j=0 ; j<puzzle[i].length ; j++ ) {
           puzzle[i][j] = puzzleFile.readChar();
            };
        };
        
    };
    
    
    private void displayPuzzle ( ) {   ///displays the previously loaded puzzle onmto the ASCIIDisplayer.
      
      for ( int i=0 ; i<puzzle.length ; i++ ) {
        for (int j=0 ; j<puzzle[i].length ; j++ ) {
          display.writeChar(puzzle[i][j]);
            };
            display.newLine();
        };
    
   
    };
          
    private void searchWord(char[][] puzzle, String word) {  //searches for the current word being searched for using search methods for each potential direction the word can be found.
      
      boolean found = false;
     for(int i = 0; i < height; i++){ 
       for (int j = 0; j < width; j++){ 
         if (puzzle[i][j] == word.charAt(0) &&! found){
           searchRight(i,j,word);
           if(found)break;
           searchLeft(i,j,word);
           if(found)break;
           searchUp(i,j,word);
           if(found)break;
           searchDown(i,j,word);
           if(found)break;
           searchDdright(i,j,word);
           if(found)break;
           searchDdleft(i,j,word);
           if(found)break;
           searchDuright(i,j,word);
           if(found)break;
           searchDuleft(i,j,word);
           if(found)break;
       }
     }
   }
    }
    
    private boolean searchRight ( int x, int y, String word) { //Searches for the given word to the right and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer. 
      
      boolean found = false;
      int length = word.length();
      
      if((y+length) - 1<width){
        for(int i=  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x][y+i])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (RIGHT) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }
     private boolean searchLeft ( int x, int y, String word) {   //Searches for the given word to the left and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer.
      
      boolean found = false;
      int length = word.length();
      
      if((y-length) + 1>=0){
        for(int i =  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x][y-i])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (LEFT) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }
      private boolean searchUp ( int x, int y, String word) {   //Searches for the given word upwards and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer.
      
      boolean found = false;
      int length = word.length();
      
      if((x-length) + 1>=0){
        for(int i =  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x-i][y])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (UPWARDS) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }
      private boolean searchDown ( int x, int y, String word) {   //Searches for the given word downwards and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer.
      
      boolean found = false;
      int length = word.length();
      
      if((x+length) - 1<height){
        for(int i =  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x+i][y])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (DOWNWARDS) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }
      private boolean searchDdright ( int x, int y, String word) {  //Searches for the given word diagonal downwards to the right and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer.
      
      boolean found = false;
      int length = word.length();
      
      if((x+length) - 1<height && (y+length) - 1<height){
        for(int i =  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x+i][y+i])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (DIAGONAL DOWNWARDS RIGHT) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }
      private boolean searchDdleft ( int x, int y, String word) {  //Searches for the given word diagonal downwards to the left and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer.
      
      boolean found = false;
      int length = word.length();
      
      if((x+length) - 1<height && (y-length) + 1>=0){
        for(int i =  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x+i][y-i])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (DIAGONAL DOWNWARDS LEFT) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }
      private boolean searchDuleft ( int x, int y, String word) {   //Searches for the given word diagonal upwards to the left and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer.
      
      boolean found = false;
      int length = word.length();
      
      if((x-length) + 1>=0 && (y-length) + 1>=0){
        for(int i =  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x-i][y-i])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (DIAGONAL UPWARDS LEFT) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }
      private boolean searchDuright ( int x, int y, String word) {  //Searches for the given word diagonal upwards to the right and returns if it is found or not. if found this method writes a message to the ASCIIDisplayer.
      
      boolean found = false;
      int length = word.length();
      
      if((x-length) + 1>=0 && (y+length) - 1<width){
        for(int i =  0; i<length; i++){
          if(!(word.charAt(i) == puzzle[x-i][y+i])){
            found = false;
            break;
          }
          found = true;
        }
      }
      
      if(found){
        display.writeString(word + " found (DIAGONAL UPWARDS RIGHT) at (" + x +","+ y+")");
        display.newLine();
      }
      return found;
   }                           
                         
       
    
    public static void main ( String[] args ) { Puzzle p = new Puzzle();}}
        