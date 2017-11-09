package views;

import entities.Book;

import java.util.Calendar;

/**
 * View for the one book.
 */
public class BookView extends View {
    private Book book;

    public BookView(Book book) {
        this.book = book;
    }

    @Override
    public void display() {
        // System.out.println(book.toString());
        System.out.printf(
                "Book{author=%s, publisher=%s, publishYear=%d, countOfPages=%d, price=%f\n",
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishDate().get(Calendar.YEAR),
                book.getCountOfPages(),
                book.getPrice()
        );
    }
}
