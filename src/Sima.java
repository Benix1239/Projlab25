public class Sima extends Spora
{
    /**
     * Konstruktor, amely létrehozza a Sima spórát egy adott gombászhoz.
     * 
     * @param tartozik A gombász, akihez a spóra tartozik.
     */
    Sima(Gombasz tartozik)
    {
        super(tartozik);
    }

    /**
     * A Sima spóra nem fejt ki hatást a bogarakra.
     * 
     * @param b A bogár, amelyre a spóra hatással lehetne.
     */
    @Override
    void hatas(Bogar b)
    {
        szkeleton.logMethodEntry(this, "hatas");
        szkeleton.logMethodExit(this, "");
        // nincs hatas
    }
    
}
