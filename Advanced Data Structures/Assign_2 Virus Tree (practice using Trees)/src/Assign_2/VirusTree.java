package Assign_2;

/** This class is an implementation of the VirusTree Structure along with all the required methods.
 * Micah Rose-Mighty
 * 6498935
 * 2020-10-08
 * Created using IntelliJ
 */

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class VirusTree {

    private VirusTreeNode root; // tree root node

    public VirusTree() { root = null; } // empty tree constructor

    public VirusTree(String filename){ // Constructor that creates tree structure from given input file
        this();
        try(Scanner scanner = new Scanner(new File(filename))) {
            while(scanner.hasNextLine()){
                String[] parts = scanner.nextLine().trim().split(",");

                for(int i = parts.length-1; i>=1; i--){
                    if(!insert(parts[0], parts[i])){
                        throw new IllegalArgumentException("Can not find parent node: " + parts[0]);
                    }
                }
                System.out.println(MessageFormat.format("{0}: {1}", parts[0], String.join(" -> ", Arrays.copyOfRange(parts, 1, parts.length))));
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int depth() { // Method for getting the depth of the given tree using inner recursive method for calculation.
        if(root == null){
            return 0;
        }
        return depthStep(root);
    }

    public void bfs(){ // Method for outputting the given tree nodes in breadth-first traversal order.
        if (root == null) {
            return;
        }

        LinkedList<VirusTreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        while(!queue.isEmpty()){
            VirusTreeNode current = queue.removeFirst();
            System.out.println(current.info);

            VirusTreeNode currChild = current.firstChild;
            while(currChild != null){
                queue.addLast(currChild);
                currChild = currChild.nextSibling;
            }
        }
    }

    public void preorder(){ // Method for outputting the given tree in Pre-order Traversal Order by calling traverse method and setting it to true.
        traverse(true);
    }

    public void postorder(){ // Method for outputting the given tree in Post-order Traversal Order by calling traverse method and setting it to false.
        traverse(false);
    }

    public void distance(String info1, String info2){ //Method for outputting the distance between two nodes in the given tree.
        if(root == null) {
            throw new IllegalArgumentException("Can't find node: " + info1);
        }

        LinkedList<String> path1 = getPath(root,info1, new LinkedList<>());
        if(path1 == null){
            throw new IllegalArgumentException("Can't find node: " + info1);
        }

        LinkedList<String> path2 = getPath(root, info2, new LinkedList<>());
        if(path2 == null){
            throw new IllegalArgumentException("Can't find node: " + info2);
        }

        for (int i = path1.size() - 1; i>=0; i--) { // For loop to find last common ancestor starting from last node in first path and if found in second path then that is common ancestor.
            String pathInfo = path1.get(i);
            int index = path2.indexOf(pathInfo);
            if(index >= 0) {
                int distance = Math.max(path1.size() - 1 - i, path2.size() - 1 - index);
                System.out.println(MessageFormat.format("The distance between {0} and {1} is {2}. They have common ancestor {3}.", info1, info2, distance, pathInfo));
                return;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format("There does not exist a common ancestor node for {0} and {1}", info1, info2));
    }

    private LinkedList<String> getPath(VirusTreeNode currentNode, String info, LinkedList<String> currentList) { // Private recursive method for searching a path to a given node.
        String currentInfo = currentNode.info;
        currentList.add(currentInfo);
        if(currentInfo.equals(info)) {
            return currentList;
        }

        VirusTreeNode currChild = currentNode.firstChild;
        while (currChild != null){
            LinkedList<String> result = getPath(currChild, info, currentList);
            if(result != null) {
                return result;
            }
            currChild = currChild.nextSibling;
        }
        currentList.removeLast();
        return null;
    }

    private void traverse(boolean preorder) { // Inner traverse method the outputs Pre-order Traversal if true and Post-order Traversal if false.
        if (root == null) {
            return;
        }
        traverseStep(root, preorder);
    }

    private void traverseStep(VirusTreeNode currentNode, boolean preorder) { // Inner recursive traverse order method that is based on the given node.

        if (preorder) {
            System.out.println(currentNode.info);
        }

        VirusTreeNode currChild = currentNode.firstChild;
        while(currChild != null) {
            traverseStep(currChild,preorder);
            currChild = currChild.nextSibling;
        }

        if (!preorder) {
            System.out.println(currentNode.info);
        }
    }

    private int depthStep(VirusTreeNode currentNode) { // Inner recursive method for calculating depth.
        int max = 0;

        VirusTreeNode currChild = currentNode.firstChild;
        while (currChild != null) {
            max = Math.max(max, 1 + depthStep(currChild));
            currChild = currChild.nextSibling;
        }
        return max;
    }

    private boolean insert(String parentInfo, String childInfo) { // Private method for adding parent-child pair into the given tree. Returns true if pair is added successfully and false otherwise.

        if (root == null) {
            root = new VirusTreeNode(parentInfo);
            root.firstChild =  new VirusTreeNode(childInfo);
            root.nextSibling = null;
            root.firstChild.firstChild = null;
            root.firstChild.nextSibling = null;

            return true;
        }
        return insertStep(root, parentInfo, childInfo);
    }

    private boolean insertStep(VirusTreeNode currentNode, String parentInfo, String childInfo) { // Inner recursive method for inserting a parent-child pair into the given tree. Also returns true if pair was successfully added and false otherwise.

        if (currentNode.info.equals(parentInfo)) {
            VirusTreeNode newNode = new VirusTreeNode(childInfo);
            newNode.firstChild = null;
            newNode.nextSibling = currentNode.firstChild;
            currentNode.firstChild = newNode;

            return true;
        }
        VirusTreeNode currChild = currentNode.firstChild;
        while (currChild != null) {
            if (insertStep(currChild, parentInfo, childInfo)) {

                return true;
            }
            currChild = currChild.nextSibling;
        }
        return false;
    }
}
