# Library Management System

## Project Overview

### Introduction

This comprehensive Library Management System is developed in Java with a Swing GUI interface. It allows librarians to efficiently manage books, authors, and illustrators. The application provides an intuitive interface for adding different types of books (Novels, Manga, Comic Books), viewing existing collections, and managing loans. All data persists through JSON files.

### Key Project Goals

The primary objectives of this system are to:
- Maintain a comprehensive catalog of different book types
- Manage a database of authors and illustrators
- Track book borrowings and returns
- Ensure data persistence via JSON files

## Core Features

- **Book Management**:
    - Add different book types (Novels, Manga, Comic Books)
    - Browse the complete collection with detailed information
    - View availability status of each book

- **Person Management**:
    - Add and manage authors with their writing styles
    - Add and manage illustrators with their illustration styles

- **Loan Management**:
    - Borrow available books
    - Return borrowed books
    - Track loan status

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21 or higher
- IntelliJ IDEA or any Java IDE
- Gson library for JSON manipulation

### Running the Application

1. Clone the repository
2. Open the project in your IDE
3. Run the `ApplicationRunner` class to start the application

## Technical Architecture

### Package Organization

The project follows a structured organization with distinct packages:

- **domain**: Contains domain model classes that represent the core entities
- **factory**: Handles data persistence through JSON files
- **gui**: Manages the user interface components
- **main**: Contains the application entry point

### Data Persistence

All data is stored in JSON files located in the assets directory:
- `authors.json`: Author information
- `illustrators.json`: Illustrator information
- `novels.json`: Novel book entries
- `bd.json`: Comic book entries
- `manga.json`: Manga book entries

### User Interface

The GUI is built using Java Swing and organized into three main sections:
1. **Book Management**: Adding and viewing various book types
2. **Person Management**: Adding and viewing authors and illustrators
3. **Loan Management**: Borrowing and returning books

## Detailed Project Structure

```
src/
├── main/
│   └── java/
│       └── fr/
│           └── ynov/
│               └── librarymanagement/
│                   ├── assets/                             # JSON data files
│                   │   ├── authors.json                    # Author records
│                   │   ├── illustrators.json               # Illustrator records
│                   │   ├── novels.json                     # Novel book records
│                   │   ├── bd.json                         # Comic book records
│                   │   └── manga.json                      # Manga book records
│                   ├── domain/                             # Domain model classes
│                   │   ├── Author.java                     # Author entity
│                   │   ├── Bd.java                         # Comic book entity
│                   │   ├── Book.java                       # Abstract book base class
│                   │   ├── Genre.java                      # Book genre enumeration
│                   │   ├── Illustrator.java                # Illustrator entity
│                   │   ├── Manga.java                      # Manga book entity
│                   │   ├── Novel.java                      # Novel book entity
│                   │   ├── Person.java                     # Abstract person base class
│                   │   └── Takeable.java                   # Interface for borrowable items
│                   ├── factory/                            # Data access and persistence
│                   │   ├── FilesManagement.java            # Base JSON file operations
│                   │   ├── book/
│                   │   │   ├── BookFactory.java            # Book collection management
│                   │   │   ├── BookReader.java             # Reads books from JSON
│                   │   │   ├── BookUpdater.java            # Updates book status
│                   │   │   └── BookWriter.java             # Writes books to JSON
│                   │   └── person/
│                   │       ├── PersonFactory.java          # Person collection management
│                   │       ├── PersonFinder.java           # Finds persons by criteria
│                   │       ├── PersonReader.java           # Reads persons from JSON
│                   │       ├── PersonUpdater.java          # Updates person information
│                   │       └── PersonWriter.java           # Writes persons to JSON
│                   ├── gui/                                # User interface components
│                   │   ├── GuiManager.java                 # Main application window
│                   │   ├── book/
│                   │   │   ├── BookActionManager.java      # Book action handling
│                   │   │   ├── BookDisplayManager.java     # Book listing views
│                   │   │   ├── BookFormManager.java        # Book creation forms
│                   │   │   └── BookManager.java            # Book window management
│                   │   ├── loan/
│                   │   │   ├── LoanBookProcessor.java      # Loan processing logic
│                   │   │   ├── LoanManager.java            # Loan feature management
│                   │   │   └── LoanWindowManager.java      # Loan window interfaces
│                   │   └── person/
│                   │       ├── PersonActionManager.java    # Person action handling
│                   │       ├── PersonDisplayManager.java   # Person listing views
│                   │       ├── PersonFormManager.java      # Person creation forms
│                   │       └── PersonManager.java          # Person window management
│                   └── main/
│                       └── ApplicationRunner.java          # Application entry point
├── .gitignore                                              # Git ignore file
├── README.md                                               # Project documentation
└── lib/
    └── gson-2.10.1.jar                                     # Gson library for JSON handling
```

## Application Workflow

1. **Main Navigation**:
    - The application starts with `GuiManager.guiInterfaceManager()` presenting three main options
    - Users can navigate to Book Management, Person Management, or Loan Management

2. **Book Management Flow**:
    - Adding books: Choose book type (Novel, Manga, Comic) and fill in details
    - Viewing books: Browse all books with filtering options
    - Book details view: See complete information and borrow/return options

3. **Person Management Flow**:
    - Adding persons: Choose between Author or Illustrator and fill in details
    - Viewing persons: Browse all authors and illustrators
    - Person details view: See complete biographical information

4. **Loan Management Flow**:
    - Borrowing books: Enter book ID to check out
    - Returning books: Enter book ID to check in

## Contributors

* [DESSENNE Ylan](https://github.com/Torolgo) - Developer