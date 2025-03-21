package fr.ynov.librarymanagement.domain;

public class Author extends Person {
    private String writingStyle;

    public Author(int id, String name, String surname, int age, String biography, String writingStyle) {
        super(id, name, surname, age, biography);
        this.writingStyle = writingStyle;
    }

    public String getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle) {
        this.writingStyle = writingStyle;
    }
}
