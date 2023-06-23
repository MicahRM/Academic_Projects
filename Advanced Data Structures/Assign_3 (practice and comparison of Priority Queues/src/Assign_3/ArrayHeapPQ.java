package Assign_3;


/** This class is an implementation of the Array Heap Priority Queue class for this Priority Queue Manipulation program.
 * Micah Rose-Mighty
 * 6498935
 * 2020-11-13
 * Created using IntelliJ
 */


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayHeapPQ<T extends Comparable<T>> implements PQ<T>{

    T[] pq;
    int N ;
    int size = 0;
    PrintWriter out;

    ArrayHeapPQ(){
        this(10000);
    }
    ArrayHeapPQ(int capacity){
        pq = (T[]) new Comparable[capacity+1];
        try {
            out = new PrintWriter("ArrayHeap.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public boolean isEmpty(){
        return N == 0;
    }




    @Override
    public void transverse() {//Traversal Method for the Array Heap implementation of the priority queue
        StringBuilder builder = new StringBuilder();
        preorder(builder,1);
        String s = Arrays.stream(builder.toString().trim().split(" ")).collect(Collectors.joining(",", "[","]"));
        out.println(s);
    }

    private void preorder(StringBuilder builder, int root){// Preorder Traversal Method for the Ordered Array Heap implementation of the priority queue
        if(root>=0 && root <= N){
            builder.append(pq[root]+" ");
            preorder(builder,2*root);
            preorder(builder,2*root+1);
        }

    }

    @Override
    public T deleteMin() {//deleteMin Method for the Array Heap implementation of the priority queue
        T min = pq[1];
        T hold = pq[N];
        pq[N] = pq[1];
        pq[1] = hold;
        pq[N--] = null;
        size--;
        sink(1);
        return min;
    }

    @Override
    public void insert(T x) {//Insertion Method for the Array Heap implementation of the priority queue
        pq[++N] = x;
        size++;
        buildHeap();
    }

    public void normalInsert(T x) {//Insertion Method for the Array Heap implementation of the priority queue that does not build the heap
        pq[++N] = x;
        size++;
    }

    private void swim(int k){
        while (k > 1 && less(k, k/2)){
            T hold = pq[k];
            pq[k] = pq[k/2];
            pq[k/2] = hold;
            k = k/2;
        }
    }

    private void minHeapify(int i){ // Method to create the minheap from the given data input using array heap implementation
        int left = 2*i;
        int right = 2*i+1;
        int m;
        if (left <= N && less(left, i))
            m = left;
        else m = i;
        if (right <=N && less(right, m))
            m = right;
        if (m!=i){
            T hold = pq[i];
            pq[i] = pq[m];
            pq[m] = hold;
            minHeapify(m);
        }

    }

    public void buildHeap(){//Method to build the heap using the minHeapify method
        for (int i=N/2; i>=1; i--){
            minHeapify(i);
        }
    }

    private boolean less(int key1Pos, int key2Pos){// method for comparing the keys of different elements in the array heap
        return pq[key1Pos].compareTo(pq[key2Pos]) < 0;
    }

    private void sink(int k){ // method for doing swaps within the array heap to keep the minheap characteristics true
        while(2*k <= N){
            int j = 2*k;
            if (j< N && less(j+1, j)) j++;
            if (!less(j, k)) break;
            T hold = pq[k];
            pq[k] = pq[j];
            pq[j] = hold;
            k =j;
        }
    }


    public void close(){
        out.close();
    }

    public static void main(String[] args){// main method for running the ArrayHeap Priority Queue implementation
        ArrayHeapPQ<Integer> pq = new ArrayHeapPQ<>();
        List<Integer> priorityList = MyFileReader.priorityList();
        pq.out.println("-------------------- i -------------------");
        for (Integer i: priorityList){
            pq.insert(i);

        }
        pq.out.print("Transversing using PreOrder");
        pq.transverse();
        for (Integer i: priorityList){
            pq.out.println(pq.deleteMin());
        }
        pq.out.println("-------------------- ii -------------------");
        for (Integer i: priorityList){
            pq.normalInsert(i);
        }
        pq.out.print("Transversing using PreOrder Before Build Heap ");
        pq.transverse();
        pq.buildHeap();
        pq.out.print("Transversing using PreOrder After Build Heap ");
        pq.transverse();
        for (Integer i: priorityList){
            pq.out.println(pq.deleteMin());
        }
        Analysis.printTime(pq, pq.out);
        pq.out.println("\n--------Analysing time of Operation of Array Heap when all elements are inserted before buildHeap is called------");
        Analysis.printArrayTime(pq, pq.out);
        pq.close();





    }

}
