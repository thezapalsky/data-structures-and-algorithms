package p3;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set vs;
    private Set es;

    // constructor
    public Graph() {
        this.vs = new HashSet();
        this.es = new HashSet();
    }

    // Adds an edge to the graph between the
    // vertex (ux,uy) y (vx,vy) with weight 'w'.
    public void add(int ux, int uy, int vx, int vy, int w) {

        Vertex o = new Vertex(ux, uy);
        if(!vs.contains(o))
            vs.add(o);

        Vertex d = new Vertex(vx, vy);
        if(!vs.contains(d))
            vs.add(d);

        Edge e = new Edge(o, d, w);
        if(!es.contains(e))
            es.add(e);
    }
    public Set<Vertex> vertices() {
        return vs;
    }

    public Set<Edge> edges()
    {
        return es;
    }

    @Override
    public String toString() {
        return "{"+//"Graph{" +
                //"vs=" + vs +
                //", es=" + es +
                es+
                '}';
    }
}
