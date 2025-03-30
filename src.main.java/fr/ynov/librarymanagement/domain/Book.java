package fr.ynov.librarymanagement.domain;

public abstract class Book implements Takeable {
    private final int id;
    private final String title;
    private final Author author;
    private final Genre genre;
    private final int year;
    private final int pages;
    private Boolean taken = false;

    /**
     * Constructs a new Book with the specified attributes.
     * <p>
     * This constructor initializes a Book object with its unique identifier, title, author,
     * genre, publication year, and number of pages.
     * </p>
     *
     * @param id The unique identifier for the book
     * @param title The title of the book
     * @param author The author of the book
     * @param genre The genre of the book
     * @param year The publication year of the book
     * @param pages The number of pages in the book
     */
    public Book(int id, String title, Author author, Genre genre, int year, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public Boolean isTaken() {
        return taken;
    }

    public void takeBook() {
        taken = true;
    }

    public void returnBook() {
        taken = false;
    }
}