package fr.ynov.librarymanagement.domain;

/**
 * Abstract class representing a person with common attributes and methods.
 * This class serves as a base for specific types of persons, such as authors or readers.
 */
public abstract class Person {
    protected final int id;
    protected final String name;
    protected final String surname;
    protected final String nationality;
    protected final String dateOfBirth;
    protected String biography;

    /**
     * Constructs a new Person with the specified attributes.
     *
     * @param id The unique identifier for the person
     * @param name The first name of the person
     * @param surname The last name of the person
     * @param nationality The nationality of the person
     * @param dateOfBirth The date of birth of the person
     * @param biography Brief description of the person's life and career
     */
    public Person(int id, String name, String surname, String nationality, String dateOfBirth, String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.biography = biography;
    }

    public int getId() {
        return id;
    }

    public String getNameAndSurname() {
        return name + " " + surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBiography() {
        return biography;
    }

    public String getNationality() {
        return nationality;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
