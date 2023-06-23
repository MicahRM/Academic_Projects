package Assign_1;

/*Micah Rose-Mighty
  Student #: 6498935
  COSC 3P71
  Assignment 1
  2021/10/17
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Connect4 { //Connect 4 class

    final int gridWidth=7;
    final int gridLength=7;
    int numberOfSpacesFilled;
    int[][] grid;

    public Connect4(){ //connect 4 constructor
        grid =new int[gridLength][gridWidth];
        numberOfSpacesFilled =0;
    }

    public static void main(String args[])  { //connect 4 main class

        Connect4 conn4=new Connect4();
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Connect 4");
        Random rand = new Random();
        origin:
        while(true){
            int col=0 ;

            while(true){ //Player 1 Game operation
                System.out.println("\n\nPlayer 1 Enter a Column Number to place your piece (0-6 inclusive):");
                try {
                    col = Integer.parseInt(buffer.readLine());
                } catch (Exception e) {
                    System.out.println("Invalid Entry, Try Again");
                    continue;
                }
                if(conn4.valid(col)) {
                    if (conn4.vacant(col)) {
                        if (conn4.placePiece(col, 1)) {
                            conn4.showGrid();
                            System.out.println("\n\nPlayer 1 has WON");
                            break origin;
                        }
                        break;
                    } else
                        System.out.println("Unfortunately,column " + col + " is at maximum capacity");
                } else
                    System.out.println("Invalid Entry, Try Again");
            }
            conn4.showGrid();

            origin2:

            while(true){ //Player 2 (CPU) Game Operation with adjustments for program to run, previously optimalPosition.col was the column choice of the CPU but I continuously ran into ArrayOutOfBoundsExceptions without a solution or time to derive one. causes of this exception have been commented out.
                System.out.println("\n\nPlayer 2 (CPU) Choose a Column Number to place your piece...");
                //Position optimalPosition = findOptimalPosition(conn4.grid);
                try {
                    //col = optimalPosition.col;
                    col= rand.nextInt(7);
                    System.out.println("\nCPU chooses Column Number: " +col+"\n");
                } catch (Exception e) {
                    System.out.println("Invalid Entry, Try Again");
                    continue origin2;
                }
                if(conn4.valid(col)) {
                    if (conn4.vacant(col)) {
                        if (conn4.placePiece(col, 2)) {
                            conn4.showGrid();
                            System.out.println("\n\nPlayer 2 has WON");
                            break origin;
                        }
                        break;
                    } else
                        System.out.println("Unfortunately,column " + col + " is at maximum capacity");
                } else
                    System.out.println("Invalid Entry, Try Again");

            }
            conn4.showGrid();

            if(conn4.isFull()){
                System.out.print("Somehow you've managed to fill the grid without an actual winner...Try Again");
                break;
            }
        }
    }

    public void showGrid(){ //function for creating and showing the grid
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if(grid[i][j] == 0)
                    System.out.print("|  ");
                else
                    System.out.print(grid[i][j]+"  ");
            }
            System.out.println();
        }

        for(int i=0;i<gridWidth;i++)
            System.out.print("_  ");
        System.out.println();

        for(int i=0;i<gridWidth;i++)
            System.out.print(i+"  ");
        System.out.println();
    }

    static class Position{ // position class used to quantify values for a position on the grid (really a helper class for the attempted AI Minimax decision
        int row;
        int col;
    }

    static int player = 1;
    static int computer = 2;

    static boolean vacantGrid(int grid[][]){ //function that returns true if the grid is vacant and otherwise false
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(grid[i][j] != 1 || grid[i][j] != 2){
                    return true;
                }
            }
        }
        return false;
    }
    static int evaluateGrid(int grid[][]){ //Function that is supposed to evaluate the state of the grid and give positive or negative values depending on the position of each piece and who the piece belongs to. this is where my ArrayOutOfBoundsException stems from but after days of effort I still can not solve that issue.

            for (int row = 0; row < 7; row++) {
                for (int col = 0; col < 8; col++) {
                    try{
                        if (grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2] && grid[row][2] == grid[row][3] || grid[row][3] == grid[row][4] && grid[row][4] == grid[row][5] && grid[row][5] == grid[row][6] || grid[row][1] == grid[row][2] && grid[row][2] == grid[row][3] && grid[row][3] == grid[row][4] || grid[row][2] == grid[row][3] && grid[row][3] == grid[row][4] && grid[row][4] == grid[row][5]) {
                            if (grid[row][0] == player || grid[row][4] == player || grid[row][1] == player || grid[row][2] == player) {
                                return +100;
                            } else if (grid[row][0] == computer || grid[row][4] == computer || grid[row][1] == computer || grid[row][2] == computer) {
                                return -100;
                    }
                }
            }catch(ArrayIndexOutOfBoundsException exception){
                        grid[row][col] = 0;
                }
            }

                for (  row = 0; row < 7; row++) {
                    for (int col = 0; col < 8; col++) { //vertical checker
                       try{
                           if (grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col] && grid[2][col] == grid[3][col] || grid[3][col] == grid[4][col] && grid[4][col] == grid[5][col] && grid[5][col] == grid[6][col] || grid[1][col] == grid[2][col] && grid[2][col] == grid[3][col] && grid[3][col] == grid[4][col] || grid[2][col] == grid[3][col] && grid[3][col] == grid[4][col] && grid[4][col] == grid[5][col]) {
                               if (grid[0][col] == player || grid[4][col] == player || grid[1][col] == player || grid[2][col] == player) {
                                   return +100;
                               } else if (grid[0][col] == computer || grid[4][col] == computer || grid[1][col] == computer || grid[2][col] == computer) {
                                   return -100;
                            }
                        }

                    } catch (ArrayIndexOutOfBoundsException exception){
                           grid[row][col] = 0;
                       }
                    }
                }

            for ( row = 0; row < 7; row++) { //downward diagonal checker
                for (int col = 0; col < 8; col++) {
                    try {
                        if (grid[row][col] == grid[row + 1][col + 1] && grid[row + 1][col + 1] == grid[row + 2][col + 2] && grid[row + 2][col + 2] == grid[row + 3][col + 3]) {
                            if (grid[row][col] == player) {
                                return +100;
                            } else if (grid[row][col] == computer) {
                                return -100;
                        }
                    }

                    }catch (ArrayIndexOutOfBoundsException exception){
                        grid[row][col] = 0;
                    }


                }
            }


            for ( row = 0; row < 7; row++) { //upward diagonal checker
                for (int col = 0; col < 8; col++) {
                    try {
                        if (grid[row][col] == grid[row - 1][col - 1] && grid[row - 1][col - 1] == grid[row - 2][col - 2] && grid[row - 2][col - 2] == grid[row - 3][col - 3]) {
                            if (grid[row][col] == player) {
                                return +100;
                            } else if (grid[row][col] == computer) {
                                return -100;
                            }
                        }
                    }catch (ArrayIndexOutOfBoundsException exception){
                        grid[row][col] = 0;
                    }

                }
            }}
            return 0;

    }

    static int minimax(int grid[][], int depth, Boolean isMaximizer){ //Minimax Function which uses the grid evaluation of the current state and possible future moves to help decide the best move for the CPU. this function will not work due to the exceptions previously discussed but I believe my logic is correct.

        int score = evaluateGrid(grid);

        if(score == 100){ //maximizer(player) has won
            return score;
        }

        if(score == -100){ //minimizer(computer) has won
            return score;
        }

        if(vacantGrid(grid) == false){ //cat's game
            return 0;
        }

        int goal;
        if(isMaximizer){
            goal = -1000;

            for(int i = 0; i <7; i++){
                for(int j = 0; j<7; j++){
                    if(grid[i][j] != 1 || grid[i][j] != 2){
                        grid[i][j] = player;
                        goal = Math.max(goal,minimax(grid,depth+1,!isMaximizer));
                        grid[i][j] = '|';
                    }
                }

            }
        }

        else {
            goal = 1000;

            for(int i = 0; i <7; i++){
                for(int j = 0; j<7; j++){
                    if(grid[i][j] != 1 || grid[i][j] != 2){
                        grid[i][j] = computer;
                        goal = Math.min(goal,minimax(grid,depth+1,!isMaximizer));
                        grid[i][j] = '|';
                    }
                }

            }


        }
        return goal;
    }

    static Position findOptimalPosition(int grid[][]){ // function of the position class that returns an optimalPosition with a row and column value. Obviously only the column value is needed which is why optimalPosition.col was attempted.

        int optimalVal = -1000;
        Position optimalPosition = new Position();
        optimalPosition.row = -1;
        optimalPosition.col = -1;

        for(int i = 0; i <7; i++){
            for(int j = 0; j<7; j++){
                if(grid[i][j] != 1 || grid[i][j] != 2){
                    grid[i][j] = player;
                    int positionVal = minimax(grid,0,false);
                    grid[i][j] = '|';

                    if(positionVal > optimalVal){
                        optimalPosition.row = i;
                        optimalPosition.col = j;
                        optimalVal = positionVal;
                    }
                }
            }

        }
        return optimalPosition;
    }
    public boolean placePiece(int col, int player){ // boolean function  that takes a column number and player number and places that within the respective column and respective row with correct number.
        int i=0;
        for(i=0; i< gridLength; i++){
            if(grid[i][col] == 1 || grid[i][col] == 2){
                grid[i-1][col]=player;
                break;
            }
        }
        if(i == gridLength)
            grid[i-1][col]=player;

        numberOfSpacesFilled++;
        return inARow(i-1,col);
    }

    public boolean vacant(int col){ // boolean function that takes column number x and  returns true if row 0 and column x = 0
        return grid[0][col] == 0;
    }

    public boolean valid(int col){ // boolean function to check if a column number is a valid entry.
        if(col == 0 || col == 1 || col == 2  || col == 3  || col == 4  || col == 5  || col == 6  ){
            return true;
        }
        else return false;

    }

    public boolean isFull(){ // returns true if the grid is maxed out
        return numberOfSpacesFilled == gridLength*gridWidth;
    }

    public boolean inARow(int m, int n){ //boolean function that returns true if a player has won the game
        int piece = grid[m][n];
        int count=0;
        int i=n;


        while(i<gridWidth && grid[m][i] == piece){ //horizontal check
            count++;
            i++;
        }
        i=n-1;
        while(i>=0 && grid[m][i] == piece){
            count++;
            i--;
        }
        if(count == 4)
            return true;


        count=0;
        int j=m;
        while(j< gridLength && grid[j][n] == piece){ //vertical check
            count++;
            j++;
        }
        if(count == 4)
            return true;


        count=0;
        i=m;
        j=n;
        while(i<gridWidth && j< gridLength && grid[i][j] == piece){ //diagonal check
            count++;
            i++;
            j++;
        }
        i=m-1;
        j=n-1;
        while(i>=0 && j>=0 && grid[i][j] == piece){
            count++;
            i--;
            j--;
        }
        if(count == 4)
            return true;


        count=0;
        i=m;
        j=n;
        while(i<gridWidth && j>=0 && grid[i][j] == piece){ //diagonal check
            count++;
            i++;
            j--;
        }
        i=m-1;
        j=n+1;
        while(i>=0 && j< gridLength && grid[i][j] == piece){
            count++;
            i--;
            j++;
        }
        if(count == 4)
            return true;

        return false;
    }



}