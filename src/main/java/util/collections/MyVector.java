package util.collections;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Dynamic array.
 * @param <T> Type of elements to store.
 */
public class MyVector<T> implements Serializable {
    /**
     * Default initial capacity.
     */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Added to array size when growth requested.
     */
    private static final int GROWTH_DELTA = 16;

    /**
     * Buffer with data.
     */
    private T[] data;

    /**
     * Visible to end user size.
     */
    private int size;

    /**
     * Is array full?
     */
    private boolean full;

    /**
     * Default constructor.
     * Creates array with INITIAL_CAPACITY as capacity.
     */
    public MyVector() {
        data = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Copy constructor.
     * @param other Original array to create copy of.
     */
    public MyVector(MyVector other) {
        size = other.size;
        full = other.full;
        data = (T[]) Arrays.copyOf(other.data, other.getBufferLength());
    }

    /**
     * Parametrized constructor.
     * @param size Desired size of array.
     */
    public MyVector(int size) {
        data = (T[]) new Object[growthLengthFor(size)];
        this.size = size;
    }

    /**
     * Returns element at the specified position.
     * @param index Position of element.
     * @return Element at the specified position.
     */
    public T get(int index) {
        throwExceptionIfNotValid(index);
        return data[index];
    }

    /**
     * Assigns new value to element at the specified position.
     * @param index Position of element.
     * @param element New value.
     */
    public void set(
            int index,
            T element
    ) {
        throwExceptionIfNotValid(index);
        data[index] = element;
    }

    /**
     * Returns last element in the array.
     * @return Last element in the array.
     */
    public T getLast() {
        return get(getLastValidIndex());
    }

    /**
     * Removes element at the specified position.
     * Does not preserves order.
     * @param index Position to remove element from.
     */
    public void removeUnordered(int index) {
        throwExceptionIfNotValid(index);
        set(
                index,
                getLast()
        );
        removeLast();
    }

    /**
     * Removes last element from the array.
     */
    private void removeLast() {
        decrementSize();
        updateFull();
    }

    /**
     * Decrements size of array.
     */
    private void decrementSize() {
        size--;
    }

    /**
     * Throws IndexOutOfBoundsException if given index is not valid.
     * @throws IndexOutOfBoundsException If given index is not valid.
     * @param index Index to check.
     */
    private void throwExceptionIfNotValid(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks if given index is valid.
     * @param index Index to check.
     * @return true if given index is valid.
     */
    public boolean isValidIndex(int index) {
        return index <= getLastValidIndex() || index >= 0;
    }

    /**
     * Returns count of elements in the array.
     * @return Count of elements in the array.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns last valid index for this array.
     * @return Last valid index for this array.
     */
    public int getLastValidIndex() {
        return size - 1;
    }

    /**
     * Appends element to the back of this array.
     * @param element Element to append.
     */
    public void push(T element) {
        growIfFull();
        size++;
        set(
                getLastValidIndex(),
                element
        );
        updateFull();
    }

    /**
     * Checks if this array already stores given element.
     * @param element Element to search for.
     * @return true if this array already stores given element.
     */
    public boolean contains(T element) {
        for (Object e : data) {
            if (e == data) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns index of first occurence of the given element in this array.
     * @param element Element to search for.
     * @return Index of first occurence of the given element in this array if array contains this element. -1 otherwise.
     */
    public int firstIndexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (get(i) == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Pushes element back if the array does not already contain it.
     * @param element Element to push.
     */
    public void pushUnique(T element) {
        if (!contains(element)) {
            push(element);
        }
    }

    /**
     * Empties array.
     */
    public void clear() {
        size = 0;
        updateFull();
    }

    /**
     * Sorts array using given comparator.
     * @param comparator Comparator to sort with.
     */
    public void sort(Comparator<T> comparator) {
        T[] actualData = Arrays.copyOf(data, size);
        Arrays.sort(
                actualData,
                comparator
        );
        System.arraycopy(
                actualData,
                0,
                data,
                0,
                size
        );
    }

    /**
     * Returns sorted copy of this array.
     * @param comparator Comparator to search with.
     * @return Sorted copy of this array.
     */
    public MyVector<T> sorted(Comparator<T> comparator) {
        MyVector<T> ret = new MyVector<>(this);
        ret.sort(comparator);
        return ret;
    }

    /**
     * If array is full then signalizes about this.
     */
    private void updateFull() {
        full = size == getBufferLength();
    }

    /**
     * Removes last element from this array.
     * @return Last element from this array.
     */
    public T pop() {
        T ret = getLast();
        removeLast();
        return ret;
    }

    /**
     * Returns actual buffer size.
     * @return Actual buffer size.
     */
    private int getBufferLength() {
        return data.length;
    }

    /**
     * Resizes array if it is full.
     */
    private void growIfFull() {
        if (isFull()) {
            grow();
        }
    }

    /**
     * Returns buffer able to store this array data plus some delta.
     * @return Buffer able to store this array data plus some delta.
     */
    private T[] generateArrayToGrow() {
        return (T[]) new Object[growthLengthFor(getBufferLength())];
    }

    /**
     * Copies data from this array to the destination buffer.
     * @param destination Buffer to copy to.
     */
    private void transferData(Object[] destination) {
        System.arraycopy(data, 0, destination, 0, getBufferLength());
    }

    /**
     * Makes this array bigger.
     */
    private void grow() {
        T[] newData = generateArrayToGrow();
        transferData(newData);
        data = newData;
        updateFull();
    }

    /**
     * Calculates length of buffer for desired size.
     * @param desiredSize Desired count of elements.
     * @return Length of buffer for desired size.
     */
    private int growthLengthFor(int desiredSize) {
        return desiredSize * 3 / 2 + GROWTH_DELTA;
    }

    /**
     * Checks if this array is full.
     * @return true if this array is full.
     */
    public boolean isFull() {
        return full;
    }

    @Override
    public String toString() {
        return "MyVector{" +
                "data=" + Arrays.toString(Arrays.copyOf(data, size)) +
                ", size=" + size +
                '}';
    }
}
