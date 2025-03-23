
public class Keseru extends Spora
{

   Keseru(Gombasz tartozik) 
    {
        super(tartozik);
    }
    
    @Override
    void hatas(Bogar b) 
    {
        if (b != null) {
            b.ragasBlock();
        }
    }

    
}
