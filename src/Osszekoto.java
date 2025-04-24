
public abstract class Osszekoto
{

    private Tekton hova;
    
    /**
     * Konstruktor, amely inicializ�lja az Osszekoto objektumot a megadott Tektonnal.
     * 
     * @param hova A Tekton objektum, amelyhez az Osszekoto kapcsol�dik.
     */
    public Osszekoto(Tekton hova) 
    {
        this.hova = hova;
    }

    /**
     * Visszaadja a Tekton objektumot, amelyhez az Osszekoto kapcsol�dik.
     * 
     * @return A Tekton objektum, amelyhez az Osszekoto kapcsol�dik.
     */
    public Tekton getHova()
    {
        return hova;
    }

    protected abstract Gombasz getTartozik();
    
}