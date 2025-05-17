package backend;



public class Szaporodo extends Spora
{
     /**
     * Konstruktor, amely l�trehozza a szaporodo sp�r�t egy adott gomb�szhoz.
     * 
     * @param tartozik A gomb�sz, akihez a sp�ra tartozik.
     */
    Szaporodo(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    /**
     * A szaporodo sp�ra kifejti a hat�s�t.
     * 
     * @param b A bog�r, amelyre kifejti a hat�s�t.
     */
    @Override
    public void hatas(Bogar b) 
    {
        if (b != null) {
            b.szaporodo();
        }
    }

}
