


public abstract class Spora 
{
    private int pluszPont;
    private Gombasz tartozik;

    /**
     * Konstruktor, amely létrehozza a spórát egy adott gombászhoz.
     * 
     * @param tartozik A gombász, akihez a spóra tartozik.
     */
    Spora(Gombasz tartozik)
    {
        this.tartozik = tartozik;
    }

    /**
     * A spóra hatása egy adott bogárra.
     * 
     * @param b A bogár, amelyre a spóra hatással lesz.
     */
    public abstract void hatas(Bogar b);
    
}
