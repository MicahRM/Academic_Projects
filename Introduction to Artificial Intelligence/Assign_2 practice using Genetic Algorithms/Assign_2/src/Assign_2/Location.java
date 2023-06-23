package Assign_2;

public class Location {

    private int locationNum;
    private double x;
    private double y;


    public Location( int locationNum, double x, double y){ //location constructor
        this.locationNum = locationNum;
        this.x = x;
        this.y = y;
    }

    public int getLocationNum(){ //location number method
        return locationNum;
    }

    public double getX() {// x getter method
        return x;
    }

    public double getY() { //y getter method
        return y;
    }
}
