package backend;

import java.io.Serializable;

public abstract class Jatekos implements Serializable
{
    protected int id;
    protected int pontok;
    protected String nev;
    protected boolean korVege = false;

    public Jatekos(String nev)
    {
        this.nev = nev;
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

    /**
     * korVege getter.
     * @return korVege boolean.
     */
    public boolean getKorvege(){
        return korVege;
    }

    /**
     * korVege setter.
     * @param ertek
     */
    public void setKorvege(boolean ertek){
        korVege = ertek;
    }

    /**
     * Absztrakt metódus a kör elején végrehajtandó inicializáláshoz.
     */
    public abstract void korElejeInicializalas();

    /**
     * Absztrakt metódus, visszaadja, hogy mit lehet csinálni.
     * @return String.
     */
    public abstract String mitLehetCsinalni();

    public String getNev(){
        return nev;
    }

}