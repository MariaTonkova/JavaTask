import java.io.Serializable;

public class AudioBook extends Item implements Serializable {
    public AudioBook(String name,String author,String genre, int duration,int year){
        super (name,author,genre,duration,year, "Audio book");
    }

    @Override
    public String getInfo(){
        return "Audiobook: " + name + " " + author + " " + genre + " " + duration + " " + year + " " + category;
    }
}