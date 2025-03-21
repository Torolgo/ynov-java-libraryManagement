package fr.ynov.librarymanagement.domain;

public class Book implements Takeable{
    private final String title;
    private final Author author;
    private final Gender gender;
    private final int year;
    private final int pages;
    private Boolean taked = false;

    public Book(String title, Author author, Gender gender, int year, int pages) {
        this.title = title;
        this.author = author;
        this.gender = gender;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Gender getGender() {
        return gender;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public Boolean isTaked() {
        return taked;
    }

    public void take() {
        taked = true;
    }

    public void returnBook() {
        taked = false;
    }

}


