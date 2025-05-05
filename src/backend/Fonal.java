package backend;

public class Fonal extends Osszekoto
{
    private Gombasz tartozik;
    private boolean elragva;
  
    /**
     * Konstruktor, amely l�trehozza a Fonal objektumot egy adott gomb�szhoz 
     * �s egy adott Tektonhoz kapcsolja.
     * 
     * @param tartozik A gomb�sz, akihez a fonal tartozik.
     * @param hova A Tekton objektum, amelyhez a fonal kapcsol�dik.
     */
    public Fonal(Tekton hova,Gombasz tartozik) 
    {
        super(hova);
        this.tartozik = tartozik;
    }
    
    /**
     * Visszaadja a gomb�szt, akihez a fonal tartozik.
     * 
     * @return A gomb�sz, akihez a fonal tartozik.
     */
    public Gombasz getTartozik()
    {
        return tartozik;
        
    }

    /**
     * Fonal bogár evés funkciója.
     * @param b A bogár, amit megeszik a fonal.
     * @return Sikeres volt-e.
     */
    public boolean bogarEves(Bogar b){
        if(b.megEve()){
            Gombatest g = new Gombatest(b.getHelyzet(), tartozik);
            b.getHelyzet().setGombatest(g);
            tartozik.gombatestHozzaad(g);
            return true;
        }
        
        return false;
    }

    /**
     * El van-e rágva a fonal
     * @return Sikeres lefutás
     */
    public boolean getElragva(){
        return elragva;
    }

    /**
     * Elragva setter.
     * @param tempelragva
     */
    public void setEkragva(boolean tempelragva){
        this.elragva = tempelragva;
    }

}