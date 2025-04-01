package fr.ynov.librarymanagement.domain;

/**
 * Represents a comic book (BD) in the library management system.
 * It extends the Book class and includes additional attributes specific to comic books.
 */
public class Bd extends Book{
    private final Illustrator illustrator;
    private final String illustrationStyle;

    /**
     * Constructs a new comic book (BD) with the specified attributes.
     *
     * @param id The unique identifier for the comic book
     * @param title The title of the comic book
     * @param author The author who wrote the comic book
     * @param gender The genre classification of the comic book
     * @param year The publication year of the comic book
     * @param pages The total number of pages in the comic book
     * @param illustrator The artist who illustrated the comic book
     * @param illustrationStyle The characteristic style of illustrations used in the comic book
     */
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