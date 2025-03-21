package fr.ynov.librarymanagement.domain;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String name;
    protected String surname;
    protected int age;
    protected String biography;

    public Person(int id, String name, String surname, int age, String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.biography = biography;
    }

    public int getId() {
        return id;
    }

    public String getNameAndSurname() {
        return name + " " + surname;
    }

    public int getAge() {
        return age;
    }

    public int getBirthYear() {
        return LocalDate.now().getYear() - age;
    }

    public String getBiography() {
        return biography;
    }
}
