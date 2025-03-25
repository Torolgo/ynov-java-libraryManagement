package fr.ynov.librarymanagement.domain;

public class Manga extends Book {
    private final String subGenre;

    public Manga(int id, String title, Author author, Genre gender, int year, int pages, String subGender) {
        super(id, title, author, gender, year, pages);
        this.subGenre = subGender;
    }
    public String getSubGenre() {
        return subGenre;
    }
}
