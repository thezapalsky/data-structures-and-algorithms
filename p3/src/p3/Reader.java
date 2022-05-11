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

        //Graph graph_test = new Graph();
        String CurrLine = null;

        try{
            while( (CurrLine = in.readLine()) != null ){
                //System.out.println(CurrLine);

                for (String s : CurrLine.split(" ")) {
                    System.out.println(s);
                }

                //System.out.println(first);

//                if(Objects.equals(first, "OPEN")){
//                    //graph_test.open(CurrLine.split(" ")[1], CurrLine.split(" ")[2]);
//                }
//                else if(Objects.equals(first, "CLOSE")){
//                    graph_test.close(CurrLine.split(" ")[1], CurrLine.split(" ")[2]);
//                }
//                else if(Objects.equals(first, "PROCESS")){
//                    graph_test.addProcess(CurrLine.split(" ")[1]);
//                }
//                else if(Objects.equals(first, "RESOURCE")){
//                    graph_test.addResource(CurrLine.split(" ")[1]);
//                }
            }
            //System.out.println(graph_test);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
