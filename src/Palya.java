
import java.io.Serializable;
import java.util.ArrayList;

public class Palya implements Serializable
{
    private ArrayList<Tekton> palya;

    /**
     * Konstruktor, amely l�trehozza a p�ly�t egy adott m�rettel (tekton sz�mmal).
     * 
     * @param kezdomeret A kezdeti m�ret.
     */
    Palya(int kezdomeret)
    {
        palya = new ArrayList<>();
        letrehoz(kezdomeret);
    }
    Palya()
    {
        palya = new ArrayList<>();
    }

    /**
     * Hozz�ad egy �j Tekton objektumot a p�ly�hoz.
     * 
     * @param t A hozz�adand� Tekton objektum.
     */
    public void tektonHozzaad(Tekton t)
    {
        szkeleton.logMethodEntry(this, "tektonHozzaad");
        if (t != null) {
            palya.add(t);
            t.setId(palya.size());
        }
        szkeleton.logMethodExit(this, "");
    }

    /**
     * L�trehozza a p�ly�t a megadott kezdeti m�rettel.
     * 
     * @param kezdomeret A l�trehozand� Tekton objektumok sz�ma.
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
     * Kezeli a t�r�si folyamatot a p�lya tektonjain.
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
     * A Testetlen tektonokon a fonal felsz�v�d�st v�gzi.
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


    public ArrayList<Tekton> getPalya()
    {
        return this.palya;
    }
}