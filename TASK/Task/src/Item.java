import java.io.Serializable;

public abstract class Item implements Serializable {
    protected String name;
    protected String author;
    protected String genre;
    protected int duration;
    protected int year;
    protected String category;

    public Item(String name, String author, String genre, int duration, int year, String category) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.duration = duration;
        this.year = year;
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
    public String getCategory() {
        return category;
    }

    public Item(String name, String category) {
        this.name = name;
        this.category = category;
        this.author = "";
        this.genre = "";
        this.duration = 0;
        this.year = 0;
    }

    public abstract String getInfo();
}
