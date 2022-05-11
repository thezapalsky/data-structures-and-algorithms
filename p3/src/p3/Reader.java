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

    public void readFile() throws FileNotFoundException {
        BufferedReader in = new BufferedReader( new FileReader(path) );
        String CurrLine = null;

        // create graph here //todo

        try{
            while( (CurrLine = in.readLine()) != null ){

                String[] strs = CurrLine.split(" ");
                // create 'relation here' with
                // strs[0], strs[1], ... //todo
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
