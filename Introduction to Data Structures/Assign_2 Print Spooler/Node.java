package Assign_2;

/**This class defines the nodes being used to create the list of print jobs.
  * Micah Rose-Mighty
  * 6498935
  * 2020/02/24
  */


class Node {
  
    PrintJob item;   // the details of the requested documents to be printed
    Node  next;  // next node
    
    
    Node ( PrintJob p, Node n ) { //creates nodes that are sequentilly linked.
        
        item = p;
        next = n;
        
    };  // constructor
    
    
}  //Node