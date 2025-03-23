
public class Benito extends Spora
{
    /**
     * Konstruktor, amely létrehozza a Benito spórát egy adott gombászhoz.
     * 
     * @param tartozik A gombász, akihez a spóra tartozik.
     */
    Benito(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    /**
     * A Benito spóra kifejti a hatását.
     * 
     * @param b A bogár, amelyre kifejti a hatását.
     */
    @Override
    public void hatas(Bogar b) 
    {
        //szkeleton.logMethodEntry(this, "hatas");
        if (b != null) {
            szkeleton.logMethodEntry(this, "benul");
            b.benul();
            szkeleton.logMethodExit(this, "");
        }
        //szkeleton.logMethodExit(this, "");
    }
}
