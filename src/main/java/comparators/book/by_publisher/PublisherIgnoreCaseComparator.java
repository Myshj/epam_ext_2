package comparators.book.by_publisher;

import entities.Book;

/**
 * Compares two objects using "publisher" field.
 * Case ignored.
 */
public class PublisherIgnoreCaseComparator extends PublisherComparator {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPublisher().compareToIgnoreCase(o2.getPublisher());
    }
}
