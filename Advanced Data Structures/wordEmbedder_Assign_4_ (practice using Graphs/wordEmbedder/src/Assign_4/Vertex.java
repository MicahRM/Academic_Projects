package Assign_4;

/** This class is an implementation of the Vertex class for this word-embedding program.
 * Micah Rose-Mighty
 * 6498935
 * 2020-12-04
 * Created using IntelliJ
 */


import java.util.Objects;

public class Vertex implements Comparable<Vertex>{
    String word;

    Vertex(String word){
        setWord(word);
    }

    public String getWord(){ //accessor method to get word
        return word;
    }

    public void setWord(String word){ //mutator method to set a word
        this.word = word;
    }

    @Override
    public boolean equals(Object o){ //method that returns true if the word matches and false otherwise.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(word, vertex.word);
    }

    @Override
    public int hashCode(){ //method that returns the hashCode for the word
        return Objects.hash(word);
    }

    @Override
    public String toString(){ //toString method to return a word at a vertex if needed.
        return "Vertex{" + "word='" + word + '\'' + '}';
    }

    @Override
    public int compareTo(Vertex o){ //method to compare words at different vertices
        return this.getWord().compareTo(o.getWord());
    }
}
