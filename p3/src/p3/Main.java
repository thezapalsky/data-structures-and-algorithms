package p3;

import java.io.FileNotFoundException;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //String pth =  "input.txt";
        String pth =  "input2.txt";
        Reader rdr = new Reader(pth);
        Graph g = rdr.readFile();
        System.out.println(g);

        //minheap stuff
        // instead of implementing minHeap class from scratch I'm using PQ from Java's API
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(
                g.edges().size(),
                Edge::compareTo);
        //System.out.println(minHeap.comparator());

        for (Edge e:g.edges() ) {
            //System.out.print(e);
            minHeap.add(e);
        }
        System.out.print("\n");
        while(minHeap.peek()!=null){
            System.out.print(minHeap.poll());
        }


        //Kruskal stuff
        //todo

        //canvas stuff
        //Canvas.paint(g);

    }
}
