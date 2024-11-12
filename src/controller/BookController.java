package controller;

import model.Book;
import model.BookRepository;
import view.BookView;

import java.io.IOException;
import java.util.List;

public class BookController {
    private BookRepository bookRepository;
    private BookView bookView;
    private Book tempBook;

    public BookController(BookRepository bookRepository, BookView bookView) {
        this.bookRepository = bookRepository;
        this.bookView = bookView;
    }

    public void selectOption(int option) throws IOException {
        switch (option) {
            case 0:
                displayAllBooks();
                break;
            case 1:
                searchBooksByTitle();
                break;
            case 2:
                searchBooksByAuthor();
                break;
            case 3:
                addNewBook();
                break;
            case 4:
                removeBook();
                break;
            case 5:
                sortBooks();
                break;
            case 6:
                filterBooks();
                break;
            case 7:
                displayBookDetails();
                break;
            case 8:
                editBook();
                break;
            case 9:
                System.exit(0);
                break;
            default:
                bookView.displayMessage("Nieprawidłowy wybór.");
                break;
        }
    }

    public void displayAllBooks() throws IOException{
        List<Book> books = bookRepository.getBooks();
        bookView.displayBooks(books);
    }

    public void searchBooksByTitle() {
        bookView.promptForInput("Podaj tytuł książki do wyszukania:");
    }

    public void searchBooksByAuthor() {
        bookView.promptForInput("Podaj autora książki do wyszukania:");
    }

    public void addNewBook() {
        tempBook = new Book("", "", "", 0, 0, "");
        bookView.promptForInput("Podaj tytuł książki:");
    }

    public void removeBook() {
        bookView.promptForInput("Podaj numer książki do usunięcia:");
    }

    public void sortBooks() {
        bookView.promptForInput("Sortuj książki: 'tytuł' lub 'autor':");
    }

    public void filterBooks() {
        bookView.promptForInput("Podaj autora do filtrowania:");
    }

    public void displayBookDetails() {
        bookView.promptForInput("Podaj numer książki do wyświetlenia:");
    }

    public void editBook() {
        bookView.promptForInput("Podaj numer książki do edycji:");
    }
    public void setBookView(BookView bookView) {
        this.bookView = bookView;
    }

    public void handleUserInput(String input, String prompt) throws IOException  {
        if (input.equalsIgnoreCase("wróć")) {
            bookView.showMenu();
            return;
        }

        switch (prompt) {
            case "Podaj tytuł książki do wyszukania:":
                List<Book> booksByTitle = bookRepository.searchByTitle(input);
                bookView.displayBooks(booksByTitle);
                break;

            case "Podaj autora książki do wyszukania:":
                List<Book> booksByAuthor = bookRepository.searchByAuthor(input);
                bookView.displayBooks(booksByAuthor);
                break;

            case "Podaj tytuł książki:":
                tempBook = new Book(input, "", "", 0, 0, "");
                bookView.promptForInput("Podaj autora książki:");
                break;

            case "Podaj autora książki:":
                tempBook.setAuthor(input);
                bookView.promptForInput("Podaj gatunek książki:");
                break;

            case "Podaj gatunek książki:":
                tempBook.setGenre(input);
                bookView.promptForInput("Podaj ilość stron:");
                break;

            case "Podaj ilość stron:":
                try {
                    int pageCount = Integer.parseInt(input);
                    tempBook.setPageCount(pageCount);
                    bookView.promptForInput("Podaj rok wydania:");
                } catch (NumberFormatException e) {
                    bookView.displayMessage("Ilość stron musi być liczbą.");
                }
                break;

            case "Podaj rok wydania:":
                try {
                    int year = Integer.parseInt(input);
                    tempBook.setYear(year);
                    bookView.promptForInput("Podaj opis książki:");
                } catch (NumberFormatException e) {
                    bookView.displayMessage("Rok musi być liczbą.");
                }
                break;

            case "Podaj opis książki:":
                tempBook.setDescription(input);
                bookRepository.addBook(tempBook);
                bookView.displayMessage("Książka została dodana.");
                break;

            default:
                bookView.displayMessage("Nieprawidłowe dane wejściowe.");
                break;
        }
    }
}
