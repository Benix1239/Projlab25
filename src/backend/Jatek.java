package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.basic.BasicSplitPaneUI;

import swing.MainFrame;

public class Jatek implements Serializable {

    private Palya jatekter; 

    private ArrayList<Gombasz> gombaszok;
    private ArrayList<Bogarasz> bogaraszok;

    private int jatekosIndex;
    private int korSzam = 0;

    private MainFrame mainFrame;



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
    }

/**
     * Összegyűjti és visszaadja a játékosok pontszámait
     * @return Map<String, Integer> ahol a kulcs a játékos neve, az érték a pontszáma
     */
    public Map<String, Integer> getJatekosPontok() {
        Map<String, Integer> pontok = new HashMap<>();
        
        // Gombászok pontjainak hozzáadása
        for (Gombasz gombasz : gombaszok) {
            pontok.put(gombasz.getNev(), gombasz.getPontok());
        }
        
        // Bogárászok pontjainak hozzáadása
        for (Bogarasz bogarasz : bogaraszok) {
            pontok.put(bogarasz.getNev(), bogarasz.getPontok());
        }
        
        return pontok;
    }    

    public Jatek(MainFrame mainFrame, ArrayList<String> jatekosNevek) {
        gombaszok = new ArrayList<Gombasz>();
        bogaraszok = new ArrayList<Bogarasz>();
        jatekter = new Palya();
        jatekosIndex = 0;
        this.mainFrame = mainFrame;
        if(jatekosNevek.get(0).toLowerCase().contains("teszt") && jatekosNevek.size()==4)
        {
            alapJatekPalya(jatekosNevek);
        }
        else
        {
            randomPalya(jatekosNevek);
        }
        
    }

    private void jatekosokSorsolasa(ArrayList<String> jatekosNevek){
        Random rand = new Random();
        int gombaszokSzama = (jatekosNevek.size() + 1) / 2;
        int bogaraszokSzama = jatekosNevek.size() - gombaszokSzama;
        for(int i = 0; i < gombaszokSzama; i++){
            int valasztottIndex = rand.nextInt(jatekosNevek.size());
            gombaszok.add(new Gombasz(jatekosNevek.get(valasztottIndex), jatekter.getPalya()));
            jatekosNevek.remove(valasztottIndex);
        }

        for(int i = 0; i < bogaraszokSzama; i++){
            int valasztottIndex = rand.nextInt(jatekosNevek.size());
            bogaraszok.add(new Bogarasz(jatekosNevek.get(valasztottIndex)));
            jatekosNevek.remove(valasztottIndex);
        }
        
    }

    public Palya getJatekter() {
        return jatekter;
    }
    public ArrayList<Gombasz> getGombaszok() {
        return gombaszok;
    }

    public ArrayList<Bogarasz> getBogaraszok() {
        return bogaraszok;
    }

    public MainFrame getMainFrame()
    {
        return this.mainFrame;
    }

    public void setMainFrame(MainFrame uj)
    {
        this.mainFrame = uj;
    }

    public int getkorSzam()
    {
        return korSzam;
    }
//jateklogika-----------------------------------------------------------------------------------

    private boolean jelenlegiJatekos_e(Jatekos jatekos) {
        return jatekos == jelenlegiJatekos();
    }

    public Jatekos jelenlegiJatekos() {
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

    private Bogarasz jelenlegiBogarasz(){
        if (jatekosIndex < gombaszok.size()) {
            throw new IllegalArgumentException("Nem hasznalhato parancs, mert Gombasz van koron eppen");
        } else {
            return bogaraszok.get(jatekosIndex - gombaszok.size());
        }
    }

    private void jatekosIndexLeptetes() {
       
        if(korSzam/(gombaszok.size() + bogaraszok.size()) < 100)
        {
            jatekosIndex = (jatekosIndex + 1) % (gombaszok.size() + bogaraszok.size());
            jelenlegiJatekos().korElejeInicializalas();
            if (jatekosIndex == 0) {
            palyaKezeles();
            }
            korSzam++;
        }
        else
        {
            mainFrame.frissit();
        }
    }

    private void palyaKezeles() {
        ArrayList<Tekton> tmp=new ArrayList<>();
        for (Tekton elem : jatekter.getPalya()) {
            tmp.add(elem);
        }
        for(Tekton t:tmp){
            t.setEletkorNoveles();
            t.fonalElszakadKoronkent();
            Tekton uj = t.tores();
            if(uj!=null && jatekter.getPalya().size() < 625){
                jatekter.getPalya().add(uj);
            }
        }
    }

    private void jatekosKorvege() {
        if (jelenlegiJatekos().getKorvege()) {
            jelenlegiJatekos().setKorvege(false);
            jatekosIndexLeptetes();
        }
    }
    
    public String jelenlegiJatekosNeve() {
        if (jatekosIndex < gombaszok.size()) {
            return "Jelenlegi gombasz: " + jelenlegiJatekos().getNev();
        } else {
            return "Jelenlegi bogarasz: " + jelenlegiJatekos().getNev();
        }
    }

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

//string leforditas objektumra----------------------------------------------------------------------------

    public Bogarasz bogaraszFromString(String bogarasz) {
        //return bogaraszok.get(Integer.parseInt(bogarasz.substring(8)));

        if(bogarasz == "Koron levo bogarasz"){
            return jelenlegiBogarasz();
        }else{
            Pattern pattern = Pattern.compile("^bogarasz(\\d+)$");
            Matcher matcher = pattern.matcher(bogarasz);

            if (matcher.matches()) {
                int szam = Integer.parseInt(matcher.group(1));
                if(szam >= 0 && szam <bogaraszok.size()){
                    return bogaraszok.get(szam);
                }
            } 
            throw new IllegalArgumentException("Ilyen bogarasz nem letezik");
        }
        
    }

    public Bogar bogarFromString(Bogarasz bogarasz, String bogar) {
        //return bogarasz.getBogarak().get(Integer.parseInt(bogar.substring(5)));
        Pattern pattern = Pattern.compile("^bogar(\\d+)$");
        Matcher matcher = pattern.matcher(bogar);

        if (matcher.matches()) {
            int szam = Integer.parseInt(matcher.group(1));
            if(szam >= 0 && szam<bogarasz.getBogarak().size()){
                return bogarasz.getBogarak().get(szam);
            }
        } 
        throw new IllegalArgumentException("Ilyen bogar nem letezik");
    }

    public Tekton tektonFromString(String tekton) {
        Pattern pattern = Pattern.compile("^tekton(\\d+)$");
        Matcher matcher = pattern.matcher(tekton);

        if (matcher.matches()) {
            int szam = Integer.parseInt(matcher.group(1));
            if(szam >= 0 && szam < jatekter.getPalya().size()){
                return jatekter.getPalya().get(szam);
            }
        } 
        throw new IllegalArgumentException("Ilyen tekton nem letezik");
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
            throw new IllegalArgumentException("Ilyen gombasz nem letezik");
        }
    }

    public Gombatest gombatestFromString(String gombatest, Gombasz gombasz) {
        //return gombasz.getTestek().get(Integer.parseInt(gombatest.substring(9)));
        Pattern pattern = Pattern.compile("^gombatest(\\d+)$");
        Matcher matcher = pattern.matcher(gombatest);

        if (matcher.matches()) {
            int szam = Integer.parseInt(matcher.group(1));
            if(szam >= 0 && szam<gombasz.getTestek().size()){
                return gombasz.getTestek().get(szam);
            }
        } 
        throw new IllegalArgumentException("Ilyen gombatest nem letezik");
    }

    public Fonal fonalFromString(String fonal, Tekton tekton) {
        //return tekton.getOsszekoto().get(Integer.parseInt(fonal.substring(5)));

        Pattern pattern = Pattern.compile("^fonal(\\d+)$");
        Matcher matcher = pattern.matcher(fonal);

        if (matcher.matches()) {
            int szam = Integer.parseInt(matcher.group(1));
            if(szam >= 0 && szam<tekton.getOsszekoto().size()){
                return tekton.getOsszekoto().get(szam);
            }
        } 
        throw new IllegalArgumentException("Ilyen fonal nem letezik");
    }

//parancsok---------------------------------------------------------------------------------------------
    
    public String getPalya(){
        String returnValue = "A jelenlegi palya: \n";
        for(int i = 0; i < jatekter.getPalya().size(); i++){
            Tekton t=jatekter.getPalya().get(i);
            returnValue += "tekton"+ jatekter.getPalya().indexOf(t);
            if (i < jatekter.getPalya().size() - 1) {
                returnValue += "\n"; 
            }
        }
        return returnValue;
    }

    public String passz(){
        String returnValue = null;
        jelenlegiJatekos().setKorvege(true);
        returnValue = "A "+jelenlegiJatekosNeve()+" jatekos passzolta a koret";
        jatekosKorvege();
        mainFrame.frissit();
        return returnValue;
    }

    public String info() {

        String s;
        s="A jelenlegi jatekos: " +jelenlegiJatekosNeve() + "\n" +jelenlegiJatekos().mitLehetCsinalni();
        jatekosKorvege();
        return s;
    }

    public ArrayList<Bogar> bogarakListazas(String bogarasz) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        return bogaraszObj.getBogarak();
    }

    public String lepes(String bogarasz, String bogar, String hova) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tektonObj = tektonFromString(hova);

        String returnValue = "Nem ennek jatekosnak a kore van";
        if (jelenlegiJatekos_e(bogaraszObj)) {
            returnValue = bogaraszObj.lep(bogarObj, tektonObj);
        }

        jatekosKorvege();
        return returnValue;
    }

    public String evesSporat(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);

        String returnValue = "Nem ennek jatekosnak a kore van";
        if (jelenlegiJatekos_e(bogaraszObj)) { 
            returnValue =bogaraszObj.eves(bogarObj);
        }

        jatekosKorvege();
        return returnValue;
    }

    public String ragas(String bogarasz, String bogar, String fonal) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton tekton = bogarObj.getHelyzet();
        Fonal fonalObj = fonalFromString(fonal, tekton);

        String returnValue = "Nem ennek jatekosnak a kore van";
        if (jelenlegiJatekos_e(bogaraszObj)) { 
            returnValue=bogaraszObj.ragas(bogarObj, fonalObj);
        }

        jatekosKorvege();
        return returnValue;
    }

    ///
    public boolean evesBogarat(String gombasz, String bogar) {
        Gombasz gobj = gombaszFromString(gombasz);
        Bogar bobj = bogarFromBenitott(gobj, bogar);
        return gobj.bogarEves(bobj);
    }

    private Bogar bogarFromBenitott(Gombasz g, String bogar) {
        return g.getBenitottak().get(Integer.parseInt(bogar.substring(5)));
    }

    public String fonalLerak(String gombatest, String t1, String t2) {
        Gombasz gombaszObj = jelenlegiGombasz();
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Tekton t1Obj = tektonFromString(t1);
        Tekton t2Obj = tektonFromString(t2);

        String returnValue = gombaszObj.fonalLerak(gombatestObj, t1Obj, t2Obj);

        jatekosKorvege();

        mainFrame.frissit();
        return returnValue;
    }

    //kesz
    public boolean sporaSzor(String gombatest, String tekton) {
        Gombasz gombaszObj = jelenlegiGombasz();
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Tekton tektonObj = tektonFromString(tekton);

        boolean returnValue = false;
        returnValue = gombaszObj.sporaSzor(gombatestObj, tektonObj);

        jatekosKorvege();
        mainFrame.frissit();
        return returnValue;
    }

    //kesz
    public boolean[] gombaHovaRakhat(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);

        Set<Tekton> hovaLehetosegek = new HashSet<Tekton>();
        if (jelenlegiJatekos_e(gombaszObj)) {
            hovaLehetosegek = gombaszObj.hovaLehetosegek(gombatestObj.getHely());
        }

        jatekosKorvege();

        boolean[] returnValue = new boolean[palyaMeret()];
        for(Tekton t : hovaLehetosegek){
            returnValue[jatekter.getPalya().indexOf(t)] = true;
        }

        return returnValue;
    }

    public boolean[] gombaHonnanRakhat(String gombasz, String gombatest, String hova){
        Tekton hovaObj = tektonFromString(hova);

        Set<Tekton> gombaAltalElert = gombaEler(gombasz, gombatest);

        boolean[] returnValue = new boolean[palyaMeret()];
        for(Tekton t : gombaAltalElert){
            if(hovaObj.szomszedE(t)){
                returnValue[jatekter.getPalya().indexOf(t)] = true;
            }
        }

        return returnValue;
    }

    //kesz
    public Set<Tekton> gombaEler(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);

        return gombatestObj.dfs();
    }

    //visszaadja, hogy melyik tektonok erhetoek el. Pl ha a harmadik elerheto: [false,false,true]
    public boolean[] gombaszEler(String gombasz) {
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

        boolean[] returnValue = new boolean[palyaMeret()];
        for(Tekton t : acc){
            returnValue[jatekter.getPalya().indexOf(t)] = true;
        }

        jatekosKorvege();
        return returnValue;
    }

    public boolean[] fonallalOsszekotott(String tekton) {
        Tekton tektonObj = tektonFromString(tekton);
        ArrayList<Tekton> helyek = tektonObj.fonalKeres();

        boolean[] returnValue = new boolean[palyaMeret()];
        for(Tekton t : helyek){
            returnValue[jatekter.getPalya().indexOf(t)] = true;
        }

        return returnValue;
    }

    //kesz
    public boolean[] gombaszHovaSzorhat(String gombasz, String gombatest) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);

        Set<Tekton> helyek = gombatestObj.hovaSzorhat();

        boolean[] hovaSzorhat = new boolean[palyaMeret()];
        for(Tekton t : helyek){
            hovaSzorhat[jatekter.getPalya().indexOf(t)] = true;;
        }

        return hovaSzorhat;
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

    public String getJelenlegiJatekosNev(){
        for(Gombasz g : gombaszok){
            if(jelenlegiJatekos_e(g)){
                return "gombasz" + gombaszok.indexOf(g);
            }
        }

        for(Bogarasz b : bogaraszok){
            if(jelenlegiJatekos_e(b)){
                return "bogarasz" + bogaraszok.indexOf(b);
            }
        }
        return null;
    }

    //Ez a fv CSAK abban az esetben használható, ha pontosan 4 jatekos van (2 gombasz, 2 bogarasz).
    public void alapJatekPalya(ArrayList<String> jatekosNevek)
    {
        jatekter = new Palya();

        Tekton elso = new Tekton();
        Tekton ketto = new Tekton();
        Tekton harom = new Tekton();
        Tekton negy = new Tekton();
        Tekton ot = new Tekton();
        Tekton hat = new Testetlen();
        Tekton het = new Testetlen();
        Tekton nyolc = new EletbenTarto();
        Tekton kilenc = new EletbenTarto();
        Tekton tiz = new Felszivo();
        Tekton tizenegy = new Felszivo();
        Tekton tizenketto = new Egyfonalas();
        Tekton tizenharom = new Egyfonalas();

        jatekter.getPalya().add(elso);
        jatekter.getPalya().add(ketto);
        jatekter.getPalya().add(harom);
        jatekter.getPalya().add(negy);
        jatekter.getPalya().add(ot);
        jatekter.getPalya().add(hat);
        jatekter.getPalya().add(het);
        jatekter.getPalya().add(nyolc);
        jatekter.getPalya().add(kilenc);
        jatekter.getPalya().add(tiz);
        jatekter.getPalya().add(tizenegy);
        jatekter.getPalya().add(tizenketto);
        jatekter.getPalya().add(tizenharom);

        elso.szomszed.add(ketto);
        elso.szomszed.add(hat);
        elso.szomszed.add(tizenketto);
        elso.szomszed.add(tizenharom);



        ketto.szomszed.add(elso);
        ketto.szomszed.add(tizenketto);
        ketto.szomszed.add(harom);

        harom.szomszed.add(ketto);
        harom.szomszed.add(hat);
        harom.szomszed.add(het);

        negy.szomszed.add(het);
        negy.szomszed.add(nyolc);
        negy.szomszed.add(tizenegy);

        ot.szomszed.add(tizenegy);
        ot.szomszed.add(tizenharom);

        hat.szomszed.add(elso);
        hat.szomszed.add(harom);
        hat.szomszed.add(nyolc);

        het.szomszed.add(harom);
        het.szomszed.add(negy);
        het.szomszed.add(tizenegy);
        het.szomszed.add(tizenketto);

        nyolc.szomszed.add(negy);
        nyolc.szomszed.add(hat);

        kilenc.szomszed.add(tizenketto);

        tiz.szomszed.add(tizenketto);
        tiz.szomszed.add(tizenharom);

        tizenegy.szomszed.add(negy);
        tizenegy.szomszed.add(ot);
        tizenegy.szomszed.add(het);

        tizenketto.szomszed.add(elso);
        tizenketto.szomszed.add(ketto);
        tizenketto.szomszed.add(het);
        tizenketto.szomszed.add(kilenc);
        tizenketto.szomszed.add(tiz);

        tizenharom.szomszed.add(elso);
        tizenharom.szomszed.add(ot);
        tizenharom.szomszed.add(tiz);

        jatekosokSorsolasa(jatekosNevek);

        Gombatest gtEgy = new Gombatest(elso, gombaszok.get(0));
        gombaszok.get(0).gombatestHozzaad(gtEgy);
        elso.gombatest = gtEgy;

        Gombatest gtKet = new Gombatest(negy, gombaszok.get(1));
        gombaszok.get(1).gombatestHozzaad(gtKet);
        negy.gombatest = gtKet;

        bogaraszok.get(0).bogarHozzaad(ketto);
        ketto.addSpora(new Benito(gombaszok.get(0)));
        
        bogaraszok.get(1).bogarHozzaad(het);
        het.addSpora(new Benito(gombaszok.get(0)));

        jelenlegiJatekos().korElejeInicializalas();

    }

    public int tektonSpora(String gombasz, String tekton){
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Tekton tektonObj = tektonFromString(tekton);
        return tektonObj.hanySporajaVan(gombaszObj);
    }

    public int palyaMeret(){
        return jatekter.getPalya().size();
    }

    public boolean gombaszKoreVanE(){
        if(jatekosIndex < gombaszok.size()){
            return true;
        }
        return false;
    }

    public boolean bogaraszKoreVanE(){
        return !gombaszKoreVanE();
    }

    public int[] sajatBogarakHelyei(){
        if(!bogaraszKoreVanE()){
            return null;
        }

        int[] returnValue = new int[palyaMeret()];
        ArrayList<Tekton> helyek = jelenlegiBogarasz().bogarakHelyei();

        for(Tekton t : helyek){
            returnValue[jatekter.getPalya().indexOf(t)]++;
        }

        return returnValue;

    }

    public int[] mindenBogarHelyei(){

        int[] returnValue = new int[palyaMeret()];
        ArrayList<Tekton> helyek = new ArrayList<>();

        for(Bogarasz bogarasz : bogaraszok){
            helyek.addAll(bogarasz.bogarakHelyei());
        }

        for(Tekton t : helyek){
            returnValue[jatekter.getPalya().indexOf(t)]++;
        }

        return returnValue;
    }

    public int[] sajatGombatestekHelyei(){
        if(!gombaszKoreVanE()){
            return null;
        }

        int[] returnValue = new int[palyaMeret()];
        ArrayList<Tekton> helyek = jelenlegiGombasz().gombatestHelyei();
        for(Tekton t : helyek){
            returnValue[jatekter.getPalya().indexOf(t)]++;
        }
        return returnValue;
    }

    public int[] mindenGombatestHelyei(){

        int[] returnValue = new int[palyaMeret()];
        ArrayList<Tekton> helyek = new ArrayList<>();

        for(Gombasz gombasz : gombaszok){
            helyek.addAll(gombasz.gombatestHelyei());
        }

        for(Tekton t : helyek){
            returnValue[jatekter.getPalya().indexOf(t)]++;
        }

        return returnValue;
    }

    public ArrayList<String> jelenlegiGombaszFonalLerakosTestjei(){
        Gombasz gombaszObj = gombaszFromString("Koron levo gombasz");
        Set<Gombatest> gombatestek = gombaszObj.fonalLerakosTestek();
        
        ArrayList<String> returnString = new ArrayList<>();
        for(Gombatest g : gombatestek){
            returnString.add("gombatest" + jelenlegiGombasz().getTestek().indexOf(g));
        }

        return returnString;
    }

    public ArrayList<String> jelenlegiGombaszSporaSzorosTestjei(){
        Gombasz gombaszObj = gombaszFromString("Koron levo gombasz");
        Set<Gombatest> gombatestek = gombaszObj.sporaSzorosTestek();

        ArrayList<String> returnString = new ArrayList<>();
        for(Gombatest g : gombatestek){
            returnString.add("gombatest" + jelenlegiGombasz().getTestek().indexOf(g));
        }

        return returnString;
    }
    public ArrayList<String> jelenlegiBogaraszBogaraiTudLepni() {
        Bogarasz bogaraszObj = jelenlegiBogarasz();  
        List<Bogar> bogarak = bogaraszObj.getBogarak();  

        ArrayList<String> returnString = new ArrayList<>();
        for (int i = 0; i < bogarak.size(); i++) {
            if(bogarak.get(i).getMozgaspont()>0&& bogarak.get(i).hovaLephet()!=null){
                returnString.add("bogar" + i);
            }
        }

        return returnString;
    }

    public ArrayList<String> jelenlegiBogaraszBogaraiTudEnni() {
        Bogarasz bogaraszObj = jelenlegiBogarasz();  
        List<Bogar> bogarak = bogaraszObj.getBogarak();  

        ArrayList<String> returnString = new ArrayList<>();
        for (int i = 0; i < bogarak.size(); i++) {
            if(bogarak.get(i).getactionEves()==true&& bogarak.get(i).getHelyzet().getSporak()!=null){
                returnString.add("bogar" + i);
            }
        }

        return returnString;
    }

    public ArrayList<String> jelenlegiGombaszTudEnni() {
        Gombasz GombaszObj = jelenlegiGombasz();  

        ArrayList<String> returnString = new ArrayList<>();
        for (int i = 0; i < GombaszObj.getBenitottak().size(); i++) {
            returnString.add("bogar" + i);
        }

        return returnString;
    }

    public ArrayList<String> jelenlegiBogaraszBogaraiTudRagni() {
        Bogarasz bogaraszObj = jelenlegiBogarasz();  
        List<Bogar> bogarak = bogaraszObj.getBogarak();  

        ArrayList<String> returnString = new ArrayList<>();
        for (int i = 0; i < bogarak.size(); i++) {
            if(bogarak.get(i).getactionRagas()==true && bogarak.get(i).getHelyzet().getOsszekoto()!=null){
                returnString.add("bogar" + i);
            }
        }

        return returnString;
    }

    public ArrayList<String> bogarMitTudElragni(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        

        ArrayList<String> returnString = new ArrayList<>();
        for (int i = 0; i < bogarObj.getHelyzet().getOsszekoto().size(); i++) {
            returnString.add("fonal" + i);
        }

        return returnString;
    }

    public boolean[] bogarHovaLephet(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj,bogar);
    
        Set<Tekton> lepesek = bogarObj.hovaLephet(); 
    
        boolean[] returnValue = new boolean[palyaMeret()]; 

        for (Tekton t : lepesek) {
            returnValue[jatekter.getPalya().indexOf(t)] = true;
        }
    
        return returnValue;
    }
    
    public boolean[] bogarHovaAll(String bogarasz, String bogar) {
        Bogarasz bogaraszObj = bogaraszFromString(bogarasz);
        Bogar bogarObj = bogarFromString(bogaraszObj, bogar);
        Tekton hely = bogarObj.getHelyzet();
    
        boolean[] returnValue = new boolean[palyaMeret()];
        int index = jatekter.getPalya().indexOf(hely);
        if (index != -1) {
            returnValue[index] = true;
        }
    
        return returnValue;
    }

    public boolean[] jelenlegiGombaszBenitottak(String gombasz) {
        Gombasz gombaszObj = gombaszFromString(gombasz);
        List<Bogar> benitott = gombaszObj.getBenitottak();
    
        boolean[] returnValue = new boolean[palyaMeret()];
        List<Tekton> palya = jatekter.getPalya();
    
        for (Bogar b : benitott) {
            Tekton hely = b.getHelyzet();
            int index = palya.indexOf(hely);
            if (index != -1) {
                returnValue[index] = true;
            }
        }
    
        return returnValue;
    }


    public void randomPalya(ArrayList<String> jatekosNevek)
    {
        //tektonok
        Random rnd = new Random();
        int meret = 5* jatekosNevek.size();
       
        jatekter = new Palya();

        int lerakhato = meret;

        Tekton elso = randomTipusuTekton();
        jatekter.getPalya().add(elso);

        do 
        { 
            lerakhato = palyaFelepites(lerakhato, elso);
        } while (lerakhato > 0);
        
        lerakhato = rnd.nextInt(meret/3) + meret/3;

        do 
        { 
            Tekton egyik = jatekter.getPalya().get(rnd.nextInt(meret));
            Tekton masik;
            do 
            { 
                masik = jatekter.getPalya().get(rnd.nextInt(meret));
            } while (egyik == masik);

            egyik.szomszed.add(masik);
            masik.szomszed.add(elso);
            lerakhato--;
        } while (lerakhato != 0);
/* 
        for(int i = 0; i < meret; i++)
        {
            jatekter.getPalya().add(randomTipusuTekton());
        }
*/
        ArrayList<Tekton> foglalt = new ArrayList<>();
        //jatekosok
        jatekosokSorsolasa(jatekosNevek);
        for(int i = 0; i < gombaszok.size(); i++)
        {
            Tekton kezdo;
            do
            {
                kezdo = jatekter.getPalya().get(rnd.nextInt(meret));
                
            }while(foglalt.contains(kezdo));
            foglalt.add(kezdo);
            gombaszok.get(i).getTestek().add(new Gombatest(kezdo, gombaszok.get(i)));
            kezdo.gombatest = gombaszok.get(i).getTestek().get(0);
        }

        for(int i = 0; i < bogaraszok.size(); i++)
        {
            Tekton kezdo;
            do
            {
                kezdo = jatekter.getPalya().get(rnd.nextInt(meret));
            }while(foglalt.contains(kezdo));
            foglalt.add(kezdo);
            bogaraszok.get(i).bogarHozzaad(kezdo);
        }

    }

    //random tekton tipus

    public Tekton randomTipusuTekton()
    {
        Random rnd = new Random();
        Tekton uj;
        int i = rnd.nextInt(10);
        switch (i) {
            case 1:
                uj = new Testetlen(); 
                break;
            case 2:
                uj = new Felszivo();
                break;
            case 3:
                uj = new Egyfonalas();
                break;
            case 4:
                uj = new EletbenTarto();
                break;
            default:
            uj = new Tekton();
        }

        return uj;
    }

    //palya felepites random kapcsolatokkal
    int palyaFelepites(int lerakhato, Tekton forras)
    {
        if(lerakhato == 1)
        {
            return 0;
        }

        Random rnd = new Random();
        int ertek;
        do { 

            do { 
                ertek = rnd.nextInt(lerakhato);
            } while (ertek > lerakhato/2);
            for(int i = 0; i < ertek; i++)
            {
                Tekton uj = randomTipusuTekton();

                forras.addSzomszed(uj);
                uj.addSzomszed(forras);
                jatekter.getPalya().add(uj);
            }

        } while (ertek == 0 && jatekter.getPalya().size() <= 4);

        int maradt = lerakhato - ertek;
        int kezdoertek = 1;
        if(forras == jatekter.getPalya().get(0))
        {
            kezdoertek = 0;
        }

        for(int i = kezdoertek; i < forras.getSzomszed().size(); i++)
        {
            if(maradt != 0)
            {
                maradt =  palyaFelepites(maradt, forras.getSzomszed().get(i));
            }
           
        }

        return maradt;
        
    }


}