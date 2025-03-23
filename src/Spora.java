


public abstract class Spora 
{
    int plusszpont;
    Gombasz tartozik;

    Spora()
    {
        
    }

    Gombasz getTartozik()
    {
        return tartozik;
    }

    abstract void hatas(Bogar b);
    
}
