import java.io.Serializable;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

public class Catalog implements Serializable {
    private List<Item> items = new ArrayList<>();
    private static final String CATALOG_FILE = "catalog.dat";
    private static final Logger logger = Logger.getLogger(Catalog.class.getName());

    public enum MainMenuOption { ADD_ITEM(1), REMOVE_ITEM(2), DISPLAY_ALL(3), PLAYLIST(4), SEARCH(5), FILTER(6), SORT(7), FILE(8), EXIT(0);
        private final int value; MainMenuOption(int value) { this.value = value; } public int getValue() { return value; }
        public static MainMenuOption fromInt(int input) { for(MainMenuOption o : values()) if(o.getValue()==input) return o; return null; }
    }

    public enum AddMenuOption { SONG(1), AUDIOBOOK(2), PODCAST(3), ALBUM(4), EXIT(0);
        private final int value; AddMenuOption(int value) { this.value=value; } public int getValue(){return value;}
        public static AddMenuOption fromInt(int input){for(AddMenuOption o:values()) if(o.getValue()==input) return o; return null;}
    }

    public enum SearchMenuOption { NAME(1), AUTHOR(2), GENRE(3), CATEGORY(4), YEAR(5), EXIT(0);
        private final int value; SearchMenuOption(int value){this.value=value;} public int getValue(){return value;}
        public static SearchMenuOption fromInt(int input){for(SearchMenuOption o:values()) if(o.getValue()==input) return o; return null;}
    }

    public enum FilterMenuOption { AUTHOR(1), GENRE(2), CATEGORY(3), YEAR(4), EXIT(0);
        private final int value; FilterMenuOption(int value){this.value=value;} public int getValue(){return value;}
        public static FilterMenuOption fromInt(int input){for(FilterMenuOption o:values()) if(o.getValue()==input) return o; return null;}
    }

    public enum PlaylistMenuOption { CREATE(1), ADD_ITEM(2), REMOVE_ITEM(3), DISPLAY(4), EXIT(0);
        private final int value; PlaylistMenuOption(int value){this.value=value;} public int getValue(){return value;}
        public static PlaylistMenuOption fromInt(int input){for(PlaylistMenuOption o:values()) if(o.getValue()==input) return o; return null;}
    }

    public enum FileMenuOption { SAVE_CATALOG(1), SAVE_PLAYLIST(2), LOAD_CATALOG(3), LOAD_PLAYLIST(4), EXIT(0);
        private final int value; FileMenuOption(int value){this.value=value;} public int getValue(){return value;}
        public static FileMenuOption fromInt(int input){for(FileMenuOption o:values()) if(o.getValue()==input) return o; return null;}
    }


    public void addItem(Item i) {
        if(!items.contains(i)) {
            items.add(i);
            logger.info("Added item: " + i.getName() + " (" + i.getCategory() + ")");
        } else {
            logger.warning("Item already exists: " + i.getName());
        }
    }

    public boolean removeItem(String name) {
        boolean removed = items.removeIf(i -> i.getName().equalsIgnoreCase(name));
        for(Item item:items) if(item instanceof Playlist) ((Playlist)item).remove(name);

        if(removed) logger.info("Removed item: " + name);
        else logger.warning("Attempted to remove non-existing item: " + name);
        return removed;
    }

    public void sortTitle() {
        items.sort(Comparator.comparing(Item::getName));
        logger.info("Catalog sorted by title.");
    }

    public List<Item> getAll() { return items; }

    public Playlist getPlaylistByName(String name) {
        for(Item i:items) if(i instanceof Playlist && i.getName().equalsIgnoreCase(name)) return (Playlist)i;
        return null;
    }


    public void addItemMenu(Scanner sc) {
        while(true) {
            System.out.println("Add menu: 1-Song 2-Audiobook 3-Podcast 4-Album 0-Exit");
            int choice = Validation.allowZero(sc, "Enter option: ");
            AddMenuOption option = AddMenuOption.fromInt(choice);
            if(option == null) { System.out.println("Invalid option."); continue; }

            switch(option) {
                case SONG -> addSong(sc);
                case AUDIOBOOK -> addAudioBook(sc);
                case PODCAST -> addPodcast(sc);
                case ALBUM -> addAlbum(sc);
                case EXIT -> { return; }
            }
        }
    }

    private void addSong(Scanner sc){
        String name = Validation.emptyString(sc,"Name: ");
        String author = Validation.emptyString(sc,"Author: ");
        String genre = Validation.emptyString(sc,"Genre: ");
        int duration = Validation.positiveInt(sc,"Duration (sec): ");
        int year = Validation.correctYear(sc,"Year: ");
        Song s = new Song(name,author,genre,duration,year);
        addItem(s); System.out.println("Added: "+s.getInfo());
    }

    private void addAudioBook(Scanner sc){
        String name = Validation.emptyString(sc,"Name: ");
        String author = Validation.emptyString(sc,"Author: ");
        String genre = Validation.emptyString(sc,"Genre: ");
        int duration = Validation.positiveInt(sc,"Duration (sec): ");
        int year = Validation.correctYear(sc,"Year: ");
        AudioBook ab = new AudioBook(name,author,genre,duration,year);
        addItem(ab); System.out.println("Added: "+ab.getInfo());
    }

    private void addPodcast(Scanner sc){
        String name = Validation.emptyString(sc,"Name: ");
        String author = Validation.emptyString(sc,"Author: ");
        String genre = Validation.emptyString(sc,"Genre: ");
        int duration = Validation.positiveInt(sc,"Duration (sec): ");
        int year = Validation.correctYear(sc,"Year: ");
        Podcast p = new Podcast(name,author,genre,duration,year);
        addItem(p); System.out.println("Added: "+p.getInfo());
    }

    private void addAlbum(Scanner sc){
        String name = Validation.emptyString(sc,"Name: ");
        String author = Validation.emptyString(sc,"Author: ");
        String genre = Validation.emptyString(sc,"Genre: ");
        int duration = Validation.positiveInt(sc,"Duration (sec): ");
        int year = Validation.correctYear(sc,"Year: ");
        MusicAlbum ma = new MusicAlbum(name,author,genre,duration,year);
        addItem(ma); System.out.println("Added: "+ma.getInfo());
    }


    public void displayAll() {
        if(items.isEmpty()) System.out.println("Catalog empty.");
        else items.forEach(i->System.out.println(i.getInfo()));
    }


    public void removeItemMenu(Scanner sc){
        String name = Validation.emptyString(sc,"Enter item name to remove: ");
        boolean removed = removeItem(name);
        if(removed) System.out.println("Removed successfully.");
        else System.out.println("Item not found.");
    }


    public void searchMenu(Scanner sc){
        while(true){
            System.out.println("Search menu: 1-Name 2-Author 3-Genre 4-Category 5-Year 0-Exit");
            int choice = Validation.allowZero(sc,"Enter option: ");
            SearchMenuOption option = SearchMenuOption.fromInt(choice);
            if(option==null){ System.out.println("Invalid option."); continue; }
            if(option==SearchMenuOption.EXIT) return;

            List<Item> results = new ArrayList<>();
            switch(option){
                case NAME -> results = searchTitle(Validation.emptyString(sc,"Name: "));
                case AUTHOR -> results = searchAuthor(Validation.emptyString(sc,"Author: "));
                case GENRE -> results = searchGenre(Validation.emptyString(sc,"Genre: "));
                case CATEGORY -> results = searchCategory(Validation.emptyString(sc,"Category: "));
                case YEAR -> results = searchYear(Validation.correctYear(sc,"Year: "));
            }
            if(results.isEmpty()) System.out.println("No items found.");
            else results.forEach(i->System.out.println(i.getInfo()));
        }
    }


    public void filterMenu(Scanner sc){
        while(true){
            System.out.println("Filter menu: 1-Author 2-Genre 3-Category 4-Year 0-Exit");
            int choice = Validation.allowZero(sc,"Enter option: ");
            FilterMenuOption option = FilterMenuOption.fromInt(choice);
            if(option==null){ System.out.println("Invalid option."); continue; }
            if(option==FilterMenuOption.EXIT) return;

            List<Item> results = new ArrayList<>();
            switch(option){
                case AUTHOR -> results = filterAuthor(Validation.emptyString(sc,"Author: "));
                case GENRE -> results = filterGenre(Validation.emptyString(sc,"Genre: "));
                case CATEGORY -> results = filterCategory(Validation.emptyString(sc,"Category: "));
                case YEAR -> results = filterYear(Validation.correctYear(sc,"Year: "));
            }
            if(results.isEmpty()) System.out.println("No items found.");
            else results.forEach(i->System.out.println(i.getInfo()));
        }
    }

    public List<Item> searchTitle(String title){ return items.stream().filter(i->i.getName().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList()); }
    public List<Item> searchAuthor(String author){ return items.stream().filter(i->i.getAuthor().toLowerCase().contains(author.toLowerCase())).collect(Collectors.toList()); }
    public List<Item> searchGenre(String genre){ return items.stream().filter(i->i.getGenre().toLowerCase().contains(genre.toLowerCase())).collect(Collectors.toList()); }
    public List<Item> searchCategory(String category){ return items.stream().filter(i->i.getCategory().toLowerCase().contains(category.toLowerCase())).collect(Collectors.toList()); }
    public List<Item> searchYear(int year){ return items.stream().filter(i->i.getYear()==year).collect(Collectors.toList()); }

    public List<Item> filterAuthor(String author){ return items.stream().filter(i->i.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList()); }
    public List<Item> filterGenre(String genre){ return items.stream().filter(i->i.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList()); }
    public List<Item> filterCategory(String category){ return items.stream().filter(i->i.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList()); }
    public List<Item> filterYear(int year){ return items.stream().filter(i->i.getYear()==year).collect(Collectors.toList()); }


    public void playlistMenu(Scanner sc){
        while(true){
            System.out.println("Playlist menu: 1-Create 2-Add item 3-Remove item 4-Display 0-Exit");
            int choice = Validation.allowZero(sc,"Enter option: ");
            PlaylistMenuOption option = PlaylistMenuOption.fromInt(choice);
            if(option==null){ System.out.println("Invalid option."); continue; }
            if(option==PlaylistMenuOption.EXIT) return;

            switch(option){
                case CREATE -> {
                    String name = Validation.emptyString(sc,"Playlist name: ");
                    if(getPlaylistByName(name)==null) { addItem(new Playlist(name)); logger.info("Playlist created: "+name); System.out.println("Created."); }
                    else { logger.warning("Attempted to create existing playlist: "+name); System.out.println("Playlist exists."); }
                }
                case ADD_ITEM -> {
                    String pname = Validation.emptyString(sc,"Playlist name: ");
                    Playlist pl = getPlaylistByName(pname);
                    if(pl==null){ System.out.println("Playlist not found."); break; }
                    String iname = Validation.emptyString(sc,"Item name: ");
                    Item i = getAll().stream().filter(it->it.getName().equalsIgnoreCase(iname)).findFirst().orElse(null);
                    if(i!=null){ pl.addItem(i); logger.info("Added item "+iname+" to playlist "+pname); System.out.println("Item added."); }
                    else { logger.warning("Item "+iname+" not found for playlist "+pname); System.out.println("Item not found."); }
                }
                case REMOVE_ITEM -> {
                    String pname = Validation.emptyString(sc,"Playlist name: ");
                    Playlist pl = getPlaylistByName(pname);
                    if(pl==null){ System.out.println("Playlist not found."); break; }
                    String iname = Validation.emptyString(sc,"Item name: ");
                    pl.remove(iname); logger.info("Removed item "+iname+" from playlist "+pname); System.out.println("Item removed.");
                }
                case DISPLAY -> {
                    String pname = Validation.emptyString(sc,"Playlist name: ");
                    Playlist pl = getPlaylistByName(pname);
                    if(pl==null){ System.out.println("Playlist not found."); logger.warning("Attempted to display non-existing playlist "+pname); }
                    else { System.out.println(pl.getInfo()); logger.info("Displayed playlist "+pname); }
                }
            }
        }
    }


    public void fileMenu(Scanner sc){
        while(true){
            System.out.println("File menu: 1-Save catalog 2-Save playlist 3-Load catalog 4-Load playlist 0-Exit");
            int choice = Validation.allowZero(sc,"Enter option: ");
            FileMenuOption option = FileMenuOption.fromInt(choice);
            if(option==null){ System.out.println("Invalid option."); continue; }
            if(option==FileMenuOption.EXIT) return;

            switch(option){
                case SAVE_CATALOG -> {
                    try{ FileManager.catalogSave(this,CATALOG_FILE); logger.info("Catalog saved."); System.out.println("Saved catalog."); }
                    catch(Exception e){ logger.log(Level.SEVERE,"Error saving catalog",e); System.out.println("Error saving.");}
                }
                case SAVE_PLAYLIST -> {
                    String pname = Validation.emptyString(sc,"Playlist name: ");
                    Playlist pl = getPlaylistByName(pname);
                    if(pl!=null){
                        try{ FileManager.playlistSave(pl,pl.getName()+".dat"); logger.info("Playlist saved: "+pname); System.out.println("Saved playlist."); }
                        catch(Exception e){ logger.log(Level.SEVERE,"Error saving playlist "+pname,e); System.out.println("Error.");}
                    } else { logger.warning("Attempted to save non-existing playlist: "+pname); System.out.println("Playlist not found."); }
                }
                case LOAD_CATALOG -> {
                    try{ Catalog loaded = FileManager.catalogRead(CATALOG_FILE); this.items=loaded.items; logger.info("Catalog loaded."); System.out.println("Loaded catalog."); }
                    catch(Exception e){ logger.log(Level.SEVERE,"Error loading catalog",e); System.out.println("Error loading."); }
                }
                case LOAD_PLAYLIST -> {
                    String file = Validation.emptyString(sc,"Playlist file name: ");
                    try{ Playlist pl = FileManager.playlistRead(file); addItem(pl); logger.info("Playlist loaded from file: "+file); System.out.println("Loaded playlist."); }
                    catch(Exception e){ logger.log(Level.SEVERE,"Error loading playlist from file "+file,e); System.out.println("Error."); }
                }
            }
        }
    }
}
