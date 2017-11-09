package specifications.book.by_publisher;

import entities.Book;

/**
 * Checks if book has a specified publisher.
 * Case ignored.
 */
public class PublisherEqualsIgnoreCaseSpecification extends SpecificationByPublisher {

    public PublisherEqualsIgnoreCaseSpecification(String publisher) {
        super(publisher);
    }

    @Override
    public boolean check(Book element) {
        return element.getPublisher().equalsIgnoreCase(getPublisher());
    }
}
