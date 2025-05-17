package backend;

import java.io.Serializable;

public abstract class Jatekos implements Serializable
{
    protected int id;
    protected int pontok;
    protected String nev;
    protected boolean korVege = false;

    protected int gyozelmekSzama = 0;
    protected int meccsekSzama = 0;
    protected int osszesPont = 0;

    public Jatekos(String nev)
    {
        this.nev = nev;
    }
    
    /**
     * Győzelem hozzáadása a játékoshoz
     */
    public void addGyzelem() {
        gyozelmekSzama++;
        meccsekSzama++;
    }

    /**
     * Vereség hozzáadása a játékoshoz (csak a meccsek számát növeli)
     */
    public void addVereseg() {
        meccsekSzama++;
    }

    /**
     * @return a győzelmek száma
     */
    public int getGyozelmekSzama() {
        return gyozelmekSzama;
    }

    /**
     * @return a lejátszott meccsek száma
     */
    public int getMeccsekSzama() {
        return meccsekSzama;
    }

    /**
     * @return az összes szerzett pont
     */
    public int getOsszesPont() {
        return osszesPont;
    }

    public void setGyozelmekSzama(int gyozelmekSzama) {
        this.gyozelmekSzama = gyozelmekSzama;
    }
    
    public void setMeccsekSzama(int meccsekSzama) {
        this.meccsekSzama = meccsekSzama;
    }
    
    public void setOsszesPont(int osszesPont) {
        this.osszesPont = osszesPont;
    }

    public void addPoint(int amount)
    {
        pontok+=amount;
        osszesPont += amount;
    }

    public void round() 
    {

    }

    public void pontSzamlalo()
    {
        
    }

    public int getPontok(){
        return pontok;
    }

    public String getNev(){
        return nev;
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

}