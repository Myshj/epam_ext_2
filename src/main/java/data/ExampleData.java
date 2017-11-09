package data;

import entities.Book;
import util.repositories.Repository;

import java.util.GregorianCalendar;

/**
 * Example initial data.
 */
public class ExampleData {
    public static final Repository<Book> REPOSITORY = new Repository<>();

    static {
        REPOSITORY.add(
                new Book(
                        "Книга 1",
                        "Автор 1",
                        "Издатель 1",
                        new GregorianCalendar(1995, 10, 10),
                        100,
                        10
                )
        );

        REPOSITORY.add(
                new Book(
                        "Книга 2",
                        "Автор 2",
                        "издатель 1",
                        new GregorianCalendar(2000, 5, 5),
                        150,
                        20
                )
        );

        REPOSITORY.add(
                new Book(
                        "Книга 3",
                        "Автор 2",
                        "издатель 2",
                        new GregorianCalendar(2000, 5, 30),
                        125,
                        20
                )
        );

        REPOSITORY.add(
                new Book(
                        "Книга 4",
                        "Автор 3",
                        "издатель 2",
                        new GregorianCalendar(2000, 5, 5),
                        150,
                        20
                )
        );

        REPOSITORY.add(
                new Book(
                        "Книга 5",
                        "Автор 4",
                        "издатель 3",
                        new GregorianCalendar(2000, 5, 5),
                        150,
                        50
                )
        );

        REPOSITORY.add(
                new Book(
                        "Книга 6",
                        "Автор 1",
                        "издатель 2",
                        new GregorianCalendar(2000, 5, 5),
                        150,
                        20
                )
        );

        REPOSITORY.add(
                new Book(
                        "Книга 7",
                        "Автор 1",
                        "Издатель 1",
                        new GregorianCalendar(1997, 10, 10),
                        100,
                        10
                )
        );
    }
}
