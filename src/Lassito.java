
public class Lassito extends Spora
{
    /**
     * Konstruktor, amely létrehozza a Lassito spórát egy adott gombászhoz.
     * 
     * @param tartozik A gombász, akihez a spóra tartozik.
     */
    Lassito(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    /**
     * A Lassito spóra kifejti a hatását.
     * 
     * @param b A bogár, amelyre kifejti a hatását.
     */
    @Override
    public void hatas(Bogar b) 
    {
        szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            b.lassul();
        }
        szkeleton.logMethodExit(this, "");
    }
}
