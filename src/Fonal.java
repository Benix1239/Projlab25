
public class Fonal extends Osszekoto
{
    private Gombasz tartozik;
  

    public Fonal(Gombasz tartozik, Tekton hova) 
    {
        super(hova);
        this.tartozik = tartozik;
    }

    Gombasz getTartozik()
    {
        return tartozik;
    }

}