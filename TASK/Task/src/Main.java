import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println(" ");
            System.out.println("1: Add a new item to the catalog.");
            System.out.println("2: Remove an item from the catalog.");
            System.out.println("3: Display all items in the catalog.");
            System.out.println("4: Playlist.");
            System.out.println("5: Search for items in the catalog.");
            System.out.println("6: Filter.");
            System.out.println("7: Sort the items by name.(A - Z).");
            System.out.println("8: File (Write/Read).");
            System.out.println("0: Exit");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    while (true) {
                        System.out.println("What item do you want to add?:");
                        System.out.println("1: Song");
                        System.out.println("2: Audiobook");
                        System.out.println("3: Podcast");
                        System.out.println("4: Music Album");
                        System.out.println("0: Exit this menu");
                        int opt = sc.nextInt();
                        sc.nextLine();

                        switch (opt) {
                            case 1:
                                System.out.println("Enter the name of the song:");
                                String title = sc.nextLine();
                                System.out.println("Enter the author:");
                                String author = sc.nextLine();
                                System.out.println("Enter the genre:");
                                String genre = sc.nextLine();
                                System.out.println("Enter the duration in seconds:");
                                int duration = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the year:");
                                int year = sc.nextInt();
                                sc.nextLine();
                                Song song = new Song(title, author, genre, duration, year);
                                catalog.addItem(song);
                                System.out.println("Song added successfully.");
                                System.out.println(song.getInfo());
                                break;

                            case 2:
                                System.out.println("Enter the name of the audiobook:");
                                String titleA = sc.nextLine();
                                System.out.println("Enter the author:");
                                String authorA = sc.nextLine();
                                System.out.println("Enter the genre:");
                                String genreA = sc.nextLine();
                                System.out.println("Enter the duration in seconds:");
                                int durationA = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the year:");
                                int yearA = sc.nextInt();
                                sc.nextLine();
                                AudioBook audiobook = new AudioBook(titleA, authorA, genreA, durationA, yearA);
                                catalog.addItem(audiobook);
                                System.out.println("Audiobook added successfully.");
                                System.out.println(audiobook.getInfo());
                                break;

                            case 3:
                                System.out.println("Enter the name of the podcast:");
                                String titleP = sc.nextLine();
                                System.out.println("Enter the author:");
                                String authorP = sc.nextLine();
                                System.out.println("Enter the genre:");
                                String genreP = sc.nextLine();
                                System.out.println("Enter the duration in seconds:");
                                int durationP = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the year:");
                                int yearP = sc.nextInt();
                                sc.nextLine();
                                Podcast podcast = new Podcast(titleP, authorP, genreP, durationP, yearP);
                                catalog.addItem(podcast);
                                System.out.println("Podcast added successfully.");
                                System.out.println(podcast.getInfo());
                                break;

                            case 4:
                                System.out.println("Enter the name of the album:");
                                String nameM = sc.nextLine();
                                System.out.println("Enter the author:");
                                String authorM = sc.nextLine();
                                System.out.println("Enter the genre:");
                                String genreM = sc.nextLine();
                                System.out.println("Enter the duration in seconds:");
                                int durationM = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the year:");
                                int yearM = sc.nextInt();
                                sc.nextLine();
                                MusicAlbum album = new MusicAlbum(nameM, authorM, genreM, durationM, yearM);
                                catalog.addItem(album);
                                System.out.println("Album added successfully.");
                                System.out.println(album.getInfo());
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }

                        if (opt == 0) break;
                    }
                    break;

                case 2:
                    System.out.println("Enter the name of the item that you want to delete:");
                    String name = sc.nextLine();
                    boolean removed = catalog.removeItem(name);
                    if (removed) {
                        System.out.println("Item deleted successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                    System.out.println("Displaying all items in the catalog:");
                    for (Item item : catalog.getAll()) {
                        System.out.println(item.getInfo());
                    }
                    break;

                case 4:
                    while (true) {
                        System.out.println("Playlist menu:");
                        System.out.println("1: Create new playlist");
                        System.out.println("2: Add item to existing playlist");
                        System.out.println("3: Remove item from existing playlist");
                        System.out.println("4: Display playlist information");
                        System.out.println("0: Exit this menu");
                        int pOption = sc.nextInt();
                        sc.nextLine();

                        switch (pOption) {
                            case 1: // Create playlist
                                System.out.println("Enter playlist name:");
                                String playlistName = sc.nextLine();
                                boolean exists = false;
                                for (Item item : catalog.getAll()) {
                                    if (item instanceof Playlist && item.getName().equalsIgnoreCase(playlistName)) {
                                        exists = true;
                                        break;
                                    }
                                }
                                if (!exists) {
                                    Playlist playlist = new Playlist(playlistName);
                                    catalog.addItem(playlist);
                                    System.out.println("Playlist added successfully.");
                                } else {
                                    System.out.println("Playlist already exists.");
                                }
                                break;

                            case 2:
                                System.out.println("Enter the name of the playlist:");
                                String playlistNameAdd = sc.nextLine();
                                Playlist selectedAdd = null;
                                for (Item item : catalog.getAll()) {
                                    if (item instanceof Playlist && item.getName().equalsIgnoreCase(playlistNameAdd)) {
                                        selectedAdd = (Playlist) item;
                                        break;
                                    }
                                }
                                if (selectedAdd == null) {
                                    System.out.println("Playlist does not exist.");
                                    break;
                                }
                                System.out.println("Enter the name of the item to add:");
                                String itemNameAdd = sc.nextLine();
                                boolean itemFound = false;
                                for (Item i : catalog.getAll()) {
                                    if (i.getName().equalsIgnoreCase(itemNameAdd)) {
                                        selectedAdd.addItem(i);
                                        System.out.println("Item added successfully.");
                                        itemFound = true;
                                        break;
                                    }
                                }
                                if (!itemFound) System.out.println("Item not found in catalog.");
                                break;

                            case 3:
                                System.out.println("Enter the name of the playlist:");
                                String playlistNameRemove = sc.nextLine();
                                Playlist selectedRemove = null;
                                for (Item item : catalog.getAll()) {
                                    if (item instanceof Playlist && item.getName().equalsIgnoreCase(playlistNameRemove)) {
                                        selectedRemove = (Playlist) item;
                                        break;
                                    }
                                }
                                if (selectedRemove == null) {
                                    System.out.println("Playlist does not exist.");
                                    break;
                                }
                                System.out.println("Enter the name of the item to remove:");
                                String itemNameRemove = sc.nextLine();
                                selectedRemove.remove(itemNameRemove);
                                System.out.println("Item deleted successfully.");
                                break;

                            case 4:
                                System.out.println("Enter the playlist name:");
                                String playlistNameDisplay = sc.nextLine();
                                boolean foundPlaylist = false;
                                for (Item item : catalog.getAll()) {
                                    if (item instanceof Playlist && item.getName().equalsIgnoreCase(playlistNameDisplay)) {
                                        System.out.println(((Playlist) item).getInfo());
                                        foundPlaylist = true;
                                        break;
                                    }
                                }
                                if (!foundPlaylist) System.out.println("Playlist does not exist.");
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }

                        if (pOption == 0) break;
                    }
                    break;

                case 5:
                    while (true) {
                        System.out.println("Search by:");
                        System.out.println("1: Name");
                        System.out.println("2: Author");
                        System.out.println("3: Genre");
                        System.out.println("4: Category");
                        System.out.println("5: Year");
                        System.out.println("0: Exit");
                        int sOption = sc.nextInt();
                        sc.nextLine();

                        if (sOption == 0) break;

                        List<Item> results = new ArrayList<>();

                        switch (sOption) {
                            case 1:
                                System.out.println("Enter name:");
                                String nameSearch = sc.nextLine();
                                results = catalog.searchTitle(nameSearch);
                                break;
                            case 2:
                                System.out.println("Enter author:");
                                String authorSearch = sc.nextLine();
                                results = catalog.searchAuthor(authorSearch);
                                break;
                            case 3:
                                System.out.println("Enter genre:");
                                String genreSearch = sc.nextLine();
                                results = catalog.searchGenre(genreSearch);
                                break;
                            case 4:
                                System.out.println("Enter category:");
                                String categorySearch = sc.nextLine();
                                results = catalog.searchCategory(categorySearch);
                                break;
                            case 5:
                                System.out.println("Enter year:");
                                int yearSearch = sc.nextInt();
                                sc.nextLine();
                                results = catalog.searchYear(yearSearch);
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }

                        if (!results.isEmpty()) {
                            System.out.println("Found items:");
                            for (Item item : results) {
                                System.out.println(item.getInfo());
                            }
                        } else {
                            System.out.println("No items found.");
                        }
                    }
                    break;

                case 6:
                    while (true) {
                        System.out.println("Filter by:");
                        System.out.println("1: Author");
                        System.out.println("2: Genre");
                        System.out.println("3: Category");
                        System.out.println("4: Year");
                        System.out.println("0: Exit");
                        int fOption = sc.nextInt();
                        sc.nextLine();
                        if (fOption == 0) break;

                        List<Item> results = new ArrayList<>();
                        switch (fOption) {
                            case 1:
                                System.out.println("Enter author:");
                                String authorF = sc.nextLine();
                                results = catalog.filterAuthor(authorF);
                                break;
                            case 2:
                                System.out.println("Enter genre:");
                                String genreF = sc.nextLine();
                                results = catalog.filterGenre(genreF);
                                break;
                            case 3:
                                System.out.println("Enter category:");
                                String categoryF = sc.nextLine();
                                results = catalog.filterCategory(categoryF);
                                break;
                            case 4:
                                System.out.println("Enter year:");
                                int yearF = sc.nextInt();
                                sc.nextLine();
                                results = catalog.filterYear(yearF);
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }

                        if (!results.isEmpty()) {
                            System.out.println("Filtered items:");
                            for (Item item : results) {
                                System.out.println(item.getInfo());
                            }
                        } else {
                            System.out.println("No items found.");
                        }
                    }
                    break;

                case 7:
                    catalog.sortTitle();
                    System.out.println("Catalog sorted (A-Z):");
                    for (Item item : catalog.getAll()) {
                        System.out.println(item.getInfo());
                    }
                    break;

                case 8:
                    while (true) {
                        System.out.println("Choose an option:");
                        System.out.println("1: Save catalog in file");
                        System.out.println("2: Save playlist in file");
                        System.out.println("3: Load catalog from file");
                        System.out.println("4: Load playlist from file");
                        System.out.println("0: Exit");
                        int fOption = sc.nextInt();
                        sc.nextLine();
                        if (fOption == 0) break;

                        switch (fOption) {
                            case 1:
                                try {
                                    FileManager.catalogSave(catalog, "catalog.dat");
                                    System.out.println("Catalog saved successfully.");
                                } catch (IOException e) {
                                    System.out.println("Error saving catalog.");
                                }
                                break;
                            case 2:
                                System.out.println("Enter playlist name:");
                                String playlistNameSave = sc.nextLine();
                                Playlist playlistSave = null;
                                for (Item item : catalog.getAll()) {
                                    if (item instanceof Playlist && item.getName().equalsIgnoreCase(playlistNameSave)) {
                                        playlistSave = (Playlist) item;
                                        break;
                                    }
                                }
                                if (playlistSave != null) {
                                    try {
                                        FileManager.playlistSave(playlistSave, playlistNameSave + ".dat");
                                        System.out.println("Playlist saved successfully.");
                                    } catch (IOException e) {
                                        System.out.println("Error saving playlist.");
                                    }
                                } else {
                                    System.out.println("Playlist not found.");
                                }
                                break;
                            case 3:
                                try {
                                    catalog = FileManager.catalogRead("catalog.dat");
                                    System.out.println("Catalog loaded successfully.");
                                } catch (IOException | ClassNotFoundException e) {
                                    System.out.println("Error loading catalog.");
                                }
                                break;
                            case 4:
                                System.out.println("Enter playlist file name:");
                                String playlistFile = sc.nextLine();
                                try {
                                    Playlist loadedPlaylist = FileManager.playlistRead(playlistFile);
                                    catalog.addItem(loadedPlaylist);
                                    System.out.println("Playlist loaded successfully.");
                                } catch (IOException | ClassNotFoundException e) {
                                    System.out.println("Error loading playlist.");
                                }
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting the program...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}