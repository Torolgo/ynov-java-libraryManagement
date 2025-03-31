package fr.ynov.librarymanagement.domain;

public class Novel extends Book {
    private final int chapters;

    /**
     * Constructs a new Novel with the specified attributes.
     * <p>
     * This constructor initializes a Novel object with all the basic book properties
     * inherited from the Book class along with the number of chapters in the novel.
     * </p>
     *
     * @param id The unique identifier for the novel
     * @param title The title of the novel
     * @param author The author of the novel
     * @param gender The genre of the novel
     * @param year The publication year of the novel
     * @param pages The number of pages in the novel
     * @param chapters The number of chapters in the novel
     */
    public Novel(int id, String title, Author author, Genre gender, int year, int pages, int chapters) {
        super(id, title, author, gender, year, pages);
        this.chapters = chapters;
    }

    public int getChapters() {
        return chapters;
    }

}
