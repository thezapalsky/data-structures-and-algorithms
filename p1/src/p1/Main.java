package p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        System.out.println("--Testing Array class--");
        //ArrayTest();

        System.out.println("--Testing HashMap class--");
        //HashMapTest();
        
        System.out.println("--Main--");
        String path;

        if(args.length>0){
            path =  args[0];
        }
        else{
            path = "text.txt";
        }

        try {
            HashMap<String, String> HM = FileToHashMap(path);
            System.out.println(HM);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * Returns a hashMap with words as keys and it's positions as values.
     * @param path a String with path to the file
     * @return HashMap object
     * @throws FileNotFoundException
     */
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


    /**
     * Testing the methods of a hashMap object
     */
    private static void HashMapTest(){
        // HashMap tests

        // constructor Test
        HashMap<String,String> m = new HashMap<>(8, 0.75f);

        // put Test
        m.put("Leonardo","da Vinci");
        m.put("Leonardo","DiCaprio");
        m.put("Mariusz","Pudzianowski");
        m.put("Javier", "Bardem");
        m.put("Enrique", "Iglesias");
        m.put("Penelope", "Cruz");
        m.put("Adam", "Malysz");
        m.put("Antonio", "Banderas");
        m.put("Jon", "Doe");

        System.out.println(m.toString());

        System.out.println("-Get test-");
        // getTest
        System.out.println(m.get("Leonardo"));

        System.out.println("-Iterator test-");
        // iterator test
        for (String key: m) {
            if (key != null) {
                System.out.println("K:"+key +" V:"+ m.get(key));
            }
        }

        // removeTest
        System.out.println("-Remove test-");
        System.out.println("\nremoved: " + m.remove("Javier"));
        System.out.println(m.toString());


        // contains, size, isEmpty, clear Tests
        System.out.println("-Other tests-");
        //System.out.println("\ncontains, size, isEmpty, clear Tests");
        System.out.println(m.contains("Javier"));
        System.out.println(m.contains("Jon"));
        System.out.println(m.contains(""));
        System.out.println(m.size());
        System.out.println(m.isEmpty());

        m.clear();
        System.out.println(m.size());
        System.out.println(m.isEmpty());
    }


    /**
     * Testing the methods of a Array object
     */
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
