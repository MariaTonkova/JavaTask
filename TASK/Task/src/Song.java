import java.io.Serializable;

public class Song extends Item implements Serializable {
    public Song(String name, String author, String genre, int duration, int year) {
        super(name, author, genre, duration, year, "Song");
    }
    @Override
    public String getInfo() {
        return "Song: "+ name + " " + author + " " + genre + " " + duration + " " + year + " " + category;
    }
}