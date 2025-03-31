package fr.ynov.librarymanagement.domain;

public class Illustrator extends Person {
    private final String illustrationStyle;

    /**
     * Constructs a new Illustrator with the specified attributes.
     * <p>
     * This constructor initializes an illustrator with personal information inherited from
     * the Person class along with the specific illustration style that characterizes their work.
     * </p>
     *
     * @param id The unique identifier for the illustrator
     * @param name The first name of the illustrator
     * @param surname The last name of the illustrator
     * @param nationality The nationality of the illustrator
     * @param dateOfBirth The date of birth of the illustrator
     * @param biography Brief description of the illustrator's life and career
     * @param illustrationStyle The characteristic style of illustrations created by this illustrator
     */
    public Illustrator(int id, String name, String surname, String nationality, String dateOfBirth, String biography, String illustrationStyle) {
        super(id, name, surname, nationality, dateOfBirth, biography);
        this.illustrationStyle = illustrationStyle;
    }

    public String getIllustrationStyle() {
        return illustrationStyle;
    }
}
