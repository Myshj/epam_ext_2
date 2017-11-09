package specifications.book.by_publish_date;

import specifications.book.BookSpecification;

import java.util.Calendar;

/**
 * Base class for all book specification that use field "publishDate" only.
 */
public abstract class PublishDateSpecification extends BookSpecification {
    private Calendar publishDate;

    public PublishDateSpecification(Calendar publishDate) {

        this.publishDate = publishDate;
    }

    public Calendar getPublishDate() {
        return publishDate;
    }
}
