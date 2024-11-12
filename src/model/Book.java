package model;

public class Book {
    private String title;          // Tytuł książki
    private String author;         // Autor książki
    private String genre;          // Gatunek książki
    private int pageCount;         // Ilość stron
    private int year;              // Rok wydania
    private String description;    // Opis książki

    // Konstruktor
    public Book(String title, String author, String genre, int pageCount, int year, String description) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount > 0 ? pageCount : 0;  // Ustawienie na 0, jeśli pageCount jest ujemne
        if (year >= 0 && year <= java.time.Year.now().getValue()) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Rok wydania musi być dodatni i nie większy niż obecny rok.");
        }
        this.description = description;
    }

    // Gettery i settery dla wszystkich pól
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        if (pageCount < 0) {
            throw new IllegalArgumentException("Ilość stron nie może być ujemna.");
        }
        this.pageCount = pageCount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 0 && year <= java.time.Year.now().getValue()) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Rok wydania musi być dodatni i nie większy niż obecny rok.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tytuł: " + title +
                ", Autor: " + author +
                ", Gatunek: " + genre +
                ", Ilość stron: " + pageCount +
                ", Rok wydania: " + year +
                ", Opis: " + description;
    }
}
