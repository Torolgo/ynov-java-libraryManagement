package fr.ynov.librarymanagement.domain;

public abstract class Book implements Takeable {
    private final int id;
    private final String title;
    private final Author author;
    private final Genre genre;
    private final int year;
    private final int pages;
    private Boolean taken = false;

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