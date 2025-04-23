
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Jatek
{
    
    private Palya jatekter;

    private ArrayList<Gombasz> gombaszok;
    private ArrayList<Bogarasz> bogaraszok;

    /*
     * Konstruktor, amely l�trehozza a j�t�kteret �s a karakterek list�j�t.
     * 
     * @param palyaMeret A p�lya m�rete.
     */
    public Jatek(int palyaMeret)
    {
      
      gombaszok = new ArrayList<Gombasz>();
      bogaraszok = new ArrayList<Bogarasz>();
      jatekter = new Palya(palyaMeret);
    }
    
    /**
     * Elind�tja a j�t�kot �s kezeli a f� j�t�kmenetet.
     */
    // public void jatekIndit()
    // {
    //     boolean gameRunning = true;
    //     int turnCount = 0;

    //     System.out.println("Hány gombász van?");   
    //     int gombaszszam = InputHandler.getScanner().nextInt();

                
    //     for(int i=0;i<gombaszszam;i++){
    //         Jatekos jatekos=null;
    //         jatekos = new Gombasz(jatekter.getPalya());

    //         karakterek.add(jatekos);
    //     }

    //     System.out.println("Hány bogarasz van?");   
    //     int bogaraszszam = InputHandler.getScanner().nextInt();

    //     for(int i=0;i<bogaraszszam;i++){
    //         Jatekos jatekos=null;
    //         jatekos = new Bogarasz();
 

    //         karakterek.add(jatekos);
    //     }

    //     jatekter = new Palya(gombaszszam + 10);
 
    // }

    public void gombaszHozzaad(Gombasz g) {
        g.setNev("Gombasz" + (gombaszok.size()));
        gombaszok.add(g);
    }

    public void bogaraszHozzaad(Bogarasz b) {
        b.setNev("Bogarasz" + (bogaraszok.size()));
        bogaraszok.add(b);
    }

     /**
     * Szerializálással betölti az adatokat egy fájlból, beleértve a játékteret és a játékosokat.
     * 
     * @param filePath A fájl elérési útja, ahonnan a játék állapota betöltésre kerül.
     */
    public void betolt(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            jatekter = (Palya) ois.readObject();
        
            gombaszok = (ArrayList<Gombasz>) ois.readObject();
            bogaraszok = (ArrayList<Bogarasz>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

     /**
     * Szerializálással elmenti az adatokat egy fájlba, beleértve a játékteret és a játékosokat.
     * 
     * @param filePath A fájl elérési útja, ahová a játék állapota mentésre kerül.
     */
    public void ment(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(jatekter);
        
            oos.writeObject(gombaszok);
            oos.writeObject(bogaraszok);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //hibakezelés
    public Bogarasz bogaraszFromString(String bogarasz) {
        return bogaraszok.get(Integer.parseInt(bogarasz.substring(7)));
    }

    public Bogar bogarFromString(Bogarasz bogarasz, String bogar) {
        return bogarasz.getBogarak().get(Integer.parseInt(bogar.substring(4)));
    }

    public Tekton tektonFromString(String tekton) {
        return jatekter.getPalya().get(Integer.parseInt(tekton.substring(5)));
    }

    public Gombasz gombaszFromString(String gombasz) {
        return gombaszok.get(Integer.parseInt(gombasz.substring(6)));
    }

    public Fonal fonalFromString(String fonal, Tekton tekton) {
        return tekton.getOsszekoto().get(Integer.parseInt(fonal.substring(4)));
    }

    public void lepes(String bogarasz, String bogar, String hova) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tektonObj = tektonFromString(hova);
        bogaraszObj.lep(bogarObj,tektonObj);
    }

    public void evesSporat(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        bogaraszObj.eves(bogarObj);
    }

    public void evesBogarat(String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        tektonObj.fonalElpusztit();
    }

    public void ragas(String bogarasz, String bogar, String fonal) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tekton = bogarObj.getHelyzet();
        Fonal fonalObj = fonalFromString(fonal, tekton);
        bogaraszObj.ragas(bogarObj, fonalObj);
    }
   
}