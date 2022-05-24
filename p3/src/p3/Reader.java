package p3;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Reader {
    String path;

    public Reader(String path) {
        this.path = path;
    }

    public Graph readFile() throws FileNotFoundException {
        BufferedReader in = new BufferedReader( new FileReader(path) );
        String CurrLine = null;

        Graph g = new Graph();

        try{
            while( (CurrLine = in.readLine()) != null ){

                String[] strs = CurrLine.split(" ");
                g.add(Integer.parseInt(strs[0]),
                        Integer.parseInt(strs[1]),
                        Integer.parseInt(strs[2]),
                        Integer.parseInt(strs[3]),
                        Integer.parseInt(strs[4]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return g;
    }
}
