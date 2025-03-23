
import java.util.ArrayList;
import java.io.Serializable;

public class Palya implements Serializable;
{
    private ArrayList<Tekton> palya;

    /**
     * Konstruktor, amely létrehozza a pályát egy adott mérettel (tekton számmal).
     * 
     * @param kezdomeret A kezdeti méret.
     */
    Palya(int kezdomeret)
    {
        letrehoz(kezdomeret);
    }

    /**
     * Hozzáad egy új Tekton objektumot a pályához.
     * 
     * @param t A hozzáadandó Tekton objektum.
     */
    public void tektonHozzaad(Tekton t)
    {
        szkeleton.logMethodEntry(this, "tektonHozzaad");
        if (t != null)) {
            palya.add(t);
        }
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Létrehozza a pályát a megadott kezdeti mérettel.
     * 
     * @param kezdomeret A létrehozandó Tekton objektumok száma.
     */
    public void letrehoz(int kezdomeret)
    {
        szkeleton.logMethodEntry(this, "letrehoz");
        for (int i = 0; i < kezdomeret; i++) {
            tektonHozzaad(new Tekton());
        }
        szkeleton.logMethodExit(this, "");
    }

    /**
     * Kezeli a törési folyamatot a pálya tektonjain.
     */
    public void tores()
    {
        szkeleton.logMethodEntry(this, "tores");
        for (Tekton t : palya) {
            Tekton torott = t.tores();
            if (torott != null) {
                tektonHozzaad(torott);
            }
        }
        szkeleton.logMethodExit(this, "");
    }

    
    /**
     * A Testetlen tektonokon a fonal felszívódást végzi.
     */
    public void felszivodik()
    {
        szkeleton.logMethodEntry(this, "felszivodik");
        for (Tekton t : palya) {
            if (t instanceof Testetlen) {
                t.fonalElszakadKoronkent();
            }
        }
        szkeleton.logMethodExit(this, "");
    }
}