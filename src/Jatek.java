
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    
    public Palya getJatekter() {
        return jatekter;
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

    public Gombatest gombatestFromString(String gombatest, Gombasz gombasz) {
        return gombasz.getTestek().get(Integer.parseInt(gombatest.substring(8)));
    }

    public Fonal fonalFromString(String fonal, Tekton tekton) {
        return tekton.getOsszekoto().get(Integer.parseInt(fonal.substring(4)));
    }

    public boolean lepes(String bogarasz, String bogar, String hova) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tektonObj = tektonFromString(hova);
        return bogaraszObj.lep(bogarObj,tektonObj);
    }

    public boolean evesSporat(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        return bogaraszObj.eves(bogarObj);
    }

    ///
    public void evesBogarat(String fonal, String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        Fonal fonalObj = fonalFromString(fonal, tektonObj);
        
    }

    public boolean ragas(String bogarasz, String bogar, String fonal) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tekton = bogarObj.getHelyzet();
        Fonal fonalObj = fonalFromString(fonal, tekton);
        return bogaraszObj.ragas(bogarObj, fonalObj);
    }

    public boolean fonalLerak(String gombasz, String gombatest, String t1, String t2) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Tekton t1Obj = tektonFromString(t1);
        Tekton t2Obj = tektonFromString(t2);
        return gombatestObj.elhelyez(t1Obj, t2Obj);
    }

    public boolean sporaSzor(String gombasz, String gombatest, String tekton) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Tekton tektonObj = tektonFromString(tekton);
        return gombatestObj.elszor(tektonObj);
    }

    public Set<Tekton> gombaHovaRakhat(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Set<Tekton> hovaLehetosegek = gombaszObj.hovaLehetosegek(gombatestObj.getHely());
        return hovaLehetosegek;
    }

    public Set<Tekton> gombaEler(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        return gombatestObj.dfs();
    }

    public Set<Tekton> gombaszEler(String gombasz) {
        Set<Tekton> acc = new HashSet<Tekton>();
        Gombasz gombaszObj = gombaszFromString(gombasz);
        for (Gombatest g : gombaszObj.getTestek()) {
            Set<Tekton> dfs = g.dfs();
            for (Tekton t : dfs) {
                acc.add(t);
            }
        }
        return acc;
    }

    public ArrayList<Tekton> fonallalOsszekotott(String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        return tektonObj.fonalKeres();
    }

    public Set<Tekton> gombaszHovaSzorhat(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        return gombatestObj.hovaSzorhat();
    }

    public ArrayList<Fonal> tektononFonal(String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        return tektonObj.getOsszekoto();
    }

    public Tekton bogarTekton(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        return bogarObj.getHelyzet();
    }

    // A palyaKor, hozzaad parancsok nem kellnek elvileg, megemeszt se

    public ArrayList<Gombasz> gombaszok() {
        return gombaszok;
    }

    public ArrayList<Bogarasz> bogaraszok() {
        return bogaraszok;
    }

    public Tekton helyzet(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        return bogarObj.getHelyzet();
    }

    public ArrayList<Gombatest> gombatestListazas(String gombasz) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        return gombaszObj.getTestek();
    }

    public ArrayList<Tekton> tektonSzomszedNincsFonal(String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        ArrayList<Tekton> acc = new ArrayList<>();
        for (Tekton t : tektonObj.getSzomszed()) {
            if (t == tektonObj) { // szomszédosak
                for (Fonal f : tektonObj.getOsszekoto()) {
                    if (!(f.getHova() == tektonObj)) { // de nem köti össze fonal
                        acc.add(t);
                    }
                }
            }
            
        }
        
        return acc;
    }

    public ArrayList<Bogar> benultBogar(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Tekton gombatestTektonja = gombatestObj.getHely();
        ArrayList<Tekton> fonallalEler = gombatestTektonja.fonalKeres();
        ArrayList<Bogar> acc = new ArrayList<>();

        for (Bogarasz b : bogaraszok) {
            for (Bogar bg : b.getBogarak()) {
                for (Tekton t : fonallalEler) {
                    if (t == bg.getHelyzet()) {
                        acc.add(bg);
                    }
                }
            }
        }

        return acc;
    }
}