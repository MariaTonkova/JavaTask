import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Main menu:");
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
            int userInput = Validation.allowZero(sc, "Enter option: ");

            Catalog.MainMenuOption option = Catalog.MainMenuOption.fromInt(userInput);

            if(option == null) {
                System.out.println("Invalid option.");
                continue;
            }

            switch(option) {
                case ADD_ITEM -> catalog.addItemMenu(sc);
                case REMOVE_ITEM -> catalog.removeItemMenu(sc);
                case DISPLAY_ALL -> catalog.displayAll();
                case PLAYLIST -> catalog.playlistMenu(sc);
                case SEARCH -> catalog.searchMenu(sc);
                case FILTER -> catalog.filterMenu(sc);
                case SORT -> {
                    catalog.sortTitle();
                    catalog.displayAll();
                    System.out.println("Catalog sorted!");
                }
                case FILE -> catalog.fileMenu(sc);
                case EXIT -> {
                    System.out.println("Exiting program.");
                    sc.close();
                    return;
                }
            }
        }
    }
}
