
import java.util.ArrayList;

public class Jatek
{
    
    private Palya jatekter;
    private ArrayList<Jatekos> karakterek;

    //konstruktor
    Jatek(int palyaMeret)
    {
      karakterek = new ArrayList<Jatekos>();
      jatekter = new Palya(palyaMeret);
    }
    
    //Jatek inditas / gameloop
    public void jatekIndit()
    {
       
    }

    //felvesz egy uj jatekost
    public void jatekosHozzaad()
    {
        Jatekos karakter = new Jatekos();
        karakterek.add(karakter);
    }

    //betolti filebol a jatekallapotot
    public void betoltes(String filePath)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            jatekter = (Palya) ois.readObject();
            karakterek = (ArrayList<Jatekos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //kimenti fileba a jatek allapotot
    public void mentes(String filepath)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(jatekter);
            oos.writeObject(karakterek);
        } catch (IOException e) {
            e.printStackTrace();
    }
}