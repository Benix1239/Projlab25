import java.io.Serializable;


public abstract class Jatekos implements Serializable
{
    protected int id;
    protected int pontok;
    protected String nev;
    protected boolean korVege = false;

    Jatekos()
    {
        
    }

    public void addPoint(int amount)
    {
        
    }

    public void round() 
    {

    }

    public void pontSzamlalo()
    {
        
    }

    public boolean getKorvege(){
        return korVege;
    }

    public void setKorvege(boolean ertek){
        korVege = ertek;
    }

    public abstract void korElejeInicializalas();

}