package p3;

import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //String pth =  "input.txt";
        String pth =  "input2.txt";
        Reader rdr = new Reader(pth);
        Graph g = rdr.readFile();
        //System.out.println(g);

        //Kruskal stuff
        Graph t = MSTKruskal(g);

        //canvas stuff
        //Canvas.paint(g);
        //Canvas.paint(g,t);

    }

    static Graph MSTKruskal(Graph g){
        // ?? //todo
        // for every edge create a one element graph
        // not graph, cause it does not implement unions?
        // start to union the graphs

        Graph t = new Graph();

        HashMap<String,Set> forest = new HashMap<>();
        for (Vertex v : g.vertices()){
            Set tree = new HashSet();
            tree.add(v);
            forest.put(v.toString(),tree);
        }

        // instead of implementing minHeap class from scratch I'm using PQ from Java's API
        PriorityQueue<Edge> edges = new PriorityQueue<>(
                g.edges().size(),
                Edge::compareTo);
        for (Edge e:g.edges() ) {
            edges.add(e);
        }
        System.out.println(forest);

        //As long as the set is not empty
        while(edges.peek()!=null) {

            //The edge with the minimum weight edge is eliminated from the set
            Edge curr = edges.poll();
            System.out.print(curr);
            Vertex u = curr.getU();
            Vertex v = curr.getV();

            //If the chosen edge connects two different trees it is added to the forest,
            //combining the two trees into one.

            // 1.
            for (Set tree : forest.values()) {
                if (tree.contains(u) ) {

                    //System.out.println("dodaje");
                    tree.add(v);
                }
            }
        }

//            if(forest.get(u.toString())!=forest.get(v.toString())){
//                //System.out.print("dupa");
//                for (Set tree:forest.values()){
//                    if(tree.contains(u)){
//                        tree.add(v); // union /AAAAA //todo
//                        System.out.println("dodaje");
//                        forest.remove(v.toString()); // remove 1-element v set
//                        t.add(u.x,u.y, v.x, v.y, curr.getW());
//                    }
//                }
//            }
//        }
//        System.out.println("\n"+forest);

            // nwm :(
//            for (Set tree:forest.values()) {
//                if(tree.contains(u) && !tree.contains(v)){
//                    tree.add(v);
//                    forest.remove(v.toString());
//                    // add to the t?
//                    t.add(u.x,u.y, v.x, v.y, curr.getW());
//                }
//            }
        return t;//
    }
}
