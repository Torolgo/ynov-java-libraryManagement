package fr.ynov.librarymanagement.factory.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;

public class PersonFinder {

    /**
     * Finds a person by their name in the list of authors or illustrators.
     *
     * @param name        The name of the person to find.
     * @param personClass The class type of the person (Author or Illustrator).
     * @param <P>        The type of the person (Author or Illustrator).
     * @return The found person, or null if not found.
     */
    private static <P extends Person> P findPersonByName(String name, Class<P> personClass) {
        PersonFactory.clearPersonList();

        if (personClass == Author.class) {
            PersonReader.readAuthorFile();
        } else if (personClass == Illustrator.class) {
            PersonReader.readIllustratorFile();
        }

        for (Person person : PersonFactory.getPersonList()) {
            if (personClass.isInstance(person) &&
                    person.getNameAndSurname().toLowerCase().contains(name.toLowerCase())) {
                return personClass.cast(person);
            }
        }
        return null;
    }

    /**
     * Finds an author by their name.
     *
     * @param name The name of the author to find.
     * @return The found author, or null if not found.
     */
    public static Author findAuthorByName(String name) {
        return findPersonByName(name, Author.class);
    }

    /**
     * Finds an illustrator by their name.
     *
     * @param name The name of the illustrator to find.
     * @return The found illustrator, or null if not found.
     */
    public static Illustrator findIllustratorByName(String name) {
        return findPersonByName(name, Illustrator.class);
    }
}