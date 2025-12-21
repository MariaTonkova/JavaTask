import java.io.Serializable;

public class MusicAlbum extends Item implements Serializable {
    public MusicAlbum(String name, String author, String genre, int duration, int year) {
        super(name, author, genre, duration, year, "Music Album");
    }
    @Override
    public String getInfo(){
        return "Music Album: " + name + " " + author + " " + genre + " " + duration + " " + year + " " + category;
    }
}
