package fr.ynov.librarymanagement.factory.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonFactory {
    private static final ArrayList<Person> personList = new ArrayList<>();

    public static List<Person> getPersonList() {
        return personList;
    }

    public static void clearPersonList() {
        personList.clear();
    }

    /**
     * Loads all authors and illustrators from JSON files.
     */
    public static void loadAllPersons() {
        clearPersonList();
        PersonReader.readAuthorFile();
        PersonReader.readIllustratorFile();
    }

    /**
     * Returns the next available ID for a new person.
     */
    public static int getNextAvailablePersonId() {
        int maxId = 0;
        loadAllPersons();
        for (Person person : personList) {
            if (person.getId() > maxId) {
                maxId = person.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Finds or creates an author by name.
     */
    public static Author findOrCreateAuthor(String authorName) {
        Author author = PersonFinder.findAuthorByName(authorName);
        if (author == null) {
            PersonWriter.writeAuthorFile(authorName, "", "", "", "", "");
            author = PersonFinder.findAuthorByName(authorName);
        }
        return author;
    }

    /**
     * Finds or creates an illustrator by name and illustration style.
     */
    public static Illustrator findOrCreateIllustrator(String illustratorName, String illustrationStyle) {
        Illustrator illustrator = PersonFinder.findIllustratorByName(illustratorName);
        if (illustrator == null) {
            PersonWriter.writeIllustratorFile(illustratorName, "", "", "", "", illustrationStyle);
            illustrator = PersonFinder.findIllustratorByName(illustratorName);
        }
        return illustrator;
    }
}