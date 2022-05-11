package p3;

public class MinHeap <E extends Comparable<E>>{
    private E[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public MinHeap(int capacity)
    {
        array = (E[]) new Comparable[capacity];
    }

    //todo
}
