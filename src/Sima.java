public class Sima extends Spora
{
    /**
     * Konstruktor, amely l�trehozza a Sima sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
     */
    Sima(Gombasz tartozik)
    {
        super(tartozik);
    }

    /**
    * A Sima sp�ra nem fejt ki hat�st a bogarakra.
    * 
    * @param b A bog�r, amelyre a sp�ra hat�ssal lehetne.
    */
       
    public void hatas(Bogar b)
    {
        // nincs hatas
    }
    
}
