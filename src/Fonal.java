
public class Fonal extends Osszekoto
{
    private Gombasz tartozik;
  
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
        szkeleton.logMethodEntry(this, "getTartozik");
        szkeleton.logMethodExit(this, "tartozik");
        return tartozik;
        
    }



}