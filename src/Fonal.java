
public class Fonal extends Osszekoto
{
    private Gombasz tartozik;
  
    /**
     * Konstruktor, amely létrehozza a Fonal objektumot egy adott gombászhoz 
     * és egy adott Tektonhoz kapcsolja.
     * 
     * @param tartozik A gombász, akihez a fonal tartozik.
     * @param hova A Tekton objektum, amelyhez a fonal kapcsolódik.
     */
    public Fonal(Gombasz tartozik, Tekton hova) 
    {
        super(hova);
        this.tartozik = tartozik;
    }

    /**
     * Visszaadja a gombászt, akihez a fonal tartozik.
     * 
     * @return A gombász, akihez a fonal tartozik.
     */
    public Gombasz getTartozik()
    {
        szkeleton.logMethodEntry(this, "getTartozik");
        return tartozik;
        szkeleton.logMethodExit(this, "");
    }

}