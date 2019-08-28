import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first, last;
    private int n;

    private class Node<Item>{
        private Node<Item> prev;
        private Item item;
        private Node<Item> next;

        public Node(Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return n;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null)
            throw new IllegalArgumentException("item is null");

        if(isEmpty()){
            first = new Node<Item>(item);
            last = first;
        } else{
            Node<Item> tmp = first;
            first = new Node<Item>(item);
            first.next = tmp;
            tmp.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item){
        /* let tmp point to last node,
         * allocate new Node last,
         * let last.item be item,
         * if deque is empty, let first = last,
         * else, let old link to last,
         * one element added n++ */

        if(item == null)
            throw new IllegalArgumentException("item is null");

        Node<Item> tmp = last;
        last = new Node<Item>(item);

        if(isEmpty()){
            first = last;
        } else{
            tmp.next = last;
            last.prev = tmp;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty())
            throw new NoSuchElementException("Deque underflow");

        Item drop = first.item;
        first = first.next;

        if(first != null)
            first.prev = null;
        else{
            last = null;
        }
        n--;
        return drop;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty())
            throw new NoSuchElementException("Deque underflow");

        Item drop = last.item;
        last = last.prev;

        if(last != null) {
            last.next = null;
        } else{
            first = null;
        }
        n--;
        return drop;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Node<Item> current = first;

        public boolean hasNext(){
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
            System.out.println("test");
    }

}
