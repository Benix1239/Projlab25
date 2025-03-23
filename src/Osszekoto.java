
public abstract class Osszekoto
{

    private Tekton hova;
    
    /**
     * Konstruktor, amely inicializálja az Osszekoto objektumot a megadott Tektonnal.
     * 
     * @param hova A Tekton objektum, amelyhez az Osszekoto kapcsolódik.
     */
    public Osszekoto(Tekton hova) 
    {
        this.hova = hova;
    }

    /**
     * Visszaadja a Tekton objektumot, amelyhez az Osszekoto kapcsolódik.
     * 
     * @return A Tekton objektum, amelyhez az Osszekoto kapcsolódik.
     */
    public Tekton getHova()
    {
        szkeleton.logMethodEntry(this, "getHova");
        return hova;
        szkeleton.logMethodExit(this, "Tekton");
    }
    
    
}