package p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Custom Array class that should behave just like the Java's ArrayList
 * @param <E> Generic element
 */
public class Array<E> implements Iterable<E>
{
    private int size = 0;
    private int capacity;
    private E[] tab;  //tab of generic type E


    /**
     * Constructor without arguments
     */
    public Array(){
        this.capacity = 8; //default capacity set to 8
        this.tab = (E[]) new Object[capacity];
    }

    /**
     * Constructor that uses capacity to create the Array instance
     * @param capacity int
     */
    public Array(int capacity){
        this.capacity = capacity;
        this.tab = (E[]) new Object[capacity];
    }

    /**
     * Method for adding a new element at the end of the array
     * @param element Generic element
     */
    public void add(E element){

        if(size>=capacity){
            AddCapacity();
        }

        tab[size] = element;
        size++;
    }

    /**
     * Private method for adding more capacity to the array
     */
    private void AddCapacity() {
        // when we are close to the limit -> copy the array and make it bigger;

        if(capacity<1){
            capacity = 2;
        }
        else{
            capacity *= 2;
        }

        E[] new_tab = (E[]) new Object[capacity];

        for(int i = 0; i < size; i++) {
            new_tab[i] = tab[i];
        }
        tab = new_tab;

    }

    /**
     * Method that returns an element at specific index
     * @param index int
     * @return Generic element or null
     */
    public E get (int index){
        //

        //exception?
        if(index<capacity) {
            return tab[index];
        }
        return null;
    }

    /**
     * A method that changes element at specified index
     * @param index int
     * @param element Generic element or null
     */
    public void set(int index, E element) {

        // exception?
        if(index<capacity){
            tab[index] = element;
            size++;
        }
    }

    /**
     * Method that returns the actual size of the array
     * @return int size of the array
     */
    public int size(){ return size; }

    /**
     * Method that returns the actual capacity of the array
     * @return int capacity of the array
     */
    public int capacity(){
        return capacity;
    }

    /**
     * Method for deleting all elements in an Array
     */
    public void clear(){
        // clears all elements of the array

        size = 0; // if we do not care about what's inside
        capacity = 8;
        tab = (E[]) new Object[capacity];
        // but what about the freeing the space? -> just rewrite the object, java will clear the memory

        //should we put nulls on every bucket when we clear it?
    }

    /**
     * {@inheritDoc}
     */
    @Override public String toString(){
        // override the default toString method

        StringBuilder str = new StringBuilder();
        str.append("[");
        boolean first = true;

        for (int i = 0; i < capacity; i++) {
            if(tab[i]!=null){
                if(!first) {
                    str.append(", \n");
                }
                str.append(tab[i].toString());
                first=false;
            }
        }
        str.append("]");
        return str.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override public Iterator<E> iterator()
    {
        return new CIterator();
    }

    /**
     * Custom iterator for the Array class
     */
    private class CIterator implements Iterator<E>
    {

        int indexPos = 0;

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
        public E next() {
            E value = tab[indexPos];
            indexPos++;
            return value;
        }

    }



}
