
public class Benito extends Spora
{
    /**
     * Konstruktor, amely l�trehozza a Benito sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
     */
    Benito(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    /**
     * A Benito sp�ra kifejti a hat�s�t.
     * 
     * @param b A bog�r, amelyre kifejti a hat�s�t.
     */
    @Override
    public void hatas(Bogar b) 
    {
        //szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            szkeleton.logMethodEntry(b, "benul");
            b.benul();
            szkeleton.logMethodExit(b, "");
        }
        //szkeleton.logMethodExit(this, "");
    }
}
