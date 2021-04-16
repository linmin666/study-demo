package com.linfafa.iterator;

import java.util.ArrayList;
import java.util.List;

public class BookShelf implements Aggregate {
    /**
     * All books on the bookshelf.
     */
    private List<Book> books;
    /**
     * The maximum number of books that can be placed on the bookshelf.
     */
    private int capacity;

    private int last = 0;

    /**
     * @param index where book be placed.
     * @return the book name in which index
     */
    public Book bookAt(int index) {
        return books.get(index);
    }

    public void appendBook(Book book) throws Exception {
        if (last < capacity) {
            books.set(last++,book);
        } else throw new Exception("装不下了");
    }

    public BookShelf(int capacity) {
        this.capacity=capacity;
        this.books =new ArrayList<Book>();
    }

    public int getLength() {
        return last;
    }

    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
