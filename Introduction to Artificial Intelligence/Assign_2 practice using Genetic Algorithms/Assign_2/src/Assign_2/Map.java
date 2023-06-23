package Assign_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Map { // map class

    private int dimension;
    private Location[] aMap;

    public Map(String fileName){ //map constructor
        createMap(fileName);
    }

    public Location getLocation (int locationNum){ //location getter method
        return aMap[locationNum-1];
    }

    public double getEucDistance (int locA,  int locB){ //euclidean distance method
        return Math.sqrt(Math.pow(aMap[locB - 1].getX()-aMap[locA-1].getX(), 2)+Math.pow(aMap[locB-1].getY()-aMap[locA-1].getY(),2));
    }

    public int getDimension(){ //dimension getter method
        return dimension;
    }

    private void createMap(String fileName){ //map creation method that takes in a string fileName
        String currLine;
        int n;
        int x1;
        int y1;
        int locationNum;
        double x;
        double y;

        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            br.readLine();
            br.readLine();
            currLine = br.readLine();
            dimension = Integer.parseInt(currLine.substring(11,currLine.length()));
            br.readLine();
            br.readLine();
            br.readLine();
            aMap = new Location[dimension];
            while((currLine = br.readLine()) != null){
                if(currLine.equals("EOF")) {
                    break;
                }
                n = 1;
                while (currLine.charAt(n) != ' '){
                    n++;
                }
                x1 = n+1;
                while (currLine.charAt(x1) != ' '){
                    x1++;
                }
                y1 = x1 +1;
                while (y1 != currLine.length()){
                    y1++;
                }
                locationNum = Integer.parseInt(currLine.substring(1,n));
                x = Double.parseDouble(currLine.substring(n+1, x1));
                y = Double.parseDouble(currLine.substring(x1+1,y1));
                addLocation(new Location(locationNum,x,y));
            }
            br.close();
        }
        catch (Exception e){
            System.out.println("Incorrect file source");
            e.printStackTrace();
        }
    }

    private void addLocation (Location l){ //method to add a location to the population
        int locationNum = l.getLocationNum();
        if (locationNum > dimension){
            throw new LocationNumOutOfBounds();
        }
        else {
            aMap[locationNum-1] = l;
        }
    }

    private class LocationNumOutOfBounds extends RuntimeException { //exception class that goes off when location number is out of bounds
        LocationNumOutOfBounds(){
            super("Location Number out of dimension boundaries");
        }
    }
}
