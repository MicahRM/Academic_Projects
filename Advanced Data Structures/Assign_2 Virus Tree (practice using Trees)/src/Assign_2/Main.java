package Assign_2;

/** This class is an implementation of the main class for this Virus Tree program.
 * Micah Rose-Mighty
 * 6498935
 * 2020-10-08
 * Created using IntelliJ
 */

import java.text.MessageFormat;

public class Main {

    public static void main(String[] args) {

        System.out.println("Tree Created: ");
        VirusTree vt = new VirusTree("tree_of_virus_input.txt");
        System.out.println();

        System.out.println("Depth of Tree: ");
        System.out.print(vt.depth());
        System.out.println();
        System.out.println();

        System.out.println("Breadth-First Traversal: ");
        vt.bfs();
        System.out.println();

        System.out.println("Pre-order Traversal: ");
        vt.preorder();
        System.out.println();

        System.out.println("Post-order Traversal: ");
        vt.postorder();
        System.out.println();

        String v1 = "HCoV-OC43";
        String v2 = "Hcov-229E";
        System.out.println(MessageFormat.format("Distance between {0} and {1}: ", v1, v2));
        vt.distance(v1, v2);
        System.out.println();

        String v3 = "SARS-CoV";
        String v4 = "Zika virus";
        System.out.println(MessageFormat.format("Distance between {0} and {1}: ", v3, v4));
        vt.distance(v3, v4);
        System.out.println();

    }
}
