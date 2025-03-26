package fr.ynov.librarymanagement.domain;

public class Illustrator extends Person {
    private final String illustrationStyle;

    public Illustrator(int id, String name, String surname, String nationality, String dateOfBirth, String biography, String illustrationStyle) {
        super(id, name, surname, nationality, dateOfBirth, biography);
        this.illustrationStyle = illustrationStyle;
    }

    public String getIllustrationStyle() {
        return illustrationStyle;
    }
}
