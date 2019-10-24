package com.zsj.learndemo.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zsj.learndemo.Book;
import com.zsj.learndemo.BookManager;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {
    public final String TAG = "zsjaidl 服务端";
    private List<Book> mBooks = new ArrayList<>();


    private final BookManager.Stub bookManager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            Log.e(TAG, "getBooks: "+mBooks.toString());
            if(mBooks!=null){
                return mBooks;
            }
            return new ArrayList<Book>();
        }

        @Override
        public Book addBook(Book book) throws RemoteException {
            if(mBooks==null){
                mBooks = new ArrayList<>();
            }
            if(book == null){
                book = new Book();
            }

            book.setPrice(1);

            if(!mBooks.contains(book)){
                mBooks.add(book);
            }

            Log.e(TAG, "addBook: "+mBooks.toString());

            return book;
        }

        @Override
        public Book addBookOut(Book book) throws RemoteException {
            if(mBooks==null){
                mBooks = new ArrayList<>();
            }
            if(book == null){
                book = new Book();
            }

            book.setPrice(1);

            if(!mBooks.contains(book)){
                mBooks.add(book);
            }

            Log.e(TAG, "addBook: "+mBooks.toString());

            return book;
        }

        @Override
        public Book addBookInOut(Book book) throws RemoteException {
            if(mBooks==null){
                mBooks = new ArrayList<>();
            }
            if(book == null){
                book = new Book();
            }

            book.setPrice(1);
            book.setName("zsj");

            if(!mBooks.contains(book)){
                mBooks.add(book);
            }

            Log.e(TAG, "addBook: "+mBooks.toString());

            return book;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: "+ intent.getAction());
        return bookManager;
    }
}
