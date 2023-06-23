
public class Reencipher {

    private ReencipherNode head = null;
    private ReencipherNode tail = null;
    private final int step;
    private final int n;

    public Reencipher(String s, int step) {
        this.step = step;
        this.n = s.length();

        for (char c : s.toCharArray()) {
            add(c);
        }
    }

    public String encipher(){
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


    private void add(char c){

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
