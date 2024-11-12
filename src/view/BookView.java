package view;

import controller.BookController;
import model.Book;

import java.io.IOException;
import java.util.List;

public class BookView {
    private BookController controller;
    private int selectedIndex = 0;
    private String[] menuOptions = {
            "1. Wyświetl wszystkie książki",
            "2. Wyszukaj książki po tytule",
            "3. Wyszukaj książki po autorze",
            "4. Dodaj książkę",
            "5. Usuń książkę",
            "6. Sortuj książki",
            "7. Filtruj książki",
            "8. Wyświetl szczegóły książki",
            "9. Edytuj książkę",
            "10. Wyjdź"
    };

    public BookView(BookController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        System.out.println("\n=== MENU ===");
        for (int i = 0; i < menuOptions.length; i++) {
            if (i == selectedIndex) {
                System.out.println("-> " + menuOptions[i] + " <-"); // Wyróżnienie wybranej opcji
            } else {
                System.out.println("   " + menuOptions[i]);
            }
        }
        System.out.println("\nUżyj strzałek do nawigacji, Enter aby wybrać, ESC aby wrócić.");
    }

    public void handleUserInput() throws IOException {
        while (true) {
            showMenu(); // Wyświetl menu z aktualnym podświetleniem
            int input = System.in.read(); // Odczytanie pierwszego znaku

            System.out.println("Odczytano kod: " + input); // Debug: Wyświetlenie odczytanego kodu

            if (input == 27) { // 27 to kod ANSI ESC, który rozpoczyna sekwencję klawisza strzałki
                int second = System.in.read();
                int third = System.in.read();

                System.out.println("Klawisz sekwencji strzałek: " + second + ", " + third); // Debug

                if (second == 91) { // Sprawdzenie znaku '['
                    switch (third) {
                        case 65: // Kod dla strzałki w górę
                            selectedIndex = (selectedIndex - 1 + menuOptions.length) % menuOptions.length;
                            System.out.println("Strzałka w górę, indeks: " + selectedIndex); // Debug
                            break;
                        case 66: // Kod dla strzałki w dół
                            selectedIndex = (selectedIndex + 1) % menuOptions.length;
                            System.out.println("Strzałka w dół, indeks: " + selectedIndex); // Debug
                            break;
                    }
                }
            } else if (input == 10 || input == 13) { // Enter
                System.out.println("Wybrano opcję: " + selectedIndex); // Debug: Wyświetl wybraną opcję
                controller.selectOption(selectedIndex); // Wywołanie akcji dla wybranej opcji
            } else if (input == 27) { // ESC
                System.out.println("Powrót do menu głównego");
                return; // Wyjście z pętli i zakończenie działania
            }
        }
    }



    public void displayBooks(List<Book> books) throws IOException {
        System.out.println("=== Lista Książek ===");
        for (Book book : books) {
            System.out.println("Tytuł: " + book.getTitle() +
                    ", Autor: " + book.getAuthor() +
                    ", Gatunek: " + book.getGenre() +
                    ", Ilość stron: " + book.getPageCount() +
                    ", Rok wydania: " + book.getYear() +
                    ", Opis: " + book.getDescription());
        }
        System.out.println("Naciśnij Enter, aby wrócić do menu.");
        System.in.read(); // Pauza do powrotu do menu
    }

    public void displayMessage(String message) throws IOException {
        System.out.println(message + "\nNaciśnij Enter, aby wrócić do menu.");
        System.in.read(); // Pauza do powrotu do menu
    }

    public void promptForInput(String prompt) {
        System.out.println(prompt);
    }
}
