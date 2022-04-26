package p2;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    // todo -> reader from the file

        //PRGraphImpl graph_test = new PRGraphImpl();
        Reader rdr = new Reader("/Users/mikolaj/Desktop/DS/data-structures-and-algorithms/p2/text.txt");
        rdr.readFile();

    }
}
