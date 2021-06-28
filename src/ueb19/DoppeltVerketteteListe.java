package ueb19;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoppeltVerketteteListe<E> implements List<E> {
    class Node {
        E item;
        Node previous;
        Node next;

        public Node(E item) {
            this.item = item;
        }
    }

    Node head = null;
    Node tail = null;

    /**
     * returns the size of DoublyLinkedList
     */
    @Override
    public int size() {
        int counter = 0;
        Node iterator = head;
        while (iterator != null) {
            counter++;
            iterator = iterator.next;
        }
        return counter;
    }

    /**
     * Returns true if LinkedList is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        if (head != null) {
            return false;
        }
        return true;
    }

    /**
     * Controls if Linked list has that object, if yes , return true, if no, return
     * false
     */
    @Override
    public boolean contains(Object o) {
        Node iterator = head;
        while (iterator != null) {

            if (iterator.item.equals(o)) {
                return true;
            }
            iterator = iterator.next;
        }
        return false;
    }

    /**
     * Returns an array of every node.item
     */
    @Override
    public Object[] toArray() {
        Object[] returnArray = new Object[this.size()];
        Node iterator = head;
        int counter = 0;
        while (iterator != null) {
            returnArray[counter] = iterator.item;
            iterator = iterator.next;
            counter++;
        }
        return returnArray;
    }

    /**
     * Takes and array and fill that array with the items from 
     * the DoublyLinkedList and returns it back
     */
    @Override
    public <T> T[] toArray(T[] a) {
        int size = this.size();

        if (a.length == size) {
            Node iterator = head;

            for (int i = 0; i < a.length; i++) {
                a[i] = (T) iterator.item;
                iterator = iterator.next;
            }
            return a;
        } else if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
            Node iterator = head;
            for (int i = 0; i < size; i++) {
                a[i] = (T) iterator.item;
                iterator = iterator.next;
            }
            return a;

        } else {
            Node iterator = head;
            for (int i = 0; i < size; i++) {
                a[i] = (T) iterator.item;
                iterator = iterator.next;
            }
            for (int j = size; j < a.length; j++) {
                a[j] = null;
            }
            return a;
        }
    }

    public void printList() {
        Node iterator = head;
        System.out.println("New version of the list is ");
        while (iterator != null) {
            System.out.print(iterator.item + "->");
            iterator = iterator.next;
        }
    }

    /**
     * Add new node to linked list by given value returns true, if successful
     */
    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Node(e);
            tail = head;
            head.previous = null;
            tail.next = null;
            return true;
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        } else {
            if (head.item.equals(o)) {
                if (head.next != null) {
                    head.next.previous = null;
                    head = head.next;
                    return true;
                } else {
                    head = null;
                    tail = null;
                    return true;
                }

            }
            if (head == tail && head.item.equals(o)) {
                head = null;
                tail = null;
                return true;
            }
            Node iterator = head;
            while (iterator != null) {
                if (iterator.item.equals(o)) {
                    iterator.previous.next = iterator.next;
                    if (iterator == tail) {
                        tail = tail.previous;
                        return true;
                    }
                    iterator.next.previous = iterator.previous;

                    return true;
                }
                iterator = iterator.next;
            }
            return false;
        }
    }

    /**
     * Add all items to the list
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {

        c.forEach(nodeItem -> {
            Node newNode = new Node(nodeItem);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.previous = tail;
                tail = tail.next;
            }

        });
        return true;
    }

    /**
     * 
     * clears the list
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        // TODO Auto-generated method stub

    }

    /**
     * returns the value at given index
     */
    @Override
    public E get(int index) {
        if (index >= this.size() || index < 0) {
            throw new IllegalArgumentException("The size of the List is smaller than the given index");
        }
        int counter = 0;
        Node iterator = head;
        while (counter != index) {
            iterator = iterator.next;
            counter++;
        }
        return iterator.item;
    }

    /**
     * set the value of given index to given parameter
     */
    @Override
    public E set(int index, E element) {
        if (index >= this.size() || index < 0) {
            throw new IllegalArgumentException("The size of the List is smaller than the given index");
        }
        int counter = 0;
        Node iterator = head;
        while (counter != index) {
            iterator = iterator.next;
            counter++;
        }
        iterator.item = element;
        return iterator.item;
    }

    /**
     * Insert a new node to given index
     */
    @Override
    public void add(int index, E element) {
        if (index >= this.size() || index < 0) {
            throw new IllegalArgumentException("The size of the List is smaller than the given index");
        }
        if (index == 0) {
            Node newNode = new Node(element);
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } else {
            int counter = 0;
            Node iterator = head;
            while (counter < index) {
                counter++;
                iterator = iterator.next;
            }
            Node newNode = new Node(element);
            iterator.previous.next = newNode;
            newNode.previous = iterator.previous;
            newNode.next = iterator;
            iterator.previous = newNode;
        }

    }

    /**
     * deletes node from given index and returns value of that node
     */
    @Override
    public E remove(int index) {
        if (index >= this.size()) {
            throw new IllegalArgumentException("The size of the List is smaller than the given index");
        }
        if (index == 0) {
            Node temporary = head;
            head = head.next;
            return temporary.item;
        }
        int counter = 0;
        Node iterator = head;
        while (counter != index) {
            counter++;
            iterator = iterator.next;
        }
        if (iterator == tail) {
            System.out.println("Tail baby");
            tail = tail.previous;
            tail.next = null;
            return iterator.item;
        }
        iterator.previous.next = iterator.next;
        iterator.next.previous = iterator.previous;
        return iterator.item;

    }

    /**
     * returns index of an item, if the item is not in the list, returns -1
     */
    @Override
    public int indexOf(Object o) {
        Node iterator = head;
        int counter = 0;
        while (iterator != null) {
            if (iterator.item.equals(o)) {
                return counter;
            }
            iterator = iterator.next;
            counter++;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Invalid operation for Double Linked list.");

    }

}
