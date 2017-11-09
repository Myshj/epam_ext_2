package specifications.book.by_publish_date;

import entities.Book;

import java.util.Calendar;

/**
 * Checks if book is published after the specified date.
 */
public class PublishDateAfterSpecification extends PublishDateSpecification {
    public PublishDateAfterSpecification(Calendar publishDate) {
        super(publishDate);
    }

    @Override
    public boolean check(Book element) {
        return getPublishDate().before(element.getPublishDate());
    }
}
