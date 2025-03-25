package fr.ynov.librarymanagement.domain;

public class Bd extends Book{
    private final Illustrator illustrator;
    private final String illustrationStyle;

    public Bd(int id, String title, Author author, Genre gender, int year, int pages, Illustrator illustrator, String illustrationStyle) {
        super(id, title, author, gender, year, pages);
        this.illustrator = illustrator;
        this.illustrationStyle = illustrationStyle;
    }

    public Illustrator getIllustrator() {
        return illustrator;
    }

    public String getIllustrationStyle() {
        return illustrationStyle;
    }
}