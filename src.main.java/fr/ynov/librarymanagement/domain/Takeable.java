package fr.ynov.librarymanagement.domain;

public interface Takeable {
    void take();
    void returnBook();
    Boolean isTaked();
}
