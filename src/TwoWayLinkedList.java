import java.util.Iterator;
import java.util.ListIterator;

class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size;

    /** Create a default list */
    public TwoWayLinkedList() {
    }

    /** Create a list from an array of objects */
    public TwoWayLinkedList(E[] objects) {
        for (E e : objects) {
            add(e);
        }
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    /** Clear the list */
    public void clear() {
        head = tail = null;
    }

    /** Return true if this list contains the element o */
    public boolean contains(Object e) {
        Node<E> current = head;
        while (current != null) {
            E element = current.element;
            if (element.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
        if (index < 0 || index > size - 1){
            return null;
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.element;
    }

    /**
     * Return the index of the head matching element in this list. Return -1 if
     * no match.
     */
    public int indexOf(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                return i;
            current = current.next;
        }

        return -1;
    }

    /**
     * Return the index of the last matching element in this list Return -1 if
     * no match.
     */
    public int lastIndexOf(Object e) {
        int currentIndex = 0;
        int matchedIndex = -1;
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e)) {
                matchedIndex = currentIndex;
            }
            currentIndex++;
            current = current.next;
        }
        return matchedIndex;
    }

    /**
     * Replace the element at the specified position in this list with the
     * specified element.
     */
    public E set(int index, E e) {
        if (index < 0 || index >= size) return null; // Out of range
        Node<E> newNode = new Node<>(e);
        Node<E> current = head;
        Node<E> previous = head;

        for (int i = 0; i < index; i++) {
            previous = current;
            current = previous.next;
        }

        E oldValue = current.element;
        previous.next = newNode;
        newNode.next = current.next;
        return oldValue;
    }

    private class LinkedListIterator implements java.util.ListIterator<E> {
        private Node<E> current = head; // Current index

        public LinkedListIterator() {
        }

        public LinkedListIterator(int index) {
            int i = 0;
            Node<E> current = head;
            while (current != null) {
                if (i == index) {
                    this.current = current;
                    break;
                }
                current = current.next;
                i++;
            }
        }

        public void setLast() {
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            System.out.println("Implementation left as an exercise");
        }

        @Override
        public void add(E e) {
            Node<E> newNode = new Node<>(e); // Create a new node for e

            if (tail == null) {
                head = tail = newNode; // The only node in list
            }else {
                newNode.previous = tail;
                tail.next = newNode; // Link the new node with the last node
                tail = newNode; // tail points to the last node
            }

            size++; // Increase size
        }

        @Override
        public boolean hasPrevious() {
            return current != null;
        }

        @Override
        public int nextIndex() {
            return indexOf(current) + 1;
        }

        @Override
        public E previous() {
            E e = current.element;
            current = current.previous;
            return e;
        }

        @Override
        public int previousIndex() {
            return indexOf(current) - 1;
        }

        @Override
        public void set(E e) {
            current.element = e;
        }
    }

    private class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E o) {
            element = o;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index);
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

// BEGIN REVEL SUBMISSION
    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        // Write your code here
        Node<E> newNode = new Node<>(e); // Create a new node
        if (size > 0) {
            head.previous = newNode;
        }

        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) {// The new node is the only node in list
            tail = head;
        }
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        // Write your code here
        Node<E> newNode = new Node<>(e); // Create a new node for e

        if (tail == null){
            head = tail = newNode; // The only node in list
        } else {
            newNode.previous = tail;
            tail.next = newNode; // Link the new node with the last node
            tail = newNode; // point tail now to last node
        }

        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     */
    public void add(int index, E e) {
        // Write your code here
        if (index == 0) {
            addFirst(e); // Insert first
        } else if (index >= size) {
            addLast(e); // Insert last
        } else {
            // Insert in the middle
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            Node<E> previous = current.previous;
            current.next = new Node<>(e);
            (current.next).previous = previous;
            (current.next).next = temp;
            size++;
        }
    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() {
        // Write your code here
        if (size == 0) {
            return null; // Nothing to delete
        } else {
            Node<E> temp = head; // Keep first node temporarily
            head = head.next; // Move head to point to next node
            size--; // Reduce size by 1
            if (head == null) {
                tail = null; // List becomes empty
            } else {
                head.previous = null;
            }
            return temp.element; // Return the deleted element
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
        // Write your code here
        if (size == 0) {
            return null; // Nothing to remove
        } else if (size == 1) {
            // Only one element in the list
            Node<E> temp = head;
            head = tail = null; // list becomes empty
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;

            for (int i = 0; i < size - 2; i++)
                current = current.next;

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     */
    public E remove(int index) {
        // Write your code here
        if (index < 0 || index >= size) {
            return null; // Out of range
        } else if (index == 0) {
            return removeFirst(); // Remove first
        } else if (index == size - 1) {
            return removeLast(); // Remove last
        } else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }
}