package util.repositories;

/**
 * Generic specification of ob object.
 * @param <T>
 */
public interface Specification<T> {

    /**
     * Check if given object satisfies the specification.
     * @param element object to check.
     * @return true if given object satisfies the specification.
     */
    boolean check(T element);
}
