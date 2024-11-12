package model;
import java.io.*;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books = new ArrayList<>();
    private String filePath;

    public BookRepository(String filePath) {
        this.filePath = filePath;
        loadBooksFromFile(filePath);
    }

    private void loadBooksFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length < 6) {  // Sprawdzenie, czy są wszystkie wymagane pola
                    System.out.println("Błąd formatu w linii: " + line);
                    continue;
                }

                String title = parts[0].split(":")[1].trim();
                String author = parts[1].split(":")[1].trim();
                String genre = parts[2].split(":")[1].trim();
                int pageCount = Integer.parseInt(parts[3].split(":")[1].trim());
                int year = Integer.parseInt(parts[4].split(":")[1].trim());
                String description = parts[5].split(":")[1].trim();

                books.add(new Book(title, author, genre, pageCount, year, description));
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania pliku: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Błąd formatu liczbowego w pliku: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
    }

    public void removeBook(Book book) {
        books.remove(book);
        saveBooksToFile();
    }


    public void sortBooksByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Książki zostały posortowane po tytule.");
    }

    public void sortBooksByAuthor() {
        books.sort(Comparator.comparing(Book::getAuthor));
        System.out.println("Książki zostały posortowane po autorze.");
    }

    public List<Book> filterByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write("Tytuł: " + book.getTitle() +
                        "; Autor: " + book.getAuthor() +
                        "; Gatunek: " + book.getGenre() +
                        "; Ilość stron: " + book.getPageCount() +
                        "; Rok wydania: " + book.getYear() +
                        "; Opis: " + book.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu do pliku: " + e.getMessage());
        }
    }
}
