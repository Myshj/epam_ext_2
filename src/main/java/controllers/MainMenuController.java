package controllers;

import comparators.book.by_publisher.PublisherIgnoreCaseComparator;
import entities.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import specifications.book.by_author.AuthorEqualsIgnoreCaseSpecification;
import specifications.book.by_publish_date.PublishDateAfterSpecification;
import specifications.book.by_publisher.PublisherEqualsIgnoreCaseSpecification;
import util.file_io.FileObjectReader;
import util.file_io.FileObjectWriter;
import util.repositories.Repository;
import util.stream_operators.input.InputStreamOperator;
import views.BookVectorView;
import views.MainMenuView;

import java.io.*;
import java.util.GregorianCalendar;

/**
 * Controller for main menu.
 */
public class MainMenuController extends InputStreamOperator {

    private static final Logger log = LogManager.getRootLogger();

    private static final int OPTION_LIST_ALL = 1;
    private static final int OPTION_LIST_BY_PUBLISHER = 2;
    private static final int OPTION_LIST_BY_AUTHOR = 3;
    private static final int OPTION_LIST_PUBLISHED_AFTER = 4;
    private static final int OPTION_SORT_BY_PUBLISHER = 5;
    private static final int OPTION_SAVE = 6;
    private static final int OPTION_LOAD = 7;
    private static final int OPTION_QUIT = 8;

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
        try {
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
                case OPTION_SAVE:
                    onSaveAll();
                    break;
                case OPTION_LOAD:
                    onLoadAll();
                    break;
                case OPTION_QUIT:
                    onExit();
                    break;
            }
        } catch (NumberFormatException e){
            log.error("Bad user input", e);
        }

    }

    private void onLoadAll() {
        log.info("Load all invoked");
        data = null;
        data = new FileObjectReader<Repository<Book>>(/*"C:\\Users\\Alexey\\IdeaProjects\\epam_ext_2\\db.ser"*/
                readString()
        ).read();
    }

    private void onSaveAll() {
        log.info("Save all invoked");
        new FileObjectWriter<Repository<Book>>(/*"C:\\Users\\Alexey\\IdeaProjects\\epam_ext_2\\db.ser"*/
                readString()
        ).write(data);
    }

    /**
     * Called every time command to list all books received.
     */
    private void onListAll() {
        log.info("List all invoked");
        listAll();
    }

    /**
     * Called every time command to list books with specified publisher received.
     */
    private void onListByPublisher() {
        log.info("List by publisher invoked");
        view.requestPublisher();
        listByPublisher(readString());
    }

    /**
     * Called every time command to list books with specified author received.
     */
    private void onListByAuthor() {
        log.info("List by author invoked");
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
        log.info("List published after invoked");
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
        log.info("Sort by publisher invoked");
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
        log.info("Exit invoked");
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
        log.info("Main menu started");
        view.display();
    }
}
