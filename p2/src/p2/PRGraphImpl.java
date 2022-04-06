package p2;

import java.util.HashMap;

public class PRGraphImpl implements PRGraph{

    HashMap<String, Node> hm;
    // hashmap with nodes (all in current problem)
    // key -> name, value -> node
    // use "put" every time we crate a node

    public void addProcess(String name) {

        // put if does not already exists inside the hm
        //hm.put(name, new Node)
    }

    public void addResource(String name) {
        // todo
    }

    public void open(String process, String resource) throws DeadlockException {
        // todo
    }

    public void close(String process, String resource) {
        // todo
    }

    private class Node{

        private String name;
        private HashMap <String, Node> links = new HashMap<>(); // could also be a list, but HM is faster

        public Node(String name) {
            this.name = name;
        }

        private void addRelation(String rel_name, Node rel_node) {
            links.put(rel_name, rel_node);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", links=" + links +
                    '}';
        }
    }

    private class Process extends Node{

        public Process(String name) {
            super(name);
        }
    }

    private class Resource extends Node{

        public Resource(String name) {
            super(name);
        }
    }
}
