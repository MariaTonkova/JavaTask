import java.io.Serializable;
import java.util.*;

public class Playlist extends Item implements Serializable {
    private List<Item> items = new ArrayList<>();

    public Playlist(String name) {
        super(name, "Playlist");
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public void remove(String title){
        items.removeIf(i -> i.getName().equalsIgnoreCase(title));
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(getName()).append("\nItems:\n");
        for (Item i : items) {
            sb.append(" - ").append(i.getInfo()).append("\n");
        }
        return sb.toString();
    }
}
