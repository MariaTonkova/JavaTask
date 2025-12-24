import java.io.*;

public class FileManager {
    public static void catalogSave(Catalog c, String fileName)throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(c);
        oos.close();
    }
    public static Catalog catalogRead(String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Catalog c = (Catalog) ois.readObject();
        ois.close();
        return c;
    }

    public static void playlistSave(Playlist p, String fileName)throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(p);
        oos.close();
    }

    public static Playlist playlistRead(String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Playlist p = (Playlist) ois.readObject();
        ois.close();
        return p;
    }
}
