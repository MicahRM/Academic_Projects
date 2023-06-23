package Assign_2;


public class Main { //main class of the TSP program used to initialize the TSP Solver and the parameters. Simply change the parameters to run the desired test.

    public static void main(String[] args) {
        new TSPSolver(new Map("ulysses22.txt"), 200, 200, 3, 0.0,0, 1.0, 8367476);
    }
}
