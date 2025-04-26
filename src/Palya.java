
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
        if (t != null) {
            palya.add(t);
            t.setId(palya.size());
        }
    }

    /**
     * L�trehozza a p�ly�t a megadott kezdeti m�rettel.
     * 
     * @param kezdomeret A l�trehozand� Tekton objektumok sz�ma.
     */
    public void letrehoz(int kezdomeret)
    {
        for (int i = 0; i < kezdomeret; i++) {
            tektonHozzaad(new Tekton());
        }
    }

    /**
     * Kezeli a t�r�si folyamatot a p�lya tektonjain.
     */
    public void tores()
    {
        int meret = palya.size();
        for(int i=0;i<meret;i++){
            Tekton palya2 = palya.get(i).tores();
            if (palya2 != null) {
                tektonHozzaad(palya2);
            }
        }
    }
    
    /**
     * A Testetlen tektonokon a fonal felsz�v�d�st v�gzi.
     */
    public void felszivodik()
    {
       
        int meret=palya.size();
        for(int i=0;i<meret;i++){
            palya.get(i).fonalElszakadKoronkent();
        }
    }

    /**
     * Palya getter.
     * @return Tekton lista.
     */
    public ArrayList<Tekton> getPalya()
    {
        return this.palya;
    }

}