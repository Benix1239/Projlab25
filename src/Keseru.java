
public class Keseru extends Spora
{
    /**
     * Konstruktor, amely l�trehozza a Keseru sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
     */
   Keseru(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    /**
     * A Keseru sp�ra kifejti a hat�s�t.
     * 
     * @param b A bog�r, amelyre kifejti a hat�s�t.
     */
    
    public void hatas(Bogar b) 
    {
        //szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            szkeleton.logMethodEntry(b, "ragasBlock");
            b.ragasBlock();
            szkeleton.logMethodExit(b, "");
        }
        //szkeleton.logMethodExit(this, "");
    }

    
}
