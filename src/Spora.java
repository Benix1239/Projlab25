


public abstract class Spora 
{
    private int pluszPont;
    private Gombasz tartozik;

    /**
     * Konstruktor, amely l�trehozza a sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
     */
    Spora(Gombasz tartozik)
    {
        this.tartozik = tartozik;
    }

    Gombasz getTartozik()
    {
        return tartozik;
    }

    void setTartozik(Gombasz g)
    {
        this.tartozik = g;
    }

    int getPluszpont()
    {
        return plusszpont;
    }
    /**
     * A sp�ra hat�sa egy adott bog�rra.
     * 
     * @param b A bog�r, amelyre a sp�ra hat�ssal lesz.
     */
    public abstract void hatas(Bogar b);
    
    

}
