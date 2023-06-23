package Assign_1;

/** This class is an implementation of the Permutation Instance
 * Micah Rose-Mighty
 * 6498935
 * 2020-09-24
 * Created using IntelliJ
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Permutation {

    private final int n;
    private final int[] values;


    public static Collection<Permutation> generatePermutations(int n) { //This is a Static method that creates a Collection that contains all the permutations of the given length
        int [] elements = new int[n];
        for (int i = 0; i<n; i++) {
            elements[i] = i;
        }
        Collection<Permutation> result = new ArrayList<>();
        for (int[] values : permute(n,elements)) {
            result.add(new Permutation(values));
        }
        return result;
    }


    private static Collection<int[]> permute(int l, int[] values) { //This is a Recursive Method for creating all the permutations.
        Collection<int[]> result = new ArrayList<>();

        if(l == 1) {
            result.add(values.clone());
        }
        else {
            for (int i: values) {
                int[] trunc = new int[values.length-1];
                int counter = 0;
                for (int value : values) {
                    if (value == i){
                        continue;
                    }
                    trunc[counter] = value;
                    counter++;
                }
                Collection<int[]> shorters = permute(l-1,trunc);
                for (int[] shorter : shorters) {
                    int[] newValues = new int[shorter.length+1];
                    System.arraycopy(shorter,0,newValues,1, shorter.length);
                    newValues[0] = i;
                    result.add(newValues);
                }
            }
        }
        return result;
    }
    private Permutation(int[] values){ // This is a Private Constructor created for use in the generatePermutations method
        this.n = values.length;
        this.values = values.clone();
    }
    public String apply(String s) { // This is a method for permuting the characters of the given string with the correct permutation pattern
        if (s.length() % n != 0){
            throw new IllegalArgumentException("The length of this string must be a multiple of the permutation size since we can't have a partial permutation.");
        }
        int parts = s.length() / n;
        char[] chars = s.toCharArray();
        char[] charsPermuted = new char[s.length()];
        for (int i = 0; i<parts; i++) {
            for (int j = 0; j<n; j++){
                charsPermuted[i*n + j] = chars[i*n + values[j]];
            }
        }
        return new String(charsPermuted);
    }
    @Override
    public String toString(){ // This is an overridden toString Method used to create the proper representation of the permutation
        return "[" + Arrays.stream(values).mapToObj(i -> Integer.valueOf(i+1).toString()).collect(Collectors.joining("")) + "]";

    }
}
