
import java.util.ArrayList;

public class Jatek
{
    
    private Palya jatekter;
    private ArrayList<Jatekos> karakterek;

    /**
     * Konstruktor, amely létrehozza a játékteret és a karakterek listáját.
     * 
     * @param palyaMeret A pálya mérete.
     */
    Jatek(int palyaMeret)
    {
      karakterek = new ArrayList<Jatekos>();
      jatekter = new Palya(palyaMeret);
    }
    
    /**
     * Elindítja a játékot és kezeli a fõ játékmenetet.
     */
    public void jatekIndit()
    {
       
    }

    /**
     * Felvesz egy új játékost a karakterek listájába.
     */
    public void jatekosHozzaad()
    {
        szkeleton.logMethodEntry(this, "jatekosHozzaad");
        Jatekos karakter = new Jatekos();
        karakterek.add(karakter);
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Szerializálással betölti az adatokat egy fájlból, beleértve a játékteret és a játékosokat.
     * 
     * @param filePath A fájl elérési útvonala, ahonnan a játék állapota betöltésre kerül.
     */
    public void betoltes(String filePath)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            jatekter = (Palya) ois.readObject();
            karakterek = (ArrayList<Jatekos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Szerializálással elmenti az adatokat egy fájlba, beleértve a játékteret és a játékosokat.
     * 
     * @param filepath A fájl elérési útvonala, ahová a játék állapota mentésre kerül.
     */
    public void mentes(String filepath)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(jatekter);
            oos.writeObject(karakterek);
        } catch (IOException e) {
            e.printStackTrace();
    }
}