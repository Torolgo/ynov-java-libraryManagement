package fr.ynov.librarymanagement.domain;

/**
 * Represents an author with personal details and a specific writing style.
 * It extends the Person class to include author-specific information.
 */
public class Author extends Person {
    private final String writingStyle;

    /**
     * Constructs a new Author with the specified attributes.
     *
     * @param id The unique identifier for the author
     * @param name The first name of the author
     * @param surname The last name/surname of the author
     * @param nationality The nationality of the author
     * @param dateOfBirth The author's date of birth as a string
     * @param biography A brief biography of the author
     * @param writingStyle The characteristic writing style of the author
     */
    public Author(int id, String name, String surname, String nationality, String dateOfBirth, String biography, String writingStyle) {
        super(id, name, surname, nationality, dateOfBirth, biography);
        this.writingStyle = writingStyle;
    }

    public String getWritingStyle() {
        return writingStyle;
    }
}
