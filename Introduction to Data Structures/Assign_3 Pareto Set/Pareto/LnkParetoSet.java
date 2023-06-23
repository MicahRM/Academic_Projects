package Pareto;

/** This class is a linked implementation of ParetoSet
  * Micah Rose-Mighty
  * 6498935
  * 2020-03-08  
  */


import java.io.*;

public class LnkParetoSet implements ParetoSet {
  
  private Node list;     
  private Node list2;    
  
  public LnkParetoSet ( int numReading ) {
    
    if ( numReading!=2 ) {
      throw new DimensionalityException();
    }
    
    list = null;
  } // constructor
  
  
  public int getSize () { //gets the size of the list
    int result=0;;
    Node p;
    
    if ( list==null ) {
      result=0;
    }
    else {
      p=list;
      while ( p!=null ) {
        result++;
        p=p.next;
      }
    }
    return result;
    
  }  
  
    private void listAdder ( Observation ob, Node listName ) { // adds a new null node to the list
    Node a=listName;
    Node b=null;
    
    while (a!=null) {
      b=a;
      a=a.next;
    }
    
    b.next = new Node (ob, null);
  }  
  
 
  public void clear () { // clears the list
    list = null;
  } 
  
   public void add ( Observation ob ) { //reads the observations and places them in a list based on the values in the observations.
    
    if ( ob.getNumReadings()!=2) {
      throw new DimensionalityException();
    } 
    
    Node p;
    if ( list==null ) {
      p=null;
    } 
    else {
      
      p=list;
      
    }
    
    list2=null;
    
    int correctness=0;
    
    if ( list==null ) {
      
      list = new Node (ob, list);
      
    } 
    
    else if ( list.next==null ) {
      if ((ob.getReading(0)<p.item.getReading(0) && ob.getReading(1)>p.item.getReading(1)) || (ob.getReading(0)>p.item.getReading(0) && ob.getReading(1)<p.item.getReading(1))) {
        listAdder(ob, list);
      }
      else if ( ob.getReading(0)<p.item.getReading(0) && ob.getReading(1)<p.item.getReading(1) ) {
        clear();
        list = new Node (ob, list);
      } 
      
      else if ( ob.getReading(0)==p.item.getReading(0) && ob.getReading(1)<p.item.getReading(1) || ob.getReading(0)<p.item.getReading(0) && ob.getReading(1)==p.item.getReading(1)) {
        clear();
        list = new Node (ob, list);
      } 
    } 
    
    else if ( list.next!=null ) {
      
      if ( (ob.getReading(0)<p.item.getReading(0) && ob.getReading(1)>p.item.getReading(1)) || (ob.getReading(0)>p.item.getReading(0) && ob.getReading(1)<p.item.getReading(1)) ) {
        
        for ( int i=1; i<=getSize(); i++ ) { 
          
          if ( (ob.getReading(0)<p.item.getReading(0) && ob.getReading(1)>p.item.getReading(1)) || (ob.getReading(0)>p.item.getReading(0) && ob.getReading(1)<p.item.getReading(1)) ) { 
            correctness++;
          } 
          p=p.next;
        } 
        if ( correctness==getSize() ) {
          if (list==null) {
            list = new Node (ob, list);
          } else {
            listAdder(ob, list);
          }
        } 
      }
      else if ( ob.getReading(0)<p.item.getReading(0) && ob.getReading(1)<p.item.getReading(1) ) {
        Node r=list;
        for ( int i=1; i<=getSize(); i++ ) {
          if ( (ob.getReading(0)<r.item.getReading(0) && ob.getReading(1)>r.item.getReading(1)) || (ob.getReading(0)>r.item.getReading(0) && ob.getReading(1)<r.item.getReading(1)) ) {
            System.out.println(i + " " + r.item.getLabel());
            if ( list2==null ) {
              list2 = new Node (r.item, list2);
              System.out.println("list item " + list2.item.getLabel());
            }
            else {
              listAdder(r.item, list2);
              System.out.println("list item " + list2.next.item.getLabel());
            }
          } 
          r=r.next;
        } 
        if (list2==null) {
          list2=new Node (ob, list2);
        }
        else {
          listAdder(ob, list2);
        }
        
        list=list2;
      } 
    } 
  }
  
 
  public String toString () { //Makes the Label by getting the first character of each and placing them in an array 
    
    String labelMaker[] = new String[getSize()];
    Node p = list;
    
    for ( int i=0; i<getSize(); i++ ) {
      labelMaker[i] = Character.toString(p.item.getLabel());
      p=p.next;
    } 
    
    String labels =(String)(labelMaker[0]);
    
    for ( int i=1; i<getSize(); i++ ) {
      labels = labels + " , " + labelMaker[i];
    }
    
    return labels = "[" + labels+ "]";
    
  } 
  
  public Observation getBestForIndex ( int i ) { //Finds which observation has the best value considering it's index
    
    if ( i<0 || i>1) {
      throw new DimensionalityException();
    }  
    
    Observation result = null;
    Node p=list;
    double x;
    double y;
    
    x = 0; 
    y = 0;
    
    y = p.item.getReading(i);
    
    if ( list==null ) {
      throw new UnderflowException();
    } 
    
    
    for ( int j=1; j<=getSize(); j++ ) {
      x = p.item.getReading(i);
      
      if ( x<y ) {
        y=x;
      }
      
      p=p.next;
      
    } 
    
    Node a=list;
    Node b=null;
    
    while ( a != null && !( (y)==(a.item.getReading(i)) ) ) {
      b=a;
      a=a.next;
    }
    
    if ( a == null ) {
      result = null;
    }
    else {
      result = a.item;
    }
    
    return result;
    
  } 
  
  
  
  public Observation[] getAll () { //Gathers all the Obseervations and Finds the size of the Pareto Set.
    
    Observation result[] = new Observation[getSize()];
    Node p = list;
    
    for ( int i=0; i< getSize(); i++ ) {
      result[i] = p.item;
      p=p.next;
    }
    
    return result;
  } 
  
  
} // LnkParetoSet
