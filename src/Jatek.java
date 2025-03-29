
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Jatek
{
    
    private Palya jatekter;
    private ArrayList<Jatekos> karakterek;

    /*
     * Konstruktor, amely l�trehozza a j�t�kteret �s a karakterek list�j�t.
     * 
     * @param palyaMeret A p�lya m�rete.
     */
    public Jatek(int palyaMeret)
    {
      karakterek = new ArrayList<Jatekos>();
      jatekter = new Palya(palyaMeret);
    }
    
    /**
     * Elind�tja a j�t�kot �s kezeli a f� j�t�kmenetet.
     */
    public void jatekIndit()
    {
        //szkeleton.logMethodEntry(this, "jatekIndit");
        boolean gameRunning = true;
        int turnCount = 0;

        System.out.println("Hány gombász van?");   
        int gombaszszam = InputHandler.getScanner().nextInt();

                
        for(int i=0;i<gombaszszam;i++){
            Jatekos jatekos=null;
            jatekos = new Gombasz(jatekter.getPalya());
            szkeleton.addToMap(jatekos, "gombasz" + (i+1));
            szkeleton.logMethodEntry(jatekos, "Gombasz()");
            szkeleton.logMethodExit(jatekos, "");
            karakterek.add(jatekos);
        }

        System.out.println("Hány bogarasz van?");   
        int bogaraszszam = InputHandler.getScanner().nextInt();

        for(int i=0;i<bogaraszszam;i++){
            Jatekos jatekos=null;
            jatekos = new Bogarasz();
            szkeleton.addToMap(jatekos, "bogarasz" + (i+1));
            szkeleton.logMethodEntry(jatekos, "Bogarasz");
            szkeleton.logMethodExit(jatekos, "");
            karakterek.add(jatekos);
        }

        jatekter = new Palya(gombaszszam + 10);
        szkeleton.addToMap(jatekter, "jatekter");
        szkeleton.logMethodEntry(jatekter, "Palya");
        szkeleton.logMethodExit(jatekter, "");
       

        /*while (gameRunning) {
            for (Jatekos karakter : karakterek) {
                karakter.round();
                jatekter.tores();
            }
            turnCount++;  // kilepesi feltetel?
        }*/
        //szkeleton.logMethodExit(this, "");
    }

    /**
     * Felvesz egy �j j�t�kost a karakterek list�j�ba.
     */
    public void jatekosHozzaad(Jatekos karakter)
    {
        szkeleton.logMethodEntry(this, "jatekosHozzaad");
       // Jatekos karakter = new Jatekos();
        karakterek.add(karakter);
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Szerializ�l�ssal bet�lti az adatokat egy f�jlb�l, bele�rtve a j�t�kteret �s a j�t�kosokat.
     * 
     * @param filePath A f�jl el�r�si �tvonala, ahonnan a j�t�k �llapota bet�lt�sre ker�l.
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
     * Szerializ�l�ssal elmenti az adatokat egy f�jlba, bele�rtve a j�t�kteret �s a j�t�kosokat.
     * 
     * @param filepath A f�jl el�r�si �tvonala, ahov� a j�t�k �llapota ment�sre ker�l.
     */
    public void mentes(String filePath)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(jatekter);
            oos.writeObject(karakterek);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}