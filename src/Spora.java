


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

    void setTartozik(Gombasz g)
    {
        this.tartozik = g;
    }

    int getPluszpont()
    {
        return plusszpont;
    }
    abstract void hatas(Bogar b);
    
    Gombasz getTartozik(){return null;}

}
