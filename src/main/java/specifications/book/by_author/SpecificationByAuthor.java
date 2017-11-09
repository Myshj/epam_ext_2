package specifications.book.by_author;

import specifications.book.BookSpecification;

/**
 * Base class for all book specifications that use field "author" only
 */
public abstract class SpecificationByAuthor extends BookSpecification {
    private String author;

    public SpecificationByAuthor(String author) {

        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
