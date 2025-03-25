package fr.ynov.librarymanagement.domain;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String name;
    protected String surname;
    protected String nationality;
    protected String dateOfBirth;
    protected String biography;

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

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
