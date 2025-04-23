import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Gombasz extends Jatekos
{
    private ArrayList<Gombatest> testek;
    private ArrayList<Tekton> palya;

    

    //valtozas: megkapja a palyat is konstruktorban es nincs Tekton kezdo parametere
    public Gombasz(ArrayList<Tekton> palya){
        testek = new ArrayList<>();
        this.palya = palya;
    }

    public void gombatestHozzaad(Gombatest g){
        testek.add(g);
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
                t.megseHalMeg();
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
        if(honnan.hanyFonalaVanGombasznak(this) != 0){
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
        return null;
    }


    public boolean fonalLerak(Gombatest g, Tekton honnan, Tekton hova){
        if(testek.contains(g) && honnanLehetosegek(g).contains(honnan) && hovaLehetosegek(honnan).contains(hova)){
            return g.elhelyez(honnan, hova);
        }
        return false;
    }

    public Set<Tekton> hovaSzorhat(Gombatest g){
        if(testek.contains(g)){
            return g.hovaSzorhat();
        }
        return null;       
    }

    public boolean sporaSzor(Gombatest g, Tekton hova){
        if(testek.contains(g) && hovaSzorhat(g).contains(hova)){
            return g.elszor(hova);
        }
        return false; 
        
    }
    
    public void setNev(String nev) {
        this.nev = nev;
    }
}