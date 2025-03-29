


public abstract class Spora 
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
    }

    public Gombasz getTartozik()
    {
        return tartozik;
    }

    public void setTartozik(Gombasz g)
    {
        this.tartozik = g;
    }

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
