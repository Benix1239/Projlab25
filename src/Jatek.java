
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jatek {

    private Palya jatekter;

    private ArrayList<Gombasz> gombaszok;
    private ArrayList<Bogarasz> bogaraszok;

    private int jatekosIndex;

    /*
     * Konstruktor, amely l�trehozza a j�t�kteret �s a karakterek list�j�t.
     * 
     * @param palyaMeret A p�lya m�rete.
     */
    public Jatek() {
        gombaszok = new ArrayList<Gombasz>();
        bogaraszok = new ArrayList<Bogarasz>();
        jatekter = new Palya();
        jatekosIndex = 0;
        inicializalasGombasz();
    }

    private void inicializalasBogar(){
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(t1);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);
        t1.addFonal(fon10);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
    }

    private void inicializalasGombasz(){
        Tekton hely = new Tekton();
        Tekton t2 = new Tekton();
        hely.addSzomszed(t2);
        t2.addSzomszed(hely);
        jatekter.tektonHozzaad(t2);
        jatekter.tektonHozzaad(hely);
        Gombasz karakter = new Gombasz(jatekter.getPalya());
        Gombatest test = new Gombatest(hely,karakter);
        karakter.gombatestHozzaad(test);
        hely.setGombatest(test);
        gombaszok.add(karakter);
        //bogaraszok.add(new Bogarasz());
    }

    public Palya getJatekter() {
        return jatekter;
    }

    private boolean jelenlegiJatekos_e(Jatekos jatekos) {
        return jatekos == jelenlegiJatekos();
    }

    private Jatekos jelenlegiJatekos() {
        if (jatekosIndex < gombaszok.size()) {
            return gombaszok.get(jatekosIndex);
        } else {
            return bogaraszok.get(jatekosIndex - gombaszok.size());
        }
    }

    private Gombasz jelenlegiGombasz(){
        if(jatekosIndex >=0 && jatekosIndex < gombaszok.size()){
            return gombaszok.get(jatekosIndex);
        }
        throw new IllegalArgumentException("Nem hasznalhato parancs, mert Bogarasz van koron eppen");
    }

    private void jatekosIndexLeptetes() {
        jatekosIndex = (jatekosIndex + 1) % (gombaszok.size() + bogaraszok.size());
        jelenlegiJatekos().korElejeInicializalas();
        if (jatekosIndex == 0) {
            palyaKezeles();
        }
    }

    private void palyaKezeles() {
    }

    private void jatekosKorvege() {
        if (jelenlegiJatekos().getKorvege()) {
            jelenlegiJatekos().setKorvege(false);
            jatekosIndexLeptetes();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void gombaszHozzaad(Gombasz g) {
        g.setNev("Gombasz" + (gombaszok.size()));
        gombaszok.add(g);
    }

    public void bogaraszHozzaad(Bogarasz b) {
        b.setNev("Bogarasz" + (bogaraszok.size()));
        bogaraszok.add(b);
    }

    /**
     * Szerializálással betölti az adatokat egy fájlból, beleértve a játékteret és a
     * játékosokat.
     * 
     * @param filePath A fájl elérési útja, ahonnan a játék állapota betöltésre
     *                 kerül.
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
     * Szerializálással elmenti az adatokat egy fájlba, beleértve a játékteret és a
     * játékosokat.
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

    // hibakezelés
    public Bogarasz bogaraszFromString(String bogarasz) {
        return bogaraszok.get(Integer.parseInt(bogarasz.substring(7)));
    }

    public Bogar bogarFromString(Bogarasz bogarasz, String bogar) {
        return bogarasz.getBogarak().get(Integer.parseInt(bogar.substring(4)));
    }

    public Tekton tektonFromString(String tekton) {
        return jatekter.getPalya().get(Integer.parseInt(tekton.substring(6)));
    }

    public Gombasz gombaszFromString(String gombasz) {

        if(gombasz == "Koron levo gombasz"){
            return jelenlegiGombasz();
        }
        else{
            Pattern pattern = Pattern.compile("^gombasz(\\d+)$");
            Matcher matcher = pattern.matcher(gombasz);

            if (matcher.matches()) {
                int szam = Integer.parseInt(matcher.group(1));
                if(szam >= 0 && szam < gombaszok.size()){
                    return gombaszok.get(szam);
                }
            } 
            throw new IllegalArgumentException("Hibas bemenet");
        }
    }

    public Gombatest gombatestFromString(String gombatest, Gombasz gombasz) {
        return gombasz.getTestek().get(Integer.parseInt(gombatest.substring(9)));
    }

    public Fonal fonalFromString(String fonal, Tekton tekton) {
        return tekton.getOsszekoto().get(Integer.parseInt(fonal.substring(4)));
    }



    //simi
    public boolean passz(){
        boolean returnValue = false;
        jelenlegiJatekos().setKorvege(true);
        returnValue = jelenlegiJatekos().getKorvege();
        jatekosKorvege();
        return returnValue;
    }

    public String info() {
       
        String s=null;
        s= jelenlegiJatekos().mitLehetCsinalni();
        jatekosKorvege();
        return s;
    }

    public boolean lepes(String bogarasz, String bogar, String hova) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tektonObj = tektonFromString(hova);

        boolean returnValue = false;
        if (jelenlegiJatekos_e(bogaraszObj)) {
            returnValue = bogaraszObj.lep(bogarObj, tektonObj);
        }

        jatekosKorvege();
        return returnValue;
    }

    public boolean evesSporat(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        return bogaraszObj.eves(bogarObj);
    }

    ///
    public boolean evesBogarat(String gombasz, String bogar) {
        Gombasz gobj = gombaszFromString(gombasz);
        Bogar bobj = bogarFromBenitott(gobj, bogar);
        return gobj.bogarEves(bobj);
    }

    private Bogar bogarFromBenitott(Gombasz g, String bogar) {
        return g.getBenitottak().get(Integer.parseInt(bogar.substring(4)));
    }

    public boolean ragas(String bogarasz, String bogar, String fonal) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tekton = bogarObj.getHelyzet();
        Fonal fonalObj = fonalFromString(fonal, tekton);
        return bogaraszObj.ragas(bogarObj, fonalObj);
    }

    //kesz
    public boolean fonalLerak(String gombatest, String t1, String t2) {
        if(jatekosIndex < gombaszok.size()){
            Gombasz gombaszObj = gombaszok.get(jatekosIndex);
            Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
            Tekton t1Obj = tektonFromString(t1);
            Tekton t2Obj = tektonFromString(t2);

            boolean returnValue = false;
        }
        

        boolean returnValue = false;
        if (jelenlegiJatekos_e(gombaszObj)) {
            returnValue = gombatestObj.elhelyez(t1Obj, t2Obj);
        }

        jatekosKorvege();
        return returnValue;
    }

    //kesz
    public boolean sporaSzor(String gombasz, String gombatest, String tekton) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Tekton tektonObj = tektonFromString(tekton);

        boolean returnValue = false;
        if (jelenlegiJatekos_e(gombaszObj)) {
            returnValue = gombatestObj.elszor(tektonObj);
        }

        jatekosKorvege();
        return returnValue;
    }

    //kesz
    public Set<Tekton> gombaHovaRakhat(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);

        Set<Tekton> hovaLehetosegek = new HashSet<Tekton>();
        if (jelenlegiJatekos_e(gombaszObj)) {
            hovaLehetosegek = gombaszObj.hovaLehetosegek(gombatestObj.getHely());
        }

        jatekosKorvege();
        return hovaLehetosegek;
    }

    //kesz
    public Set<Tekton> gombaEler(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);

        return gombatestObj.dfs();
    }

    //kesz
    public Set<Tekton> gombaszEler(String gombasz) {
        Set<Tekton> acc = new HashSet<Tekton>();
        Gombasz gombaszObj = gombaszFromString(gombasz);

        if (jelenlegiJatekos_e(gombaszObj)){
            for (Gombatest g : gombaszObj.getTestek()) {
                Set<Tekton> dfs = g.dfs();
                for (Tekton t : dfs) {
                    acc.add(t);
                }
            }
        }

        jatekosKorvege();
        return acc;
    }

    public ArrayList<Tekton> fonallalOsszekotott(String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        return tektonObj.fonalKeres();
    }

    //kesz
    public Set<Tekton> gombaszHovaSzorhat(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);

        Set<Tekton> returnValue = new HashSet<Tekton>();
        if (jelenlegiJatekos_e(gombaszObj)){
            returnValue = gombatestObj.hovaSzorhat();
        }
        jatekosKorvege();

        return returnValue;
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

    public ArrayList<Gombasz> getGombaszok() {
        return gombaszok;
    }

    public ArrayList<Bogarasz> getBogaraszok() {
        return bogaraszok;
    }

    public Tekton helyzet(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        return bogarObj.getHelyzet();
    }

    //kesz
    public ArrayList<Gombatest> gombatestListazas(String gombasz) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        return gombaszObj.getTestek();
    }

    public ArrayList<Tekton> tektonSzomszedNincsFonal(String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        ArrayList<Tekton> acc = tektonObj.fonalNelkuliSzomzed(jelenlegiGombasz());
        return acc;
    }

    //kesz
    public ArrayList<Bogar> benultBogar(String gombasz) {
        Gombasz gombaszObj = gombaszFromString(gombasz);

        ArrayList<Bogar> returnValue = new ArrayList<>();
        if(jelenlegiJatekos_e(gombaszObj)){
            returnValue = gombaszObj.getBenitottak();
        }
        jatekosKorvege();

        return returnValue;
    }
}