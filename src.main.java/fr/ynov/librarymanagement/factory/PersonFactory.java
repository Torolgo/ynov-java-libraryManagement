package fr.ynov.librarymanagement.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PersonFactory {
    private static final ArrayList<Person> PersonList = new ArrayList<>();

    public static List<Person> getPersonList() {
        return PersonList;
    }

    public static void clearPersonList() {
        PersonList.clear();
    }

    public static void ReadAuthorFile() {
        Gson gson = new Gson();
        List<Person> authorList;
        try {
            authorList = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/authors.json"), new TypeToken<List<Author>>() {
            }.getType());
            if (authorList != null) {
                PersonList.addAll(authorList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ReadIllustratorFile() {
        Gson gson = new Gson();
        List<Person> illustratorList;
        try {
            illustratorList = gson.fromJson(new FileReader("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json"), new TypeToken<List<Illustrator>>() {
            }.getType());
            if (illustratorList != null) {
                PersonList.addAll(illustratorList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteAuthorFile(String name, String surname, String nationality, String dateOfBirth, String biography, String writingStyle) {
        int nextId = getNextAvailablePersonId();
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/authors.json");
        List<Author> authorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                authorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Author>>() {
                }.getType());
            }
            if (authorJson == null) {
                authorJson = new ArrayList<>();
            }
            authorJson.add(new Author(nextId, name, surname, nationality, dateOfBirth, biography, writingStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(authorJson));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteIllustratorFile(String name, String surname, String nationality, String dateOfBirth, String biography, String illustrationStyle) {
        int nextId = getNextAvailablePersonId();
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json");
        List<Illustrator> illustratorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                illustratorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Illustrator>>() {
                }.getType());
            }
            if (illustratorJson == null) {
                illustratorJson = new ArrayList<>();
            }
            illustratorJson.add(new Illustrator(nextId, name, surname, nationality, dateOfBirth, biography, illustrationStyle));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(illustratorJson));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAuthorBiography(int id, String newBiography) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/authors.json");
        List<Author> authorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                authorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Author>>() {
                }.getType());
            }
            if (authorJson == null) {
                return;
            }

            boolean found = false;
            for (Author author : authorJson) {
                if (author.getId() == id) {
                    author.setBiography(newBiography);
                    found = true;
                    break;
                }
            }

            if (found) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(gson.toJson(authorJson));
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateIllustratorBiography(int id, String newBiography) {
        Gson gson = new Gson();
        File file = new File("src.main.java/fr/ynov/librarymanagement/assets/illustrators.json");
        List<Illustrator> illustratorJson = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                illustratorJson = gson.fromJson(new FileReader(file), new TypeToken<List<Illustrator>>() {
                }.getType());
            }
            if (illustratorJson == null) {
                return;
            }

            boolean found = false;
            for (Illustrator illustrator : illustratorJson) {
                if (illustrator.getId() == id) {
                    illustrator.setBiography(newBiography);
                    found = true;
                    break;
                }
            }

            if (found) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(gson.toJson(illustratorJson));
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNextAvailablePersonId() {
        int maxId = 0;

        // Clear and reload all persons to ensure we have the latest data
        clearPersonList();
        ReadAuthorFile();
        ReadIllustratorFile();

        // Find the maximum ID currently in use
        for (Person person : PersonList) {
            if (person.getId() > maxId) {
                maxId = person.getId();
            }
        }

        // Return max ID + 1
        return maxId + 1;
    }

    public static Author findOrCreateAuthor(String authorName) {
        Author author = findAuthorByName(authorName);
        if (author == null) {
            PersonFactory.WriteAuthorFile(authorName, "", "", "", "", "");
            author = findAuthorByName(authorName);
        }
        return author;
    }

    public static Illustrator findOrCreateIllustrator(String illustratorName, String illustrationStyle) {
        Illustrator illustrator = findIllustratorByName(illustratorName);
        if (illustrator == null) {
            PersonFactory.WriteIllustratorFile(illustratorName, "", "", "", "", illustrationStyle);
            illustrator = findIllustratorByName(illustratorName);
        }
        return illustrator;
    }

    private static <person extends Person> person findPersonByName(String name, Class<person> personClass) {
        PersonFactory.clearPersonList();

        if (personClass == Author.class) {
            PersonFactory.ReadAuthorFile();
        } else if (personClass == Illustrator.class) {
            PersonFactory.ReadIllustratorFile();
        }

        for (Person person : PersonFactory.getPersonList()) {
            if (personClass.isInstance(person) &&
                    person.getNameAndSurname().toLowerCase().contains(name.toLowerCase())) {
                return personClass.cast(person);
            }
        }
        return null;
    }

    public static Author findAuthorByName(String name) {
        return findPersonByName(name, Author.class);
    }

    public static Illustrator findIllustratorByName(String name) {
        return findPersonByName(name, Illustrator.class);
    }
}