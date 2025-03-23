
public class Keseru extends Spora
{
    /**
     * Konstruktor, amely létrehozza a Keseru spórát egy adott gombászhoz.
     * 
     * @param tartozik A gombász, akihez a spóra tartozik.
     */
   Keseru(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    /**
     * A Keseru spóra kifejti a hatását.
     * 
     * @param b A bogár, amelyre kifejti a hatását.
     */
    @Override
    public void hatas(Bogar b) 
    {
        szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            b.ragasBlock();
        }
        szkeleton.logMethodExit(this, "");
    }

    
}
