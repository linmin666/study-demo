package com.linfafa.iterator;

public class Test {
    public static void main(String[] args) throws Exception {
        Book book1 = new Book("《了不起的盖茨比》");
        Book book2 = new Book("《下沉年代》");
        Book book3 = new Book("《社会心理学》");
        Book book4 = new Book("《面纱》");
        BookShelf bookShelf = new BookShelf(10);
        bookShelf.appendBook(book1);
        bookShelf.appendBook(book2);
        bookShelf.appendBook(book3);
        bookShelf.appendBook(book4);

        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()){
            Book book = (Book)iterator.next();
            System.out.println("BookName: "+book.getName());
        }
    }
}
