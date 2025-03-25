package fr.ynov.librarymanagement.domain;

public class Manga extends Book {
    private String subGender;

    public Manga(int id, String title, Author author, Genre gender, int year, int pages, String subGender) {
        super(id, title, author, gender, year, pages);
        this.subGender = subGender;
    }

    public String getSubGender() {
        return subGender;
    }
}
