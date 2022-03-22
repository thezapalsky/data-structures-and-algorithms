package p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        // todo -> docs

        /**
         * d
         */

        //ArrayTest();
        //HashMapTest();

        //String path = "/Users/mikolaj/Desktop/DS/p1/text.txt";
        String path = "text.txt";
        //String path =  args[0];

        try {
            HashMap<String, String> HM = FileToHashMap(path);
            System.out.println(HM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static HashMap FileToHashMap(String path) throws FileNotFoundException{


        BufferedReader in = new BufferedReader( new FileReader(path) );
        String CurrLine = null;
        HashMap<String, String> HM = new HashMap<>();

        try {
            int lineNumber=1;
            int colNumber=0;
            while ((CurrLine = in.readLine()) != null) {
                //System.out.println(CurrLine.split(" "));

                for(String word: CurrLine.toLowerCase().split(" ")){
                    colNumber++; //because of the space

                    //System.out.println(word.replaceAll("[.,]", "") ); //should we care about these?
                    //System.out.println(word + " (" + lineNumber + ", " + colNumber + ")" );

                    String pos =  "(" + lineNumber + ":" + colNumber + ")";
                    HM.put(word, pos);

                    colNumber+=word.length();
                }
                colNumber=0;
                lineNumber++;
            }

            return HM;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void HashMapTest(){
        // HashMap tests

        // constructor Test
        HashMap<String,String> m = new HashMap<>(9, 0.75f);

        // put Test
        m.put("adam","malysz");
        m.put("adam","mickiewicz");
        m.put("twoj","stary");
        m.put("mikoolaj", "dupeczka");
        m.put("gosia", "kasia");
        m.put("reksio", "pies");
        m.put("tomek", "lokomotywa");
        m.put("pepe", "pig");
        m.put("papaj", "czlowiek");

        System.out.println(m.toString());

        // getTest
        //System.out.println(m.get("papaj"));

        // iterator test
//        for (String key: m) {
//            if (key != null) {
//                System.out.println("K:"+key +" V:"+ m.get(key));
//            }
//        }

        // removeTest
        //System.out.println("\nremoved: " + m.remove("papaj"));
        //System.out.println(m.toString());

        // contains, size, isEmpty, clear Tests
        //System.out.println("\ncontains, size, isEmpty, clear Tests");
        //System.out.println(m.contains("adam"));
        //System.out.println(m.contains("mikoolaj"));
        //System.out.println(m.contains(""));
        //System.out.println(m.size());
        //System.out.println(m.isEmpty());
        //m.clear();
        //System.out.println(m.size());
        //System.out.println(m.isEmpty());


        // Tests from pdf

//        Map<Integer,String> m = new HashMap<>(); // create a dictionary
//        m.put(2, "dos");
//        m.put(3, "tres");
//        m.put(5, "cinco");
//
//        System.out.println(m.get(2)); // prints "dos"
//        System.out.println(m.get(3)); // prints "tres"
//        System.out.println(m.get(5)); // prints "cinco"
//        System.out.println(m); // prints "{2=dos, 3=tres, 5=cinco}"
    }

    private static void ArrayTest(){
        // Array tests

        Array<Integer> a1 = new Array<>(2);
        a1.add(11);
        a1.add(22);
        a1.add(33);

        //System.out.println(a1.size() );
        //System.out.println(a1.get(1));

        System.out.println(a1.toString());

        a1.clear();
        a1.add(123);

        System.out.println(a1.toString());

        System.out.println(a1.get(0));
        System.out.println(a1.get(1)); //should return exception or null?

        a1.set(1,222);
        a1.set(2,333);
        System.out.println(a1.toString());
        System.out.println(a1.size());

    }
}
