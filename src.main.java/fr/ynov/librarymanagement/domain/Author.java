package fr.ynov.librarymanagement.domain;

public class Author extends Person {
    private String writingStyle;

    public Author(int id, String name, String surname, String nationality, String dateOfBirth, String biography, String writingStyle) {
        super(id, name, surname, nationality, dateOfBirth, biography);
        this.writingStyle = writingStyle;
    }

    public String getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle) {
        this.writingStyle = writingStyle;
    }
}
