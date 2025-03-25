package fr.ynov.librarymanagement.domain;

public class Novel extends Book {
    private int chapters;

    public Novel(int id, String title, Author author, Genre gender, int year, int pages, int chapters) {
        super(id, title, author, gender, year, pages);
        this.chapters = chapters;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

}
