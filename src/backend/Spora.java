package backend;

import java.io.Serializable;
import java.util.Random;

public abstract class Spora implements Serializable 
{
    private int pluszPont;
    private Gombasz tartozik;

    /**
     * Konstruktor, amely l�trehozza a sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
    */
    public Spora(Gombasz tartozik)
    {
        this.tartozik = tartozik;
        Random random = new Random();
		pluszPont=random.nextInt(5);
    }

    /**
     * Tartozik getter.
     * @return Gombasz.
     */
    public Gombasz getTartozik()
    {
        return tartozik;
    }

    /**
     * Tartozik setter.
     * @param g
     */
    public void setTartozik(Gombasz g)
    {
        this.tartozik = g;
    }

    /**
     * Pluszpont getter.
     * @return int.
     */
    public int getPluszpont()
    {
        return pluszPont;
    }

    /**
     * A sp�ra hat�sa egy adott bog�rra.
     * 
     * @param b A bog�r, amelyre a sp�ra hat�ssal lesz.
     */
    public abstract void hatas(Bogar b);
    
}
