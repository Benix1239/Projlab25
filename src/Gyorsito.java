
public class Gyorsito extends Spora
{
    /**
     * Konstruktor, amely létrehozza a Gyorsito spórát egy adott gombászhoz.
     * 
     * @param tartozik A gombász, akihez a spóra tartozik.
     */
    Gyorsito(Gombasz tartozik) 
    {
      super(tartozik);
    }
    
    /**
     * A Gyorsito spóra kifejti a hatását.
     * 
     * @param b A bogár, amelyre kifejti a hatását.
     */
    @Override
    public void hatas(Bogar b) 
    {
        szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            b.gyorsul();
        }
        szkeleton.logMethodExit(this, "");
    }
    
}
