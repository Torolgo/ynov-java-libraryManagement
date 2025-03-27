package fr.ynov.librarymanagement.domain;

public class Manga extends Book {
    private final String subGenre;

    public Manga(int id, String title, Author author, Genre gender, int year, int pages, String subGenre) {
        super(id, title, author, gender, year, pages);
        this.subGenre = subGenre;
    }
    public String getSubGenre() {
        return subGenre;
    }
}
