package p2;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        String pth =  "text.txt";
        Reader rdr = new Reader(pth);
        rdr.readFile();
    }
}
