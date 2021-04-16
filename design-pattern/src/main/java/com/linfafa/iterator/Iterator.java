package com.linfafa.iterator;

/**
 * @param <E> the type of elements returned by this iterator.
 * @author min.lin
 * @since 1.0
 */
public interface Iterator<E> {
    /**
     * Returns {@code ture} if the iteration has more element.
     * (In other words, returns {@code true} if {@link #next()} would
     * return an element rather than throwing an exception.)
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException if the iteration has no more elements.
     */
    E next();

}
