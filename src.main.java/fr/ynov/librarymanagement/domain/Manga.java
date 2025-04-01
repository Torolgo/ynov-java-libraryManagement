package fr.ynov.librarymanagement.domain;

/**
 * Represents a Manga book with specific attributes such as sub-genre.
 * It extends the Book class to include manga-specific information.
 */
public class Manga extends Book {
    private final String subGenre;

    /**
     * Constructs a new Manga with the specified attributes.
     *
     * @param id The unique identifier for the manga
     * @param title The title of the manga
     * @param author The author of the manga
     * @param gender The genre of the manga
     * @param year The publication year of the manga
     * @param pages The number of pages in the manga
     * @param subGenre The specific sub-genre of the manga
     */
    public Manga(int id, String title, Author author, Genre gender, int year, int pages, String subGenre) {
        super(id, title, author, gender, year, pages);
        this.subGenre = subGenre;
    }
    public String getSubGenre() {
        return subGenre;
    }
}
