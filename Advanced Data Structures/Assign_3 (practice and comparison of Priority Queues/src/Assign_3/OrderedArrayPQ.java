package Assign_3;

/** This class is an implementation of the Ordered Linked List class for this Priority Queue Manipulation program.
 * Micah Rose-Mighty
 * 6498935
 * 2020-11-13
 * Created using IntelliJ
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class OrderedArrayPQ<T extends Comparable<T>> implements PQ<T>{
    T[] pq;
    int N;
    int size;

    PrintWriter out;

    public OrderedArrayPQ(){
        this(10000);
    }

    public OrderedArrayPQ(int capacity){
        pq = (T[]) new Comparable[capacity+1];
        try {
            out = new PrintWriter("OrderedArray.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }


    @Override
    public void transverse() { //Traversal Method for the Ordered Linked List implementation of the priority queue
        out.print("[");
      for(int i=0; i<N; i++){
          if(i<N-1)
              out.printf("%d,", pq[i]);
          else
              out.printf("%d", pq[i]);
      }
      out.println("]");
    }

    @Override
    public T deleteMin() { //deleteMin Method for the Ordered Linked List implementation of the priority queue
     T min = pq[N-1];
     pq[N--] = null;
     return min;
    }

    @Override
    public void insert(T key) { //Insertion Method for the Ordered Linked List implementation of the priority queue
        pq[N] = key;
          int i = N-1;
          while (i>=0 && pq[i].compareTo(key)<0){
              pq[i+1] = pq[i];
              i = i-1;
          }
          pq[i+1]=key;
          N++;
    }

    public void close(){ //close method for the output txt file
        out.close();
    }

    public static void main(String[] args){ //Main method for the Ordered Linked List implementation of the priority queue
        OrderedArrayPQ<Integer> pq = new OrderedArrayPQ<>();
        List<Integer> priorityList = MyFileReader.priorityList();
        for (Integer i: priorityList){
            pq.insert(i);
        }
        pq.transverse();
        for (Integer i: priorityList){
            pq.out.println(pq.deleteMin());
        }

        Analysis.printTime(pq, pq.out);
        pq.close();

    }

}
