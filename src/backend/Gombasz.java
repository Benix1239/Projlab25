package backend;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Gombasz extends Jatekos  
{
    private ArrayList<Gombatest> testek;
    private ArrayList<Tekton> palya;
    private ArrayList<Bogar> benitottak;
    

    //valtozas: megkapja a palyat is konstruktorban es nincs Tekton kezdo parametere
    /**
     * Konstruktor
     * @param palya
     */
    public Gombasz(String nev, ArrayList<Tekton> palya){
        super(nev);
        testek = new ArrayList<>();
        this.palya = palya;
        benitottak = new ArrayList<>();
    }

    /**
     * Kör eleji inicializálás.
     */
    public void korElejeInicializalas(){
        for(Gombatest gt : testek){
            gt.korElejeInicializalas();
        }
    }
    
    /**
     * Gombatest hozzáadása a testek listához.
     * @param g A gombatest, amit hozzáadunk.
     */
    public void gombatestHozzaad(Gombatest g){
        testek.add(g);
    }

    /**
     * Bénított bogár evés funkció
     * @param b Bogár, amit megeszik.
     * @return Sikeres-e.
     */
    public boolean bogarEves(Bogar b){
        if(benitottak.contains(b)){
            Tekton bogarHelyzet = b.getHelyzet();
            ArrayList<Fonal> fonalakHelyzeten = bogarHelyzet.getOsszekoto(this);
            if(fonalakHelyzeten.size() != 0){
                if(fonalakHelyzeten.get(0).bogarEves(b)){
                    benitottak.remove(b);
                    return true;
                }
            }
        }
        return false;
    }

    //torli a fonalakat, amik már nem elérhetőek a gombász egyik testjéből sem
    /**
     * Kezeli az elszakadásokat DFS-el.
     */
    public void elszakadasDfsKezeles(){
        Set<Tekton> elerhetok = new HashSet<>();
        for(Gombatest test : testek){
            elerhetok.addAll(test.dfs());
        }

        for(Tekton t : palya){
            if(!elerhetok.contains(t)){
                t.fonalElpusztit(this);
            }
        }

        for(Tekton t : palya){
            if(!elerhetok.contains(t)){
                t.megSeHalMeg();
            }
        }
    }

    /**
     * Gombatest eltávolítása.
     * @param g Gombatest, amit eltávolítunk.
     */
    public void removeGombatest(Gombatest g){
        testek.remove(g);
    }

    /**
     * Honnan lehetőségek.
     * @param g Gombatest.
     * @return Sikeres-e.
     */
    Set<Tekton> honnanLehetosegek(Gombatest g){
        if(testek.contains(g)){
            return g.dfs();
        }
        return null;
    }

    /**
     * Visszaadja, hogy a honnan tektonról melyik Tektonokra tud még a gombász fonalat rakni.
     * @param honnan Tekton
     * @return Halmaz
     */
    Set<Tekton> hovaLehetosegek(Tekton honnan){
        ArrayList<Tekton> honnanSzomszedok = honnan.getSzomszed();
        ArrayList<Tekton> honnanOsszekotve = honnan.fonalKeres(this);
        Set<Tekton> hovaLehetoseg = new HashSet<>();

        for(Tekton t : honnanSzomszedok){
            if(!honnanOsszekotve.contains(t)){
                hovaLehetoseg.add(t);
            }
        }
        return hovaLehetoseg;
    }

    /**
     * Minden gomba körének vége.
     * @return Sikeres-e.
     */
    private boolean mindenGombaKorVege(){
        for(Gombatest gt : testek){
            if(!gt.getKorvege()){
                return false;
            }
        }

        benitottak = new ArrayList<>();
        return true;
    }

    /**
     * Fonal lerakás
     * @param g Gombatest, ami lerakja
     * @param honnan Honnan rakjuk le
     * @param hova Hova rakjuk le
     * @return
     */
    public String fonalLerak(Gombatest g, Tekton honnan, Tekton hova){
        String returnValue = "Hibas parameter";
        if(testek.contains(g) && honnanLehetosegek(g).contains(honnan) && hovaLehetosegek(honnan).contains(hova)){
            returnValue = g.elhelyez(honnan, hova);
        }
        if(mindenGombaKorVege()){
            korVege = true;
        }
        return returnValue;
    }

    /**
     * Hova szórhat az adott gombatest spórát.
     * @param g Az adott gombatest.
     * @return Ahova szórhat.
     */
    public Set<Tekton> hovaSzorhat(Gombatest g){
        if(testek.contains(g)){
            return g.hovaSzorhat();
        }
        return null;       
    }

    /**
     * Spóra szórása.
     * @param g Gombatest, ami szór.
     * @param hova Tekton, ahova szór.
     * @return Sikeres-e.
     */
    public boolean sporaSzor(Gombatest g, Tekton hova){
        boolean returnValue = false;
        if(testek.contains(g) && hovaSzorhat(g).contains(hova)){
            returnValue = g.elszor(hova);
        }
        if(mindenGombaKorVege()){
            korVege = true;
        }
        return returnValue; 
        
    }

    /**
     * Visszaadja, mit lehet csinálni.
     * @return Mit lehet csinálni, String.
     */
    public String mitLehetCsinalni(){
        String returnValue = "";
        int szamlalo = 0;
        for(Gombatest gt : testek){
            returnValue += "gombatest" + szamlalo + ": " + gt.mitLehetCsinalni();
            szamlalo++;
        }
        return returnValue;
    }
    
    /**
     * Név setter.
     * @param nev
     */
    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * Testek getter.
     * @return testek lista.
     */
    public ArrayList<Gombatest> getTestek() {
        return testek;
    }

    /**
     * Benitott bogarakat adja vissza.
     * @return Benitott bogarak lista.
     */
    public ArrayList<Bogar> getBenitottak()
    {
        Set<Tekton> elerhetok = new HashSet<>();
        for(Gombatest test : testek){
            elerhetok.addAll(test.dfs());
        }

        ArrayList<Bogar> returnValue = new ArrayList<>();
        for(Bogar b : benitottak){
            if(b.elerhetoFonallal(elerhetok)){
                returnValue.add(b);
            }
        }
        return returnValue;
    }

    /**
     * Benult bogarat hozzáad a benitottak-hoz.
     * @param b benult Bogar.
     */
    public void benultBogarHozzaad(Bogar b){
        benitottak.add(b);
    }

    /**
     * 
     * @return gombatestjeinek helyei.
     */
    public ArrayList<Tekton> gombatestHelyei(){
        ArrayList<Tekton> returnValue = new ArrayList<>();
        for(Gombatest g : testek){
            returnValue.add(g.getHely());
        }

        return returnValue;
    }

    /**
     * 
     * @return Azok a gombatestek, akik meg kepesek fonalat lerakni.
     */
    public Set<Gombatest> fonalLerakosTestek(){
        Set<Gombatest> returnValue = new HashSet<>();
        for(Gombatest g : testek){
            if(g.tudFonalatRakni()){
                returnValue.add(g);
            }
        }

        return returnValue;
    }

    /**
     * 
     * @return Azok a gombatestek, akik meg kepesek sporat szorni.
     */
    public Set<Gombatest> sporaSzorosTestek(){
        Set<Gombatest> returnValue = new HashSet<>();
        for(Gombatest g : testek){
            if(g.tudSporatSzorni()){
                returnValue.add(g);
            }
        }

        return returnValue;
    }
    
}