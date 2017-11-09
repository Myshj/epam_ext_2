package util.repositories;

import util.collections.MyVector;

/**
 * Generic repository for objects.
 * @param <T>
 */
public class Repository<T> {
    /**
     * Collection to store objects in.
     */
    private MyVector<T> data = new MyVector<>();

    /**
     * Adds new element to repository.
     * @param element Element to add.
     */
    public void add(T element) {
        data.pushUnique(element);
    }

    /**
     * Removes element from repository.
     * @param element Element to remove
     */
    public void remove(T element) {
        data.removeUnordered(data.firstIndexOf(element));
    }

    /**
     * Empties repository.
     */
    public void removeAll() {
        data.clear();
    }

    /**
     * Returns all elements managed by repository.
     * @return All elements managed by repository.
     */
    public MyVector<T> getAll() {
        return new MyVector<>(data);
    }

    /**
     * Returns all elements that satisfy specification.
     * @param specification Specification to satisfy.
     * @return All elements that satisfy specification.
     */
    public MyVector<T> filter(Specification<T> specification) {
        MyVector<T> ret = new MyVector<>();
        for (int i = 0; i < data.getSize(); i++) {
            T current = data.get(i);
            if (specification.check(current)) {
                ret.push(current);
            }
        }
        return ret;
    }
}
