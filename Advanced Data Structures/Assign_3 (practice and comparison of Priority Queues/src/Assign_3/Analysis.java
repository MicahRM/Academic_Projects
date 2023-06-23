package Assign_3;

/** This class is an implementation of the Analysis class for this Priority Queue Manipulation program.
 * Micah Rose-Mighty
 * 6498935
 * 2020-11-13
 * Created using IntelliJ
 */

import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Analysis {

    public static void printTime(PQ pq, PrintWriter out){ // Method to Print the time analysis of the different types of Priority Queues to the Output File
        Random random = new Random();

        List<Integer> list50 = random.ints(50, 1, 1000).boxed().collect(Collectors.toList());

        Instant start50 = Instant.now();
        list50.stream().forEach(i-> pq.insert(i));
        list50.stream().forEach(i->pq.deleteMin());
        Instant end50 = Instant.now();

        List<Integer> list100 = random.ints(100, 1, 1000).boxed().collect(Collectors.toList());

        Instant start100 = Instant.now();
        list100.stream().forEach(i-> pq.insert(i));
        list100.stream().forEach(i->pq.deleteMin());
        Instant end100 = Instant.now();

        List<Integer> list1000 = random.ints(1000, 1, 1000).boxed().collect(Collectors.toList());

        Instant start1000 = Instant.now();
        list1000.stream().forEach(i-> pq.insert(i));
        list1000.stream().forEach(i->pq.deleteMin());
        Instant end1000 = Instant.now();

        List<Integer> list5000 = random.ints(5000, 1, 1000).boxed().collect(Collectors.toList());

        Instant start5000 = Instant.now();
        list5000.stream().forEach(i-> pq.insert(i));
        list5000.stream().forEach(i->pq.deleteMin());
        Instant end5000 = Instant.now();

        List<Integer> list10000 = random.ints(10000, 1, 1000).boxed().collect(Collectors.toList());

        Instant start10000 = Instant.now();
        list10000.stream().forEach(i-> pq.insert(i));
        list10000.stream().forEach(i->pq.deleteMin());
        Instant end10000 = Instant.now();

        out.println("Analysing time of operation");
        out.printf("%-6s %-15s%n", "n", "Duration");
        out.printf("----------------------%n");
        out.printf("%-6s %-15d ns %n", "50", Duration.between(start50, end50).toNanos());
        out.printf("%-6s %-15d ns %n", "100", Duration.between(start100, end100).toNanos());
        out.printf("%-6s %-15d ns %n", "1000", Duration.between(start1000, end1000).toNanos());
        out.printf("%-6s %-15d ns %n", "5000", Duration.between(start5000, end5000).toNanos());
        out.printf("%-6s %-15d ns %n", "10000", Duration.between(start10000, end10000).toNanos());


    }

    public static void printArrayTime(ArrayHeapPQ pq, PrintWriter out){//Method to Print the time analysis of the Array Heap type of Priority Queue to the Output File
        Random random = new Random();
        //50 100 1000 5000 10000
        List<Integer> list50 = random.ints(50, 1, 1000).boxed().collect(Collectors.toList());

        Instant start50 = Instant.now();
        list50.stream().forEach(i-> pq.normalInsert(i));
        pq.buildHeap();
        list50.stream().forEach(i->pq.deleteMin());
        Instant end50 = Instant.now();

        List<Integer> list100 = random.ints(100, 1, 1000).boxed().collect(Collectors.toList());

        Instant start100 = Instant.now();
        list100.stream().forEach(i-> pq.normalInsert(i));
        pq.buildHeap();
        list100.stream().forEach(i->pq.deleteMin());
        Instant end100 = Instant.now();

        List<Integer> list1000 = random.ints(1000, 1, 1000).boxed().collect(Collectors.toList());

        Instant start1000 = Instant.now();
        list1000.stream().forEach(i-> pq.normalInsert(i));
        pq.buildHeap();
        list1000.stream().forEach(i->pq.deleteMin());
        Instant end1000 = Instant.now();

        List<Integer> list5000 = random.ints(5000, 1, 1000).boxed().collect(Collectors.toList());

        Instant start5000 = Instant.now();
        list5000.stream().forEach(i-> pq.normalInsert(i));
        pq.buildHeap();
        list5000.stream().forEach(i->pq.deleteMin());
        Instant end5000 = Instant.now();

        List<Integer> list10000 = random.ints(10000, 1, 1000).boxed().collect(Collectors.toList());

        Instant start10000 = Instant.now();
        list10000.stream().forEach(i-> pq.normalInsert(i));
        pq.buildHeap();
        list10000.stream().forEach(i->pq.deleteMin());
        Instant end10000 = Instant.now();

        out.println("Analysing time of operation");
        out.printf("%-6s %-15s%n", "n", "Duration");
        out.printf("----------------------%n");
        out.printf("%-6s %-15d ns %n", "50", Duration.between(start50, end50).toNanos());
        out.printf("%-6s %-15d ns %n", "100", Duration.between(start100, end100).toNanos());
        out.printf("%-6s %-15d ns %n", "1000", Duration.between(start1000, end1000).toNanos());
        out.printf("%-6s %-15d ns %n", "5000", Duration.between(start5000, end5000).toNanos());
        out.printf("%-6s %-15d ns %n", "10000", Duration.between(start10000, end10000).toNanos());


    }
}

