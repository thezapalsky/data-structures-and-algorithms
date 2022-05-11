package p3;

public class Edge implements Comparable<Edge>{

    // Returns the origin vertex
    public Vertex getU() {
        //todo
        return;
    }

    // Returns the destination vertex
    public Vertex getV() {
        //todo
        return;
    }

    // Returns the edge weight
    public int getW() {
        //todo
        return;
    }

    //The Edge class should be comparable taking into account only the weight to allow to add the edges to the MinHeap and get them from lowest to highest weight.
    //?? //todo
    @Override
    public int compareTo(Edge o) {
        return 0;
    }

}
