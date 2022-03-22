package p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class Array<E> implements Iterable<E>
{
    /// should work as Java's ArrayList

    private int size = 0;
    private int capacity;
    private E[] tab;  //tab of generic type E


    public Array(){
        // constructor with 0 arguments

        this.capacity = 8; //default capacity set to 8
        this.tab = (E[]) new Object[capacity];
    }

    public Array(int capacity){
        // constructor with capacity argument

        this.capacity = capacity;
        this.tab = (E[]) new Object[capacity];
    }

    public void add(E element){
        //method for adding a new element at the end of the array

        if(size>=capacity){
            AddCapacity();
        }

        tab[size] = element;
        size++;


    }

    private void AddCapacity() {
        //when we are close to the limit -> copy the array and make it bigger;

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

    public E get (int index){
        //returns element at specific index

        //exception?
        if(index<capacity) {
            return tab[index];
        }
        return null;
    }

    public void set(int index, E element) {
        // changes element at specified index ? not sure how it should work

        // exception?
        if(index<capacity){
            tab[index] = element;
            size++;
        }
    }

    public int size(){
        // returns the actual size of the array
        return size;
    }

    public int capacity(){
        return capacity;
    }

    public void clear(){
        // clears all elements of the array

        size = 0; // if we do not care about what's inside
        capacity = 8;
        E[] tab = (E[]) new Object[capacity];
        // but what about the freeing the space? -> just rewrite the object, java will clear the memory

        //should we put nulls on every bucket when we clear it?
    }

    @Override public String toString(){
        // override the default toString method

        StringBuilder str = new StringBuilder();
        str.append("[\n");

        for (int i = 0; i < capacity; i++) {
            if(tab[i]!=null){
                str.append( tab[i].toString() );
                str.append( "\n" );
            }
        }
        str.append("]");
        return str.toString();
    }

    @Override public Iterator<E> iterator()
    {
        return new CIterator();
    }

    private class CIterator implements Iterator<E>
    {

        int indexPos = 0;

        @Override
        public boolean hasNext() {

            if(capacity >= indexPos + 1  )
                return true;
            return false;
        }

        @Override
        public E next() {
            E value = tab[indexPos];
            indexPos++;
            return value;
        }

    }



}
