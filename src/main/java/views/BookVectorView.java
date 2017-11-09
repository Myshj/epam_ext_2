package views;

import entities.Book;
import util.collections.MyVector;

/**
 * View for the vector of books.
 */
public class BookVectorView extends View {
    private MyVector<Book> books;

    public BookVectorView(MyVector<Book> books) {
        this.books = books;
    }

    @Override
    public void display() {
        //System.out.println(books.toString());

        for (int i = 0; i < books.getSize(); i++) {
            new BookView(books.get(i)).display();
        }
    }
}
