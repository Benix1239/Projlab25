
public class Gyorsito extends Spora
{

    Gyorsito(Gombasz tartozik) 
    {
      super(tartozik);
    }
    
    @Override
    public void hatas(Bogar b) 
    {
        if (b != null) {
            b.gyorsul();
        }
    }
    
}
