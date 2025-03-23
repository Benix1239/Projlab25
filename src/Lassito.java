
public class Lassito extends Spora
{
    /**
     * Konstruktor, amely l�trehozza a Lassito sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
     */
    Lassito(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    /**
     * A Lassito sp�ra kifejti a hat�s�t.
     * 
     * @param b A bog�r, amelyre kifejti a hat�s�t.
     */
 
    public void hatas(Bogar b) 
    {
        //szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            szkeleton.logMethodEntry(this, "lassul");
            b.lassul();
            szkeleton.logMethodExit(this, "");
        }
        //szkeleton.logMethodExit(this, "");
    }
}
