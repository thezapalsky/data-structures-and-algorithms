package p1;

import javax.sound.midi.SysexMessage;
import java.util.Iterator;
import java.lang.Math;

//import java.util.Map;

public class HashMap<K,V> implements Map<K,V> {

    private int size = 0; // number of all elements currently stored in the HM
    private int capacity; // number of all buckets
    private float loadFactor; //alpha
    private Array<Node> buckets;

    // default constructor
    public HashMap()
    {
        this(16, 0.75f);
    }

    // constructor with cap and lf
    public HashMap(int capacity, float loadFactor)
    {
        this.capacity = capacity;
        this.loadFactor = loadFactor;

        this.buckets = new Array(capacity);

    }

    public void put(K key, V value){

        // check if we should expand the buckets
        if(size > (capacity * loadFactor) ){
            //System.out.println("Expanding cap, old cap: " + capacity);
            expandCap();
            System.out.println("new cap: " + capacity);
        }

        Node newNode = new Node(key, value);
        int index = newNode.hash%buckets.capacity(); // can be optimized using hashCode & (buckets.length - 1)

        Node bucket = buckets.get(index);

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
        size++;
    }

    private void expandCap(){

        // calculate new_cap
        //int new_cap = (int) (Math.nextUp(loadFactor * size)  + 1 ); // +1 because of the float -> int parsing
        //int new_cap = capacity*2; //easier like this, and more efficient i think
        int new_cap = Math.max(size + 1 , capacity*2);

        //create new buckets array with new_cap
        //Array new_buckets = new Array(new_cap);

        HashMap NewHashMap = new HashMap(new_cap, loadFactor);

        // copy elements to the HM
        for(K key: this){
            if(key!=null){
                NewHashMap.put(key, get(key));
            }
        }

        this.capacity = NewHashMap.capacity;
        this.buckets = NewHashMap.buckets;
    }

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
                    System.out.println("del last or mid"); //ok
                    prev.next = node.next;
                }
                else{ //if it's the first element of the bucket
                    System.out.println("del first");
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

    public boolean contains(K key){

        //Node node = buckets.get( Math.abs( key.hashCode() )%buckets.capacity() );
        //return node != null;

        for(K key_contained: this){
            if(key_contained==key)
                return true;
        }
        return false;

    }

    public int size(){
        // # of elements contained (size) or # of buckets (capacity)?
        return size;
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public void clear(){
        //del everything?

        size = 0;
        capacity = 16;
        buckets.clear();
        buckets = new Array(capacity);

    }

    @Override
    public String toString() {
        return "HashMap{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", loadFactor=" + loadFactor +
                ", buckets=" + buckets.toString() +
                '}';
    }

    @Override public Iterator<K> iterator()
    {
        return new CIterator();
    }

    private class CIterator implements Iterator<K>
    {
        int indexPos = 0;
        Node lastNode = null;

        @Override
        public boolean hasNext() {
            if(capacity >= indexPos + 1  )
                return true;
            return false;
        }

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

//    // ?? todo
//    @SuppressWarnings("unchecked")
//    private static Node<K,V>[] new Array(int length)
//    {
//
//        return (Node<K,V>[]) new Node[length];
//    }

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

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }



}
