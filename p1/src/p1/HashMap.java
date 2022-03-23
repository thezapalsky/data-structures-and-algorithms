package p1;

import javax.sound.midi.SysexMessage;
import java.util.Iterator;
import java.lang.Math;

//import java.util.Map;

/**
 * HashMap class to store key-value pairs, duplicated keys are not allowed
 * @param <K> Generic key
 * @param <V> Generic value
 */
public class HashMap<K,V> implements Map<K,V> {

    private int size = 0; // number of all elements currently stored in the HM
    private int capacity; // number of all buckets
    private float loadFactor; //alpha
    private Array<Node> buckets;

    /**
     * Default constructor
     */
    public HashMap()
    {
        this(16, 0.75f);
    }

    /**
     * Overloaded constructor with capacity and loadFactor values
     * @param capacity number of 'buckets' in the inner Array
     * @param loadFactor alpha parameter, based on that the HashMap is automatically expanded
     */
    public HashMap(int capacity, float loadFactor)
    {
        this.capacity = capacity;
        this.loadFactor = loadFactor;

        this.buckets = new Array(capacity);

    }

    /**
     * Method for putting a new key-value pair to the HashMap
     * @param key generic key
     * @param value generic value
     */
    public void put(K key, V value){

        // check if we should expand the buckets
        if(size > (capacity * loadFactor) ){
            //System.out.println("Expanding cap, old cap: " + capacity);
            expandCap();
            System.out.println("new cap: " + capacity);
        }

        // I created this method for to code to be more clear as the snippet is being used
        // here and in expandCap() also
        addPair(key, value, buckets);

        size++;
    }

    /**
     * Method for adding a Node to existing buckets Array
     * the same code is being used in put() and expandCap() with different 'buckets' param
     * @param key Generic key
     * @param value Generic value
     * @param buckets Array of buckets
     */
    private void addPair(K key, V value, Array buckets){
        Node newNode = new Node(key, value);
        int index = newNode.hash%buckets.capacity(); // can be optimized using hashCode & (buckets.length - 1)

        Node bucket = (Node) buckets.get(index);

        if(bucket==null){
            buckets.set(index, newNode);
        }
        else{
            Node lastBucket = bucket; //to save the parent

            while( bucket != null ){
                lastBucket = bucket;
                if( bucket.key.equals(key) ){
                    bucket.value = value; // rewrite existing value with the same key ->
                    // because duplicated keys are not allowed?
                    return;
                }
                bucket = bucket.next;
            }
            // add to the parent
            lastBucket.next = newNode;
        }
    }

    /**
     * Private method accessed by the set method when the capacity needs to be enlarged
     */
    private void expandCap(){

        // calculate new_cap
        //int new_cap = (int) (Math.nextUp(loadFactor * size)  + 1 ); // +1 because of the float -> int parsing
        //int new_cap = capacity*2; //easier like this, and more efficient i think
        int new_cap = Math.max(size + 1 , capacity*2);

        // create temp hashmap and copy the elements from the previous one, but with recalculated indexes
        //HashMap NewHashMap = new HashMap(new_cap, loadFactor);
        Array NewBuckets = new Array(new_cap); // new approach (!)

        for(K key: this){
            if(key!=null){
                //NewHashMap.put(key, get(key)); // very computer-time consuming, do it with Array only
                addPair(key, get(key), NewBuckets); // new approach (!)
            }
        }

        // overwrite parameters of 'this' HM
        this.capacity = new_cap;
        this.buckets = NewBuckets;
    }

    /**
     * Method for accessing a value from an element with specified key
     * @param key Generic key
     * @return Generic value or null in case the key is not in the HashMap
     */
    public V get(K key){

        Node node = buckets.get( Math.abs( key.hashCode() )%buckets.capacity() ); // !

        while(node!=null){
            if(node.key.equals(key)){
                return (V) node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Method for removing the element at specified key
     * @param key Generic key
     * @return Value of the removed element or null in case it's not contained in the HashMap
     */
    public V remove(K key){

        int idx = Math.abs( key.hashCode() )%buckets.capacity();
        Node node = buckets.get(idx);
        Node prev = null;
        System.out.println(node.toString());

        while(node!=null){
            //System.out.println("to remove: " + node.toString());
            if(node.key.equals(key)){

                V removed_val = (V) node.value;

                if( prev!=null && prev.hasNext() ){ // case when the node is not first
                    //System.out.println("del last or mid"); //ok
                    prev.next = node.next;
                }
                else{ //if it's the first element of the bucket
                    //System.out.println("del first");
                    buckets.set(idx, node.next);
                }
                size--;
                return removed_val;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    /**
     * Method to check if element with specified key exists inside current HashMap
     * @param key Generic key
     * @return Boolean value
     */
    public boolean contains(K key){

        //Node node = buckets.get( Math.abs( key.hashCode() )%buckets.capacity() );
        //return node != null;

        for(K key_contained: this){
            if(key_contained==key)
                return true;
        }
        return false;

    }

    /**
     * Method for accessing the current size of the HashMap (number of elements stored)
     * @return int value
     */
    public int size(){
        // # of elements contained (size)
        return size;
    }

    /**
     * Method for checking if HashMap is empty
     * @return Boolean value
     */
    public boolean isEmpty(){
        return (size==0);
    }

    /**
     * Method for removing every element from the HashMap and setting its default parameters to default
     */
    public void clear(){
        //del everything?

        size = 0;
        capacity = 16;
        buckets.clear();
        buckets = new Array(capacity);
    }

    /**
     * Method for accessing the content of the HashMap in a human-readable format
     * @return String with information about the HashMap
     */
    @Override
    public String toString() {
        return "HashMap{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", loadFactor=" + loadFactor +
                ", buckets=" + buckets.toString() +
                '}';
    }


    /**
     * {@inheritDoc}
     */
    @Override public Iterator<K> iterator()
    {
        return new CIterator();
    }


    /**
     * Custom iterator for the HashMap class
     */
    private class CIterator implements Iterator<K>
    {
        int indexPos = 0;
        Node lastNode = null;


        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            if(capacity >= indexPos + 1  )
                return true;
            return false;
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public K next() {
            Node node;

            if(lastNode!=null){
                node = lastNode.next;
            }
            else{
                node = buckets.get(indexPos);
            }

            if( node != null ){
                lastNode = node;
                return (K) node.key;
            }
            lastNode = null;
            indexPos++;

            return null; //can i somehow code it to not return anything in this case?
        }
    }

//    // ?? i don't think i need that snippet?
//    @SuppressWarnings("unchecked")
//    private static Node<K,V>[] new Array(int length)
//    {
//
//        return (Node<K,V>[]) new Node[length];
//    }

    /**
     * Private Node class for operations on the HashMap,
     * hash absolute value is automatically calculated,
     * next is initialized as null
     * @param <K> Generic key
     * @param <V> Generic Value
     */
    // Node private class
    private static class Node<K,V>
    {
        int hash;
        K key;
        V value;
        Node<K,V> next;

        // Node constructor
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
            this.hash =  Math.abs(key.hashCode());
        }

        public Node<K, V> getNext() {
            return next;
        }

        public boolean hasNext(){
            return getNext() != null;
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "Node[" +
                    //"hash=" + hash +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    "]";
        }
    }



}
