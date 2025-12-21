import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Catalog implements Serializable {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item i) {
        if(!items.contains(i)) {
            items.add(i);
        }
    }

    public boolean removeItem(String name) {
        Iterator<Item> it = items.iterator();
        boolean removed = false;

        while (it.hasNext()) {
            Item i = it.next();
            if (i.getName().equalsIgnoreCase(name)) {
                it.remove();
                removed = true;
            }
        }

        for (Item item : items) {
            if (item instanceof Playlist) {
                ((Playlist) item).remove(name);
            }
        }

        return removed;
    }

    public List <Item> searchTitle(String title) {
        return items.stream().filter(i -> i.getName().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
    }

    public List <Item> searchAuthor(String author){
        return items.stream().filter(i -> i.getAuthor().toLowerCase().contains(author.toLowerCase())).collect(Collectors.toList());
    }

    public List<Item> searchGenre(String genre){
        return items.stream().filter(i -> i.getGenre().toLowerCase().contains(genre.toLowerCase())).collect(Collectors.toList());
    }

    public List<Item> searchCategory(String category){
        return items.stream().filter(i -> i.getCategory().toLowerCase().contains(category.toLowerCase())).collect(Collectors.toList());
    }

    public List<Item> searchYear(int year){
        return items.stream().filter(i -> i.getYear() == year).collect(Collectors.toList());
    }

    public List<Item> filterAuthor(String author){
        return items.stream().filter(i -> i.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }

    public List<Item> filterGenre(String genre){
        return items.stream().filter(i -> i.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
    }

    public List<Item> filterYear(int year){
        return items.stream().filter(i -> i.getYear() == year).collect(Collectors.toList());
    }

    public List <Item> filterCategory(String category){
        return items.stream().filter(i -> i.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public void sortTitle (){
        items.sort(Comparator.comparing(Item::getName));
    }

    public List<Item> getAll(){
        return items;
    }
}
