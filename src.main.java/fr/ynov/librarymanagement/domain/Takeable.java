package fr.ynov.librarymanagement.domain;

/**
 * Interface representing a takeable item, such as a book.
 * It provides methods to manage the borrowing and returning of the item.
 */
public interface Takeable {
    void takeBook();
    void returnBook();
    Boolean isTaken();
}
