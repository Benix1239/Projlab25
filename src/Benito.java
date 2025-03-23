
public class Benito extends Spora
{

    Benito(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    @Override
    void hatas(Bogar b) 
    {
        if (b != null) {
            b.benul();
        }
    }
}
