package p3;

import java.io.FileNotFoundException;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //String pth =  "input.txt";
        String pth =  "input2.txt";
        Reader rdr = new Reader(pth);
        Graph g = rdr.readFile();

        //System.out.println(g);

        //minheap stuff //todo
        PriorityQueue minHeap = new PriorityQueue<Edge>(g.edges().size());
        for (Edge e:g.edges() ) {
            minHeap.add(e);
        }
        System.out.println(minHeap);

        //Kruskal stuff

        //canvas stuff
        //Canvas.paint(g);

    }
}
