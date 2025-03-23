
public class Gyorsito extends Spora
{
    /**
     * Konstruktor, amely l�trehozza a Gyorsito sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
     */
    Gyorsito(Gombasz tartozik) 
    {
      super(tartozik);
    }
    
    /**
     * A Gyorsito sp�ra kifejti a hat�s�t.
     * 
     * @param b A bog�r, amelyre kifejti a hat�s�t.
     */
    @Override
    public void hatas(Bogar b) 
    {
        //szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            szkeleton.logMethodEntry(b, "gyorsul");
            b.gyorsul();
            szkeleton.logMethodExit(b, "");
        }
        //szkeleton.logMethodExit(this, "");
    }
    
}
