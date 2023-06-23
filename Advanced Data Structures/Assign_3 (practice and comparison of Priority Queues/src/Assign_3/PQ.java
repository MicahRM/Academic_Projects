package Assign_3;

/** This interface is an implementation of PQ or Priority Queue for this Priority Queue Manipulation program.
 * Micah Rose-Mighty
 * 6498935
 * 2020-11-13
 * Created using IntelliJ
 */


public interface PQ<T extends Comparable<T>> {

    public void transverse();

    public T deleteMin ();

    public void insert(T x);
}
