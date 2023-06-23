package Assign_3;


/** This class is an implementation of the File Reader class for this Priority Queue Manipulation program.
 * Micah Rose-Mighty
 * 6498935
 * 2020-11-13
 * Created using IntelliJ
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyFileReader {

    public static List<Integer> priorityList (){
        List<Integer> list = new ArrayList<Integer>();
        try (BufferedReader reader = new BufferedReader(new FileReader("assn3in.txt"))) {
            int length = Integer.parseInt(reader.readLine());
            list = Arrays.stream(reader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void write(String fileName, String value){
        try {
            PrintWriter writer  = new PrintWriter(fileName);
            writer.write(value);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }


}