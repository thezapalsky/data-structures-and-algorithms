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

        // bla bla ...


    }

//    BufferedReader in = new BufferedReader( new FileReader(path) );
//    String CurrLine = null;
//    HashMap<String, String> HM = new HashMap<>();
//
//        try {
//        int lineNumber=1;
//        int colNumber=0;
//        while ((CurrLine = in.readLine()) != null) {
//            //System.out.println(CurrLine.split(" "));
//
//            for(String word: CurrLine.toLowerCase().split(" ")){
//                colNumber++; //because of the space
//
//                //System.out.println(word.replaceAll("[.,]", "") ); //should we care about these?
//                //System.out.println(word + " (" + lineNumber + ", " + colNumber + ")" );
//
//                String pos =  "(" + lineNumber + ":" + colNumber + ")";
//                HM.put(word, pos);
//
//                colNumber+=word.length();
//            }
//            colNumber=0;
//            lineNumber++;
//        }
//
//        return HM;
//    }
//        catch (IOException e) {
//        e.printStackTrace();
//        return null;
//    }
}
