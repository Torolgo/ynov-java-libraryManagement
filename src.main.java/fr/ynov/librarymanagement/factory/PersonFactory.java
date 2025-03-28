package fr.ynov.librarymanagement.factory;

import com.google.gson.reflect.TypeToken;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;

import java.util.ArrayList;
import java.util.List;

import static fr.ynov.librarymanagement.factory.Reader.loadAllPersons;

public class PersonFactory {
    private static final ArrayList<Person> PERSON_LIST = new ArrayList<>();

    public static List<Person> getPersonList() {
        return PERSON_LIST;
    }

    public static void clearPersonList() {
        PERSON_LIST.clear();
    }

    /**
     * Returns the next available person ID.
     * <p>
     * This method reloads all persons from storage, then finds the highest ID
     * among existing persons and returns that value plus one. This ensures
     * that new persons receive a unique ID that doesn't conflict with any existing person.
     * </p>
     *
     * @return The next available unique ID for a new person
     */
    public static int getNextAvailablePersonId() {
        int maxId = 0;
        loadAllPersons();
        for (Person person : PERSON_LIST) {
            if (person.getId() > maxId) {
                maxId = person.getId();
            }
        }
        return maxId + 1;
    }


    /**
     * Finds or creates a person (Author or Illustrator) by name.
     * <p>
     * This method searches for a person with the specified name in the system.
     * If the person does not exist, it creates a new entry with the provided details.
     * </p>
     *
     * @param name The person's name
     * @param personClass The class of the person (Author.class or Illustrator.class)
     * @param style The style (writing for an author or illustration for an illustrator)
     * @return The found or newly created person
     */
    public static <T extends Person> T findOrCreatePerson(String name, Class<T> personClass, String style) {
        String filename = personClass == Author.class ? "authors.json" : "illustrators.json";
        T person = findPersonByName(name, personClass);

        if (person == null) {
            Writer.writePersonFile(name, "", "", "", "", style, personClass, filename);
            person = findPersonByName(name, personClass);
        }

        return person;
    }

    /**
     * Finds a person by name in the system.
     * <p>
     * This method searches for a person with the specified name in the system.
     * </p>
     *
     * @param name The person's name
     * @param personClass The class of the person (Author.class or Illustrator.class)
     * @return The found person, or null if not found
     */
    private static <P extends Person> P findPersonByName(String name, Class<P> personClass) {
        PersonFactory.clearPersonList();

        if (personClass == Author.class) {
            Reader.readPersonFile("authors.json", new TypeToken<List<Author>>() {});
        } else if (personClass == Illustrator.class) {
            Reader.readPersonFile("illustrators.json", new TypeToken<List<Illustrator>>() {});
        }

        for (Person person : PersonFactory.getPersonList()) {
            if (personClass.isInstance(person) &&
                    person.getNameAndSurname().toLowerCase().contains(name.toLowerCase())) {
                return personClass.cast(person);
            }
        }
        return null;
    }
}