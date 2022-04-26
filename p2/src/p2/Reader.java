package p2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    String path;

    public Reader(String path) {
        this.path = path;
    }

    public void readFile() throws FileNotFoundException {
        BufferedReader in = new BufferedReader( new FileReader(path) );
        String CurrLine = null;

        try{
            while( (CurrLine = in.readLine()) != null ){
                System.out.println(CurrLine);

                // todo
                // add ifs there or sth
                //PRGraphImpl graph_test = new PRGraphImpl();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
