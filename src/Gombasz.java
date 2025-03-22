
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Gombasz extends Jatekos
{
    ArrayList<Gombatest> testek;
    ArrayList<Tekton> palya;

    //valtozas: megkapja a palyat is konstruktorban es nincs Tekton kezdo parametere
    Gombasz(Tekton kezdo, ArrayList<Tekton> palya){
        testek = new ArrayList<>();
        this.palya = palya;
    }

    void gombatestHozzaad(Gombatest g)
    {
        testek.add(g);
    }

    void Round()
    {
     
    }

    void elszakadasDfsKezeles()
    {
        HashSet<Tekton> elerhetok = new HashSet<>();
        for(Gombatest test : testek){
            ArrayList<Tekton> elerhetoTektonok = test.dfs();
            for(Tekton t : elerhetoTektonok){
                elerhetok.add(t);
            }
        }

        

    }

    void removeGombatest(Gombatest g)
    {
        
    }
    
}