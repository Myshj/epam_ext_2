package specifications.book.by_publisher;

import specifications.book.BookSpecification;

/**
 * Base class for all book specifications that use field "publisher" only.
 */
public abstract class SpecificationByPublisher extends BookSpecification {
    private String publisher;

    public SpecificationByPublisher(String publisher) {

        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }
}
