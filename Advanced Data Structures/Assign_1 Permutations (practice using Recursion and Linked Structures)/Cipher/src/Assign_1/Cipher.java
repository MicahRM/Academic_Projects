package Assign_1;

/** This class is an implementation of the main class for this program called Cipher.
 * Micah Rose-Mighty
 * 6498935
 * 2020-09-24
 * Created using IntelliJ
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.MessageFormat;
import java.util.*;


public class Cipher {

    public static void main(String[] args) throws FileNotFoundException {

        File fileText = new File("assn1in.txt");

        Scanner s = new Scanner(fileText);

        int n = s.nextInt();
        int m = s.nextInt();
        String cipherText = s.next();
        assert cipherText.length() == n;
        String knownWord = s.next();
        assert knownWord.length() == m;
        int k = s.nextInt();
        int i = s.nextInt();

        Collection<Permutation>  acceptedPermutations = new ArrayList<>(); // This is a Collection of accepted Permutations that match the known word
        String wordSegment = cipherText.substring(0,m);
        System.out.println("n = " + n + " m = "+ m + " Ciphertext = " + cipherText + " Known word = "+ knownWord + " k = " + k +" i = " + i + " Word Segment = "+ wordSegment);
        s.close();

        for (Permutation permutation:Permutation.generatePermutations(m)){ // Applies all the m-length permutations to the word segment
            String permuted = permutation.apply(wordSegment);
            System.out.println(MessageFormat.format("{0} {1}", permutation, permuted));

            if(knownWord.equals(permuted)){ //Adds all permutations that match the known word to the acceptedPermutations ArrayList
                acceptedPermutations.add(permutation);
            }

        }

        int counter  = 1;
        for (Permutation permutation : acceptedPermutations) { // This counts all accepted permutations to re-encrypt the k'th potential plaintext
            String decrypted = permutation.apply(cipherText);
            System.out.println(decrypted);

            if (counter == k) {
                Reencipher reencipher = new Reencipher(decrypted, i);
                System.out.println(reencipher.encipher());
            }
            counter++;
        }

        }}


