package fr.ynov.librarymanagement.domain;

public class Bd extends Book{
    private final Illustrator illustrator;

    public Bd(int id, String title, Author author, Gender gender, int year, int pages, Illustrator illustrator) {
        super(id, title, author, gender, year, pages);
        this.illustrator = illustrator;
    }

    public Illustrator getIllustrator() {
        return illustrator;
    }
}