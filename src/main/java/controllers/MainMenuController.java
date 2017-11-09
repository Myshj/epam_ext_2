package controllers;

import comparators.book.by_publisher.PublisherIgnoreCaseComparator;
import entities.Book;
import specifications.book.by_author.AuthorEqualsIgnoreCaseSpecification;
import specifications.book.by_publish_date.PublishDateAfterSpecification;
import specifications.book.by_publisher.PublisherEqualsIgnoreCaseSpecification;
import util.repositories.Repository;
import util.stream_operators.input.InputStreamOperator;
import views.BookVectorView;
import views.MainMenuView;

import java.io.InputStream;
import java.util.GregorianCalendar;

/**
 * Controller for main menu.
 */
public class MainMenuController extends InputStreamOperator {

    private static final int OPTION_LIST_ALL = 1;
    private static final int OPTION_LIST_BY_PUBLISHER = 2;
    private static final int OPTION_LIST_BY_AUTHOR = 3;
    private static final int OPTION_LIST_PUBLISHED_AFTER = 4;
    private static final int OPTION_SORT_BY_PUBLISHER = 5;
    private static final int OPTION_QUIT = 6;

    private MainMenuView view;
    private Repository<Book> data;

    /**
     * Parametrized constructor.
     * @param input Stream to read data/commands from.
     * @param view Main view.
     * @param data Data to control.
     */
    public MainMenuController(
            InputStream input,
            MainMenuView view,
            Repository<Book> data
    ) {
        super(input);
        this.view = view;
        this.data = data;
    }

    @Override
    protected void onMessage(String message) {
        int chosenOption = parseInt(message);
        switch (chosenOption) {
            case OPTION_LIST_ALL:
                onListAll();
                break;
            case OPTION_LIST_BY_PUBLISHER:
                onListByPublisher();
                break;
            case OPTION_LIST_BY_AUTHOR:
                onListByAuthor();
                break;
            case OPTION_LIST_PUBLISHED_AFTER:
                onListPublishedAfter();
                break;
            case OPTION_SORT_BY_PUBLISHER:
                onSortByPublisher();
                break;
            case OPTION_QUIT:
                onExit();
                break;
        }
    }

    /**
     * Called every time command to list all books received.
     */
    private void onListAll() {
        listAll();
    }

    /**
     * Called every time command to list books with specified publisher received.
     */
    private void onListByPublisher() {
        view.requestPublisher();
        listByPublisher(readString());
    }

    /**
     * Called every time command to list books with specified author received.
     */
    private void onListByAuthor() {
        view.requestAuthor();
        listByAuthor(readString());
    }

    /**
     * Lists all books with specified author.
     * @param author Author of books to list.
     */
    private void listByAuthor(String author) {
        new BookVectorView(
                data.filter(
                        new AuthorEqualsIgnoreCaseSpecification(
                                author
                        )
                )
        ).display();
    }

    /**
     * Called every time command to list books published after the specified year received.
     */
    private void onListPublishedAfter() {
        view.requestYear();
        listPublishedAfter(parseInt(readString()));
    }

    /**
     * Shortcut to "Integer.parseInt(str)"
     * @param str String to parse.
     * @return Parsed int.
     */
    private int parseInt(String str) {
        return Integer.parseInt(str);
    }

    /**
     * Lists all books published after the specified year.
     * @param publishYear Minimal year for published books.
     */
    private void listPublishedAfter(int publishYear) {
        new BookVectorView(
                data.filter(
                        new PublishDateAfterSpecification(
                                new GregorianCalendar(publishYear, 1, 1)
                        )
                )
        ).display();
    }

    /**
     * Called every time command to sort books by publisher received.
     */
    private void onSortByPublisher() {
        sortByPublisher();
    }

    /**
     * Outputs sorted by publisher books.
     */
    private void sortByPublisher() {

        new BookVectorView(
                data.getAll().sorted(
                        new PublisherIgnoreCaseComparator()
                )
        ).display();
    }

    /**
     * Lists all books.
     */
    private void listAll() {
        new BookVectorView(data.getAll()).display();
    }

    /**
     * Lists all books with specified publisher.
     * @param publisher Publisher of books to list.
     */
    private void listByPublisher(String publisher) {
        new BookVectorView(
                data.filter(
                        new PublisherEqualsIgnoreCaseSpecification(
                                publisher
                        )
                )
        ).display();
    }

    /**
     * Called every time command to exit received.
     */
    private void onExit() {
        exit();
    }

    /**
     * Successfully ends main loop
     */
    private void exit(){
        stop();
    }

    @Override
    protected void onStarted() {
        view.display();
    }
}
