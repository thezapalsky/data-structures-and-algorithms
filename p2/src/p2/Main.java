package p2;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        String pth =  "/Users/mikolaj/Desktop/DS/data-structures-and-algorithms/p2/text.txt";
        Reader rdr = new Reader(pth);
        rdr.readFile();
    }
}
