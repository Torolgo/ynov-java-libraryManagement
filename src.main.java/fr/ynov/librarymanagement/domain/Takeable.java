package fr.ynov.librarymanagement.domain;

public interface Takeable {
    void takeBook();
    void returnBook();
    Boolean isTaken();
}
