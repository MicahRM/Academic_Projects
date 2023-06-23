package Assign_2;

/**Micah Rose-Mighty
 * St#:6498935
 * COSC 3p71 Assignment 2
 */

import java.util.Random;

public class TSPSolver { //main TSP solver program
    int maxGen;
    int pSize;
    int k;
    double mutFrequency;
    double crossFrequency;
    int crossType;
    long Seed;
    int dimension;
    Map aMap;
    Random rand;


    public TSPSolver(Map aMap, int maxGen, int pSize, int k, double mutFrequency, int crossType, double crossFrequency, long Seed) { //TSP Solver constructor
        this.maxGen = maxGen;
        this.pSize = pSize;
        this.k = k;
        this.mutFrequency = mutFrequency;
        this.crossFrequency = crossFrequency;
        this.crossType = crossType;
        this.Seed = Seed;
        rand = new Random(Seed);
        this.aMap = aMap;
        dimension = aMap.getDimension();
        Solve(genPop(aMap));
    }

    private void Solve (Path[] population) { //main method used to solve the TSP problem
        Path aPath = new Path(aMap, dimension);
        Path tPath;
        double tLength;
        System.out.println("Number of generations to be reproduced: " + maxGen + "\nPopulation Size: " + pSize + "\nk tournament selection value: " + k + "\nCrossover Rate: " + crossFrequency + "\nCrossover Type: ");
        if (crossType == 0) {
            System.out.print("Uniform Order Crossover");
        } else {
            System.out.print("Ordered Crossover");
        }
        System.out.print("\nRandom Number Seed: " + Seed + "\n");

        for (int curPop = 0; curPop < maxGen; curPop++) { //population generator
            tLength = 0;
            if (curPop != 0) { //elitism component
                for (int i = 0; i < dimension; i++) {
                    if (population[i].getLength() == aPath.getLength()) {
                        break;
                    } else if (population[i].getLength() < aPath.getLength()) {
                        population[i] = aPath;
                        break;
                    }
                }
            }
            tPath = calcPop(population);
            if (curPop == 0 || tPath.getLength() < aPath.getLength()) {
                aPath = tPath;
            }
            population = mutate(crossover(compare(population, k), crossType));
            System.out.print(aPath.getLength() + "\t");
            for (int i = 0; i < pSize; i++) {
                tLength += population[i].getLength();
            }
            System.out.println(tLength/pSize);
        }
        System.out.println();

        for (int i = 0; i < dimension; i++) {
            System.out.print(aPath.getLocation(i).getLocationNum() + " ");
        }
        System.out.println();
        System.out.println(aPath.getLength());
    }

    private Path[] mutate (Path[] population) { //mutation method to perform a Reciprocal Exchange Mutation
            int i;
            int j;
            Location temp;
            for (int p = 0; p<pSize; p++) {
                if (rand.nextDouble() < mutFrequency) {
                    i = (int)(rand.nextDouble()*dimension);
                    j = i;
            while (j == i) {
                j = (int)(rand.nextDouble()*dimension);
            }
            temp = population[p].getLocation(i);
            population[p].addLocation(population[p].getLocation(j), i);
            population[p].addLocation(temp, j);
        }
    }
            return population;
}

    private Path[] crossover (Path[] population, int crossType){ //method to decide which crossover to perform
        if(crossType == 0){
            return uniformOrderCrossover(population);
        } else{
            return orderedCrossover(population);
        }
    }

    private Path[] uniformOrderCrossover (Path[] population) { //method to perform Uniform Order Crossover
        Path[] nPop = new Path[pSize];
        int k;
        for(int i = 0; i < pSize; i+=2){
            if(rand.nextDouble()<crossFrequency){
                nPop[i] = new Path(aMap, dimension);
                nPop[i+1] = new Path(aMap, dimension);
                for(int j = 0; j< dimension; j++){
                    if((int)(rand.nextDouble()*2) == 1) {
                        nPop[i].addLocation(population[i].getLocation(j), j);
                        nPop[i+1].addLocation(population[i+1].getLocation(j), j);
                    }
                }
                k = 0;
                for(int j = 0; j< dimension; j++){
                    if(!nPop[i+1].exists(population[i].getLocation(j))) {
                        while(k< dimension && nPop[i+1].getLocation(k) != null) {
                            k++;
                        }
                        if (nPop[i+1].getLocation(k) == null){
                            nPop[i+1].addLocation(population[i].getLocation(j), k);
                        }
                    }
                }
                k = 0;
                for(int j = 0; j<dimension; j++){
                    if(!nPop[i].exists(population[i+1].getLocation(j))){
                        while (k<dimension && nPop[i].getLocation(k) != null){
                            k++;
                        }
                        if(nPop[i].getLocation(k) == null){
                            nPop[i].addLocation(population[i+1].getLocation(j),k);
                        }
                    }
                }
            }
            else {
                nPop[i] = population[i];
                nPop[i+1] = population[i+1];
            }
        }
        return nPop;
    }

    private Path[] orderedCrossover (Path[] population){ //method to perform Ordered Crossover
        Path[] nPop = new Path[pSize];
        int partSize = (int)(rand.nextDouble()*dimension);
        int start = (int)(rand.nextDouble()*dimension);
        int k;
        for(int i = 0; i<pSize; i+=2){
            if(rand.nextDouble() < crossFrequency){
                nPop[i] = new Path(aMap, dimension);
                nPop[i+1] = new Path(aMap, dimension);
                for(int j = start; j < start+partSize; j++) {
                    nPop[i].addLocation(population[i].getLocation(j%dimension), j%dimension);
                    nPop[i + 1].addLocation(population[i + 1].getLocation(j%dimension), j%dimension);
                }
                k = start+partSize;
                for(int j = start+partSize; j < start+partSize+dimension; j++){
                    if(!nPop[i+1].exists(population[i].getLocation(j%dimension))){
                        while (nPop[i+1].getLocation(k%dimension) != null) {
                            k++;
                        }
                        nPop[i+1].addLocation(population[i].getLocation(j%dimension), k%dimension);
                        k++;
                    }
                }
                k = start+partSize;
                for(int j = start+partSize; j < start+partSize+dimension; j++){
                    if(!nPop[i].exists(population[i+1].getLocation(j%dimension))){
                        while(nPop[i].getLocation(k%dimension) != null){
                            k++;
                        }
                        nPop[i].addLocation(population[i+1].getLocation(j%dimension), k%dimension);
                        k++;
                    }
                }
            }
            else {
                nPop[i] = population[i];
                nPop[i+1] = population[i+1];
            }
        }
        return nPop;
    }
    private Path[] compare (Path[] population, int kVal){ //method to compute tournament between chromosomes given a k value and a population.
        Path[] comparison = new Path[kVal];
        Path[] nPop = new Path[pSize];
        Path optimalPath = new Path(aMap, dimension);
        double compLength;
        double bestCompLength;
        for (int i = 0; i < pSize; i++){
            nPop[i] = new Path(aMap, dimension);
            for(int k = 0; k < kVal; k++){
                comparison[k] = population[(int)(rand.nextDouble()*pSize)];
            }
            bestCompLength = -1;
            for(int k = 0; k < kVal; k++){
                if(bestCompLength == -1){
                    optimalPath = comparison[k];
                    bestCompLength = comparison[k].getLength();
                }
                else {
                    compLength = comparison[k].getLength();
                    if(compLength < bestCompLength) {
                        optimalPath = comparison[k];
                        bestCompLength = compLength;
                    }
                }
            }
            nPop[i] = optimalPath;
        }
        return nPop;
    }

    private Path[] genPop (Map aMap){ //method to generate the population
        Path[] population = new Path[pSize];
        int pointer;
        for(int i = 0; i < pSize; i++){
            population[i] = new Path(aMap, aMap.getDimension());
            for(int j = 0; j < dimension; j++){
                pointer = (int)(1+rand.nextDouble()* dimension);
                while(population[i].exists(aMap.getLocation(pointer))){
                    pointer = (int)(1+rand.nextDouble()* dimension);
                }
                population[i].addLocation(aMap.getLocation(pointer), j);
            }
        }
        return population;
    }


    private Path calcPop (Path[] population){ //method to get the best path from the population
        Path optimalPath = new Path(aMap, dimension);
        double compLength;
        double bestCompLength = -1;
        for(int i = 0; i < pSize; i++){
            if(bestCompLength == -1){
                optimalPath = population[i];
                bestCompLength = population[i].getLength();
            }
            else {
                compLength = population[i].getLength();
                if(compLength < bestCompLength){
                    optimalPath = population[i];
                    bestCompLength = compLength;
                }
            }
        }
        return optimalPath;
    }



        }



