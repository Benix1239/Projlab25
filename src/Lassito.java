
public class Lassito extends Spora
{

    Lassito(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    @Override
    void hatas(Bogar b) 
    {
        if (b != null) {
            b.lassul();
        }
    }
}
