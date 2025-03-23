
public class Fonal
{
    private Gombasz tartozik;
    private Tekton hova;
  

    public Fonal(Tekton hova, Gombasz tartozik) 
    {
        this.hova = hova;
        this.tartozik = tartozik;
    }

    public Gombasz getTartozik(){
        return tartozik;
    }

    public Tekton getHova(){
        return hova;
    }


}