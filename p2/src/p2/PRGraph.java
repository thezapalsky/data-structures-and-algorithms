package p2;

// Resource allocation graph
public interface PRGraph {

    // Adds a process to the graph
    void addProcess(String name);

    // Adds a resource to the graph
    void addResource(String name);

    // Requests a resource
    void open(String process, String resource)
        throws DeadlockException;

    // Frees a resource
    void close(String process, String resource);
}
