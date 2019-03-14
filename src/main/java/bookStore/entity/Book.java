package bookStore.entity;

import javax.persistence.*;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String genre;
    private String title;
    private int price;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Author author;

    public Book(String genre, String title, int price, int quantity, Author author) {
        this.genre = genre;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.author = author;
    }

    private Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return id + ";" + title + ";" + author.getName() + ";" + genre + ";" + price + ";" + quantity;
    }
}
