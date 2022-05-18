package p3;

public class Edge implements Comparable<Edge>{

    private Vertex origin;
    private Vertex destination;
    private int weight;

    public Edge(Vertex origin, Vertex destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    // Returns the origin vertex
    public Vertex getU() {
        return origin;
    }

    // Returns the destination vertex
    public Vertex getV() {
        return destination;
    }

    // Returns the edge weight
    public int getW() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }

    //The Edge class should be comparable taking into account only the weight
    // to allow to add the edges to the MinHeap and get them from lowest to highest weight.
    //todo
    @Override
    public int compareTo(Edge o) {
        return 0;
    }

}
