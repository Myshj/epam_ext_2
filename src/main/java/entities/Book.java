package entities;

import java.util.Calendar;

/**
 * Book entity.
 */
public class Book {

    private String name;

    private String author;

    private String publisher;

    private Calendar publishDate;

    private int countOfPages;

    private double price;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate=" + publishDate +
                ", countOfPages=" + countOfPages +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (countOfPages != book.countOfPages) return false;
        if (Double.compare(book.price, price) != 0) return false;
        if (name != null ? !name.equalsIgnoreCase(book.name) : book.name != null) return false;
        if (author != null ? !author.equalsIgnoreCase(book.author) : book.author != null) return false;
        if (publisher != null ? !publisher.equalsIgnoreCase(book.publisher) : book.publisher != null) return false;
        return publishDate != null ? publishDate.equals(book.publishDate) : book.publishDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + countOfPages;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(String name, String author, String publisher, Calendar publishDate, int countOfPages, double price) {

        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.countOfPages = countOfPages;
        this.price = price;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Calendar getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Calendar publishDate) {
        this.publishDate = publishDate;
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
