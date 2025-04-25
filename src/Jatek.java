
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
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
    }

    private void inicializalasBogar(){
        //BogarLep
        /*Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);
        t1.addFonal(fon10);
        gombaszok.add(jatekos);*/

        //BogarEszik
        /*Tekton t1 = new Tekton();
        jatekter.tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Spora s= new Sima(jatekos);
        t1.addSpora(s);
        gombaszok.add(jatekos);*/

        //BogarNemEszik
        /*Tekton t1 = new Tekton();
        jatekter.tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        gombaszok.add(jatekos);*/

        //BogarRagas
        /*Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        t1.addSzomszed(t2);
        t2.addSzomszed(t1);
        
        jatekter.tektonHozzaad(t1);
        jatekter.tektonHozzaad(t2);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Fonal fon10 = new Fonal(t2,jatekos);
        Fonal fon11 = new Fonal(t1,jatekos);
        t2.addFonal(fon11);
        t1.addFonal(fon10);
        gombaszok.add(jatekos);*/

        //Gyorsito
        /*Tekton t1 = new Tekton();
        jatekter.tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Spora s= new Gyorsito(jatekos);
        t1.addSpora(s);
        gombaszok.add(jatekos);*/

        //Benito
        /*Tekton t1 = new Tekton();
        jatekter.tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Spora s= new Benito(jatekos);
        t1.addSpora(s);
        gombaszok.add(jatekos);*/

        //Keseru
        /*Tekton t1 = new Tekton();
        jatekter.tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Spora s= new Keseru(jatekos);
        t1.addSpora(s);
        gombaszok.add(jatekos);*/

        //Lassito
        /*Tekton t1 = new Tekton();
        jatekter.tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Spora s= new Lassito(jatekos);
        t1.addSpora(s);
        gombaszok.add(jatekos);*/

        Tekton t1 = new Tekton();
        jatekter.tektonHozzaad(t1);

        Bogarasz karakter =new Bogarasz();
        Bogar bogar = new Bogar();
        karakter.bogarHozzaad(bogar, t1);
        bogaraszok.add(karakter);

        Gombasz jatekos = new Gombasz(jatekter.getPalya());
        Spora s= new Szaporodo(jatekos);
        t1.addSpora(s);
        gombaszok.add(jatekos);
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
	jelenlegiJatekos().korElejeInicializalas();
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

    private void jatekosIndexLeptetes() {
        jatekosIndex = (jatekosIndex + 1) % (gombaszok.size() + bogaraszok.size());
        jelenlegiJatekos().korElejeInicializalas();
        if (jatekosIndex == 0) {
            palyaKezeles();
        }
    }

    private void palyaKezeles() {
        ArrayList<Tekton> tmp=new ArrayList<>();
        for (Tekton elem : jatekter.getPalya()) {
            tmp.add(elem);
        }
        for(Tekton t:tmp){
            t.fonalElszakadKoronkent();
            Tekton uj = t.tores();
            if(uj!=null){
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
            return "Gombasz" + jatekosIndex;
        } else {
            return "Bogarasz" + (jatekosIndex - gombaszok.size());
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
        return g.getBenitottak().get(Integer.parseInt(bogar.substring(4)));
    }

    public String fonalLerak(String gombatest, String t1, String t2) {
        Gombasz gombaszObj = jelenlegiGombasz();
        Gombatest gombatestObj = gombatestFromString(gombatest, gombaszObj);
        Tekton t1Obj = tektonFromString(t1);
        Tekton t2Obj = tektonFromString(t2);

        String returnValue = gombaszObj.fonalLerak(gombatestObj, t1Obj, t2Obj);

        jatekosKorvege();
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

    public void alapJatekPalya()
    {
        jatekter = new Palya();
        Gombasz gombaszEgy = new Gombasz(jatekter.getPalya());
        Gombasz gombaszKet = new Gombasz(jatekter.getPalya());

        Bogarasz bogaraszEgy = new Bogarasz();
        Bogarasz bogaraszKet = new Bogarasz();

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

        Gombatest gtEgy = new Gombatest(elso, gombaszEgy);
        gombaszEgy.gombatestHozzaad(gtEgy);
        gombaszok.add(gombaszEgy);

        Gombatest gtKet = new Gombatest(negy, gombaszKet);
        gombaszKet.gombatestHozzaad(gtKet);
        gombaszok.add(gombaszKet);

        Bogar bEgy = new Bogar();
        bogaraszEgy.bogarHozzaad(bEgy, ketto);
        bogaraszok.add(bogaraszEgy);

        Bogar bKet = new Bogar();
        bogaraszKet.bogarHozzaad(bKet, het);
        bogaraszok.add(bogaraszKet);

    }

    public int tektonSpora(String gombasz, String tekton){
        Gombasz gombaszObj = gombaszFromString(gombasz);
        Tekton tektonObj = tektonFromString(tekton);
        return tektonObj.hanySporajaVan(gombaszObj);
    }
}