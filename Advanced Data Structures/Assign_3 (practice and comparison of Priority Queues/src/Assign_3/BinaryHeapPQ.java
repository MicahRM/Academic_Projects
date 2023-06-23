package Assign_3;

/** This class is an implementation of the Binary Tree Heap class for this Priority Queue Manipulation program.
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

public class BinaryHeapPQ<T extends Comparable<T>> implements PQ<T>{

    PrintWriter out;

    BinaryHeapPQ(){
        try{
            out = new PrintWriter("BinaryHeapPQ.txt");
        } catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }

    @Override
    public void transverse(){//Traversal Method for the Binary Heap implementation of the priority queue
        StringBuilder builder = new StringBuilder();
        preorder(builder,root);
        String s = Arrays.stream(builder.toString().trim().split(" ")).collect(Collectors.joining(",", "[", "]"));
        out.println(s);
    }

    @Override
    public T deleteMin() {//deleteMin Method for the Binary Heap implementation of the priority queue
        T minVal = minValue(root);
        deleteKey(minVal);
        return minVal;
    }


       static class Node<T extends  Comparable<T>> {
        T key;
        Node left, right;


        public Node(T data){// Node class for this Binary Tree Implentation of the Priority Queue
            key = data;
            left = right = null;
        }
       }

       Node root;


       void deleteKey(T key) {// method for deleting a node of a certain key within the binary heap
           root = delete_Recursive(root, key);
       }

       Node delete_Recursive(Node root, T key) {//another method for deleting a node of a certain key within the binary tree
           if (root == null) return root;

           if(key.compareTo((T) root.key) < 0)
               root.left = delete_Recursive(root.left, key);
           else if (key.compareTo((T) root.key) > 0)
               root.right = delete_Recursive(root.right, key);
           else {
               if (root.left == null)
                   return root.right;
               else if (root.right == null)
                   return root.left;

               root.key = minValue(root.right);
               root.right = delete_Recursive(root.right, (T) root.key);
       }
       return root;
    }

    T minValue(Node root) {// method for getting the smallest value of the heap
           T minval = (T) root.key;

           while (root.left != null) {
               minval = (T) root.left.key;
               root = root.left;
           }
           return minval;
    }

    @Override
    public void insert(T key) {// method for inserting a node into the binary heap based on key
           root = insert_Recursive(root, key);
    }

    Node insert_Recursive(Node root, T key) {// recursive insertion method
           if (root == null){
               root = new Node(key);
               return root;
           }
           if (key.compareTo((T) root.key) < 0)
               root.left = insert_Recursive(root.left, key);
           else if (key.compareTo((T) root.key) >= 0)
               root.right = insert_Recursive(root.right, key);
           return root;

    }

    private void preorder(){
           StringBuilder builder = new StringBuilder();
           preorder(builder,root);
    }

    private void preorder(StringBuilder builder, Node root) { // method for preorder traversal of the binary heap implementation
           if (root != null) {
               builder.append(root.key+" ");
               preorder(builder,root.left);
               preorder(builder, root.right);
           }
    }

    private void close(){
           out.close();
    }

    public static void main(String[] args){// main method for running this binary heap priority queue implementation.
    BinaryHeapPQ<Integer> pq = new BinaryHeapPQ<>();
        List<Integer> priorityList = MyFileReader.priorityList();
        for (Integer i: priorityList){
            pq.insert(i);
        }
        pq.transverse();
        for (Integer i: priorityList) {
            pq.out.println(pq.deleteMin());
        }

        Analysis.printTime(pq, pq.out);
        pq.close();

    }



}

