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