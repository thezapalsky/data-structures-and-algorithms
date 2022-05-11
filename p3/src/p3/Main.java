package p3;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String pth =  "input.txt";
        Reader rdr = new Reader(pth);
        rdr.readFile(); // return Graph

        // and then paint it? // todo

    }
}
