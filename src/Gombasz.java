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
    public Gombasz(ArrayList<Tekton> palya){
        testek = new ArrayList<>();
        this.palya = palya;
        benitottak = new ArrayList<>();
    }

    public void korElejeInicializalas(){
        for(Gombatest gt : testek){
            gt.korElejeInicializalas();
        }
    }
    
    public void gombatestHozzaad(Gombatest g){
        testek.add(g);
    }

    public boolean bogarEves(Bogar b){
        if(benitottak.contains(b)){
            Tekton bogarHelyzet = b.getHelyzet();
            ArrayList<Fonal> fonalakHelyzeten = bogarHelyzet.getOsszekoto(this);
            if(fonalakHelyzeten.size() != 0){
                return fonalakHelyzeten.get(0).bogarEves(b);
            }
        }
        return false;
    }

    //torli a fonalakat, amik már nem elérhetőek a gombász egyik testjéből sem
    public void elszakadasDfsKezeles(){
        Set<Tekton> elerhetok = new HashSet<>();
        for(Gombatest test : testek){
            Set<Tekton> elerhetoTektonok = test.dfs();
            for(Tekton t : elerhetoTektonok){
                elerhetok.add(t);
            }
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

    public void removeGombatest(Gombatest g){
        testek.remove(g);
    }

    Set<Tekton> honnanLehetosegek(Gombatest g){
        if(testek.contains(g)){
            return g.dfs();
        }
        return null;
    }

    //visszaadja, hogy a honnan tektonról melyik Tektonokra tud még a gombász fonalat rakni
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

    private boolean mindenGombaKorVege(){
        for(Gombatest gt : testek){
            if(!gt.getKorvege()){
                return false;
            }
        }
        return true;
    }

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

    public Set<Tekton> hovaSzorhat(Gombatest g){
        if(testek.contains(g)){
            return g.hovaSzorhat();
        }
        return null;       
    }

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

    public String mitLehetCsinalni(){
        return "Meg nincs megvalositva";
    }
    
    public void setNev(String nev) {
        this.nev = nev;
    }

    public ArrayList<Gombatest> getTestek() {
        return testek;
    }

    public ArrayList<Bogar> getBenitottak()
    {
        return benitottak;
    }
}