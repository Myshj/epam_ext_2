package specifications.book.by_author;

import entities.Book;

/**
 * Checks if book has a specified author.
 * Case ignored.
 */
public class AuthorEqualsIgnoreCaseSpecification extends SpecificationByAuthor {
    public AuthorEqualsIgnoreCaseSpecification(String author) {
        super(author);
    }

    @Override
    public boolean check(Book element) {
        return element.getAuthor().equalsIgnoreCase(getAuthor());
    }
}
