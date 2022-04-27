package p2;

import java.util.HashMap;

public class PRGraphImpl implements PRGraph{

    HashMap<String, Node> hm = new HashMap<>();
    // hashmap with nodes (all in current problem)
    // key -> name, value -> node
    // use "put" every time we crate a node

    public void addProcess(String name) {
        if(!hm.containsKey(name)){
            hm.put(name, new Process(name));
        }
    }

    public void addResource(String name) {
        if(!hm.containsKey(name)){
            hm.put(name, new Resource(name));
        }
    }

    public void open(String process, String resource) throws DeadlockException {

        // check for cycle
        for (Node node: hm.values()) {
            for (Node link :node.links.values()) {
                if(node.name.equals(link.name)){
                    throw new DeadlockException();
                }
            }
        }
        hm.get(process).addRelation(resource, hm.get(resource)); // what if we add not existent?
    }

    public void close(String process, String resource) {
        hm.get(process).delRelation(resource);

        // ??
        // Note that when a resource is released (close), if there is a process waiting for that resource, it must be assigned to it.
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for (Node node : hm.values()) {
            str.append( node.toString()+"\n");
        }

        return str.toString();
    }

    private abstract static class Node{

        private final String name;
        private final HashMap <String, Node> links = new HashMap<>(); // could also be a list, but HM is faster

        public Node(String name) {
            this.name = name;
        }

        private void addRelation(String rel_name, Node rel_node) {
            links.put(rel_name, rel_node);
        }

        private void delRelation(String rel_name) {
            links.remove(rel_name);
        }

        @Override
        public String toString() {
            //return name + "->"+links;

            return "Node{" +
                    "name='" + name + '\'' +
                    ", links=" + links +
                    '}';
        }
    }

    private static class Process extends Node{

        public Process(String name) {
            super(name);
        }
    }

    private static class Resource extends Node{

        public Resource(String name) {
            super(name);
        }
    }
}
