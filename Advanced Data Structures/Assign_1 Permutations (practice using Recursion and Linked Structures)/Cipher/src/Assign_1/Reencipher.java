package Assign_1;

/** This class is an implementation of the Circular Linked List used to Reencipher a given word
 * Micah Rose-Mighty
 * 6498935
 * 2020-09-24
 * Created using IntelliJ
 */


public class Reencipher {

    private ReencipherNode head = null;
    private ReencipherNode tail = null;
    private final int step;
    private final int n;

    public Reencipher(String s, int step) { // This a Constructor that accepts the word to encipher and the corresponding enciphering parameter
        this.step = step;
        this.n = s.length();

        for (char c : s.toCharArray()) {
            add(c);
        }
    }

    public String encipher(){ //This is a method to actually generate the enciphered word
        ReencipherNode current = head;

        char [] chars = new char[n];
        int counter = 0;
        while(true) {
            for (int i = 0; i<step-2; i++){
                current = current.next;
            }

            if (current == current.next){
                chars[counter] = current.c;
                break;

            }

            chars[counter] = current.next.c;
            counter++;

            current.next = current.next.next;
            current = current.next;
        }
        return new String(chars);
    }


    private void add(char c){ // This is a private method that adds a character c correctly to the circular linked list

        ReencipherNode newNode = new ReencipherNode(c);

        if(head == null){
            head = newNode;
            tail = head;
        }
        else if(head == tail) {
            head.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
    }



}
