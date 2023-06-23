package Assign_2;

public class Path { //path class

    private Map aMap;
    private Location[] aPath;

    public Path(Map aMap, int dimension){ //path constructor
        this.aMap = aMap;
        aPath = new Location[dimension];
    }

    public void addLocation(Location l, int pointer){ // location adder for path class
        aPath[pointer] = l;
    }

    public Location getLocation(int pointer){ //location getter for path class
        return aPath[pointer];
    }

    public double getLength(){//method to get path length
        double length = 0;
        for(int i = 0; i < aPath.length-1; i++){
            length = length + aMap.getEucDistance(aPath[i].getLocationNum(),aPath[i+1].getLocationNum());
        }
        length = length + aMap.getEucDistance(aPath[aPath.length-1].getLocationNum(), aPath[0].getLocationNum());
        return length;
    }

    public boolean exists(Location l){ // method to check if a certain location exists
        for (Location location : aPath){
            if(location != null && l.getLocationNum() == location.getLocationNum()){
                return true;
            }
        }
        return false;
    }
}
