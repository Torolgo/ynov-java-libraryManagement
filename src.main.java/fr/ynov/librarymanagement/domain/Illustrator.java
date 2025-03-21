package fr.ynov.librarymanagement.domain;

public class Illustrator extends Person {
    private String illustrationStyle;

    public Illustrator(int id, String name, String surname, int age, String biography, String illustrationStyle) {
        super(id, name, surname, age, biography);
        this.illustrationStyle = illustrationStyle;
    }

    public String getIllustrationStyle() {
        return illustrationStyle;
    }

    public void setIllustrationStyle(String illustrationStyle) {
        this.illustrationStyle = illustrationStyle;
    }
}
