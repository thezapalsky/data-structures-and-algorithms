package p3;

import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String pth =  "input.txt";
        //String pth =  "input2.txt";
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
        // for every edge create an one element graph
        // not graph, cause it does not implement unions?
        // start to union the graphs

        Graph t = new Graph();

        HashMap<Integer,Set> forest = new HashMap<>();
        for (Vertex v : g.vertices()){
            Set tree = new HashSet();
            tree.add(v);
            forest.put(tree.hashCode(),tree);
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
            Set set_u = Find(forest, u);
            Set set_v = Find(forest, v);

            if(set_u!=set_v){
                //System.out.println("Union");
                Union(set_u,set_v);
            }
            System.out.println("u:"+set_u);
            //System.out.println("v:"+set_v);
        }

        //there should be only one tree in the forest left
        System.out.println("\n"+forest);

        // add MST to the t
        return t;
    }

    //returns a tree (set) if it's containing vertex V
    static Set Find(HashMap<Integer, Set> forest, Vertex V){
        for (Set tree : forest.values() ) {
                if (tree.contains(V)) {
                    return tree;
                }
            }
        return null;
    }

    //returns union of two sets, removes the second set (?)
    static void Union(Set set1, Set set2){
        set1.addAll(set2);
        set2.clear();
        //return set1;
    }
}
