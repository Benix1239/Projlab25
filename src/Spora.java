


public abstract class Spora 
{
    private int pluszPont;
    private Gombasz tartozik;

    Spora(Gombasz tartozik)
    {
        this.tartozik = tartozik;
    }

    public abstract void hatas(Bogar b);
    
}
