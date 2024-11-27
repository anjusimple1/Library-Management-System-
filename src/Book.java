// Book.java
public class Book {
    private String title;
    private String author;
    private int copiesAvailable;

    public Book(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Copies Available: " + copiesAvailable;
    }
}
