// BookManager.aidl
package com.zsj.learndemo;
import com.zsj.learndemo.Book;

interface BookManager {
    List<Book> getBooks();
    Book addBook(in Book book);
    Book addBookOut(out Book book);
    Book addBookInOut(inout Book book);
}
