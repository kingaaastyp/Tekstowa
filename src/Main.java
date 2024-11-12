import controller.BookController;
import model.BookRepository;
import view.BookView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BookRepository bookRepository = new BookRepository("books.txt");
        BookController bookController = new BookController(bookRepository, null);
        BookView bookView = new BookView(bookController);

        bookController.setBookView(bookView); // Ustawienie bookView w kontrolerze

        bookView.handleUserInput(); // Start interakcji z użytkownikiem
    }
}
