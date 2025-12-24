import java.io.Serializable;

public class Podcast extends Item implements Serializable {
    public Podcast(String name,String author,String genre,int duration,int year){
        super(name,author,genre, duration, year, "Podcast");
    }

    @Override
    public String getInfo(){
        return "Podcast: " + name + " " + author + " " + genre + " " + duration + " " + year + " " + category;
    }
}
