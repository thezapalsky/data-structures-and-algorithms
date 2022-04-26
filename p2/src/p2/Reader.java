package p2;

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
        PRGraphImpl graph_test = new PRGraphImpl();
        String CurrLine = null;


        try{
            while( (CurrLine = in.readLine()) != null ){
                //System.out.println(CurrLine);

                String first = CurrLine.split(" ")[0];
                //System.out.println(first);

                if(Objects.equals(first, "OPEN")){
                    graph_test.open(CurrLine.split(" ")[1], CurrLine.split(" ")[2]);
                }
                else if(Objects.equals(first, "CLOSE")){
                    graph_test.close(CurrLine.split(" ")[1], CurrLine.split(" ")[2]);
                }
                else if(Objects.equals(first, "PROCESS")){
                    graph_test.addProcess(CurrLine.split(" ")[1]);
                }
                else if(Objects.equals(first, "RESOURCE")){
                    graph_test.addResource(CurrLine.split(" ")[1]);
                }
            }
            System.out.println(graph_test);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (DeadlockException e) {
            e.printStackTrace();
        }
    }
}
