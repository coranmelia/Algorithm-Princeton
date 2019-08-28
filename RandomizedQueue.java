import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] rQueue;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue(){
        rQueue = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return n;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null)
            throw new IllegalArgumentException("item is null");

       if(n == rQueue.length)
           resize(rQueue.length * 2);

       rQueue[n] = item;
       n++;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty())
            throw new NoSuchElementException();

        int index = StdRandom.uniform(n);
        Item item = rQueue[index];
        rQueue[index] = rQueue[n-1];
        rQueue[n-1] = null; //avoid loitering
        n--;

        if(n > 0 && n == rQueue.length / 4)
            resize(rQueue.length / 4);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty())
            throw new NoSuchElementException();

        int i = StdRandom.uniform(n);

        return rQueue[i];
    }

    private void resize(int capacity){
        Item[] tmp = (Item[]) new Object[capacity];
        for(int i = 0; i < n; i++){
            tmp[i] = rQueue[i];
        }
        rQueue = tmp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>{
        private int current;
        private final Item[] copy;

        public RandomizedQueueIterator(){
            copy = (Item[]) new Object[n];
            for(int i = 0; i < n; i++){
                copy[i] = rQueue[i];
            }
            // shuffle using Fisher-Yates
            for(int i = n-1; i > 0; i--){
                int j = (int)(Math.random() * (i+1));
                Item tmp = copy[i];
                copy[i] = copy[j];
                copy[j] = tmp;
            }
            current = n-1;
        }
        public boolean hasNext(){
            return current >= 0;
        }
        public Item next(){
            if(!hasNext())
                throw new NoSuchElementException();
            Item item = copy[current];
            current--;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        System.out.println("test");
    }

}
